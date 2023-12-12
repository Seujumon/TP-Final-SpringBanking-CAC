CREATE TABLE `account_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) DEFAULT NULL,
  `amount` decimal(38,2) DEFAULT NULL,
  `cbu` varchar(255) NOT NULL,
  `account_type` enum('DOLLAR_SAVINGS_BANK','SAVINGS_BANK','SAVING_ACCOUNT') DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  updated_at datetime not null,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g1kavfy7acqcs5gda9u5rphgi` (`cbu`),
  UNIQUE KEY `UK_821uc3eux26d1jx0bmpb195aa` (`alias`),
  KEY `FKjln86358moqf5k5pw89oiq8ur` (`owner_id`),
  CONSTRAINT `fk_owner` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
