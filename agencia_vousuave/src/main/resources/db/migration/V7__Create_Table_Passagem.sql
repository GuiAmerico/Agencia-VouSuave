DROP TABLE IF EXISTS `passagem`;
CREATE TABLE `passagem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `caminho` varchar(255) DEFAULT NULL,
  `desconto` double NOT NULL,
  `destino` varchar(110) NOT NULL,
  `disponibilidade` varchar(10) DEFAULT NULL,
  `origem` varchar(110) NOT NULL,
  `preco` double NOT NULL,
  `tipo_passagem` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
)