
DROP TRIGGER IF EXISTS `home_banking`.`usuario_AFTER_UPDATE`;

DELIMITER $$
USE `home_banking`$$
CREATE DEFINER = CURRENT_USER TRIGGER `home_banking`.`usuario_AFTER_UPDATE` AFTER UPDATE ON `usuario` FOR EACH ROW
BEGIN
insert into usuario_historial 
values (new.id, new.nombre_usuario, new.email, new.contraseña, new.dni, new.fecha_nacimiento, new.domicilio, new.id_cuenta, current_timestamp());
END$$
DELIMITER ;

