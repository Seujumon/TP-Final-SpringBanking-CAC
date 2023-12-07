
DROP TRIGGER IF EXISTS `db_springbanking`.`users_AFTER_UPDATE`;

DELIMITER $$
USE `db_springbanking`$$
CREATE DEFINER = CURRENT_USER TRIGGER `db_springbanking`.`users_AFTER_UPDATE` AFTER UPDATE ON `users` FOR EACH ROW
BEGIN
insert into user_history
values (new.id, new.birthdate, new.dni, new.email, new.homeaddres, new.password, new.username, current_timestamp());

END$$
DELIMITER ;

