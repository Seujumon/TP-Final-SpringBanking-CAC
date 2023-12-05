CREATE TABLE `user_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthdate` date DEFAULT NULL,
  `dni` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `homeaddres` varchar(255) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  updated_at datetime not null,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6aphui3g30h49muho4c91n0yl` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

