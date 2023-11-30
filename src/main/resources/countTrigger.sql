DROP TRIGGER IF EXISTS `home_banking`.`cuenta_AFTER_UPDATE`;

DELIMITER $$
USE `home_banking`$$
CREATE DEFINER = CURRENT_USER TRIGGER `home_banking`.`cuenta_AFTER_UPDATE` AFTER UPDATE ON `cuenta` FOR EACH ROW
BEGIN
insert into cuenta_historial 
values (new.id, new.nombre, new.CBU, new.alias, new.dni, new.id_usuario, new.monto, current_timestamp());
END$$
DELIMITER ;