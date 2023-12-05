DROP TRIGGER IF EXISTS `db_springbanking`.`accounts_AFTER_UPDATE`;

DELIMITER $$
USE `db_springbanking`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `db_springbanking`.`accounts_AFTER_UPDATE` AFTER UPDATE ON `accounts` FOR EACH ROW
BEGIN
insert into account_history  
values (new.id, new.alias, new.amount, new.cbu, new.account_type, new.owner_id, current_timestamp());
END$$
DELIMITER ;
