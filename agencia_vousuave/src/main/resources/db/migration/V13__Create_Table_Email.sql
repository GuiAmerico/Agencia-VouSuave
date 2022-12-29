DROP TABLE IF EXISTS `email`;
CREATE TABLE `email` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email_from` varchar(255) DEFAULT NULL,
  `email_to` varchar(255) DEFAULT NULL,
  `owner_ref` varchar(255) DEFAULT NULL,
  `send_date_email` datetime DEFAULT NULL,
  `status_email` int DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `text` text,
  PRIMARY KEY (`id`)
)

