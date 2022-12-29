DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `celular` varchar(11) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  `data_nascimento` datetime NOT NULL,
  `email` varchar(110) NOT NULL,
  `nome` varchar(110) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h1mvtqgunowkllo2v0mlbw233` (`celular`),
  UNIQUE KEY `UK_692bsnqxa8m9fmx7m1yc6hsui` (`cpf`)
)
