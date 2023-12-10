package com.SpringBanking.api.services;

import com.SpringBanking.api.exceptions.AccountNotExistsException;
import com.SpringBanking.api.exceptions.InsufficientFundsException;
import com.SpringBanking.api.exceptions.TransferNotExistsException;
import com.SpringBanking.api.mappers.TransferMapper;
import com.SpringBanking.api.models.Account;
import com.SpringBanking.api.models.Transfer;
import com.SpringBanking.api.models.dto.TransferDto;
import com.SpringBanking.api.repositories.AccountRepository;
import com.SpringBanking.api.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {
    TransferRepository transferRepository;
    AccountRepository accountRepository;
    public TransferService(TransferRepository repository, AccountRepository accountRepository){

        this.transferRepository= repository;
        this.accountRepository= accountRepository;
    }
    public List<TransferDto> getTransfers() {
        List<Transfer> transfers = transferRepository.findAll();
        return transfers.stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public TransferDto getTransferById(Long id) {
        Transfer entity = transferRepository.findById(id).get();
        return TransferMapper.transferToDto(entity);
    }
    @Transactional
    public TransferDto createTransfer(TransferDto dto, Long idAccount) {
        validateAccountExist(idAccount);
        doTransfer(dto);
        dto.setAccount(accountRepository.findById(idAccount).get());
        dto.setDateTime(LocalDateTime.now());
        Transfer newTransfer = transferRepository.save(TransferMapper.dtoToTransfer(dto));
        return TransferMapper.transferToDto(newTransfer);
    }
    public boolean validateTransfer(TransferDto dto){
        validateAccountExist(dto.getOriginAccount());
        validateAccountExist(dto.getDestinationAccount());
        validateAccountFounds(dto);
        return true;
    }
    //validar que la cuenta de origen y destino existan
    //TODO refactorizar para hacerlo en account
    public void validateAccountExist(Long id){
        if(!accountRepository.existsById(id)){
            throw new AccountNotExistsException("La cuenta con el id " + id + " no existe");
        }
    }
    //validar que la cuenta de origen tenga fondos
    public void validateAccountFounds(TransferDto transferDto){
        Account acount = accountRepository.findById(transferDto.getOriginAccount()).get();
        if (transferDto.getAmount().compareTo(acount.getAmount()) == 1){
            throw new InsufficientFundsException("La cuenta de origen no posee fondos suficientes" +
                    "para realizar la transferencia");
        }
    }
    //Transferir los fondos
    private void transferFunds(TransferDto dto) {
        try {
            Account originAccount = accountRepository.findById(dto.getOriginAccount()).get();
            Account destinationAccount = accountRepository.findById(dto.getDestinationAccount()).get();
            originAccount.setAmount(originAccount.getAmount().subtract(dto.getAmount()));
            destinationAccount.setAmount(destinationAccount.getAmount().add(dto.getAmount()));
            accountRepository.save(originAccount);
            accountRepository.save(destinationAccount);
        }catch(Exception e){
            throw e;
        }
    }
    @Transactional
    public TransferDto updateTransfer(Long id, TransferDto dto) {

        if(transferRepository.existsById(id)){
            //Rollback
            Transfer transfer = transferRepository.findById(id).get();
            undoTransfer(transfer);
            //Cargar datos nuevos a la transferencia
            if(dto.getOriginAccount()!=null){
                transfer.setOriginAccount(dto.getOriginAccount());
            }
            if(dto.getDestinationAccount()!=null){
                transfer.setDestinationAccount(dto.getDestinationAccount());
            }
            if(dto.getAmount()!=null){
                transfer.setAmount(dto.getAmount());
            }
            transfer.setDateTime(LocalDateTime.now());
            //hacer la nueva transferencia
            doTransfer(TransferMapper.transferToDto(transfer));
            Transfer updatedTransfer =transferRepository.save(transfer);
            return TransferMapper.transferToDto(updatedTransfer);
        }else {
            throw new TransferNotExistsException("No existe una transferencia con el id " + id);
        }
    }

    private void undoTransfer(Transfer transfer) {
        TransferDto dto = TransferMapper.transferToDto(transfer);
        doTransfer(invertAccounts(dto));
    }
    private TransferDto invertAccounts(TransferDto dto) {
        Long id_aux = dto.getOriginAccount();
        dto.setOriginAccount(dto.getDestinationAccount());
        dto.setDestinationAccount(id_aux);
        return dto;
    }
    public void doTransfer(TransferDto dto){
        if(validateTransfer(dto)){
            transferFunds(dto);
        }
    }
    @Transactional
    public String deleteTransfer(Long id) {
        try{
            if (transferRepository.existsById(id)){
                undoTransfer(transferRepository.findById(id).get());
                transferRepository.deleteById(id);
                return "La transferencia con id: " + id + " ha sido eliminada";
            } else {
                throw new TransferNotExistsException("La transferencia a eliminar no existe");
            }
        }catch (InsufficientFundsException e){
            throw new InsufficientFundsException( "La transferencia no puede ser eliminada por falta de fondos ");
        }
    }
}
