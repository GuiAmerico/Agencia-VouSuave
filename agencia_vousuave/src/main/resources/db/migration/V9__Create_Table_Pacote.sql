DROP TABLE IF EXISTS `pacote`;
CREATE TABLE `pacote` (
  `id` int NOT NULL AUTO_INCREMENT,
  `caminho` varchar(255) NOT NULL,
  `desconto` double NOT NULL,
  `destino` varchar(50) NOT NULL,
  `diaria` int NOT NULL,
  `guia_turismo` bit(1) NOT NULL,
  `hotel` varchar(40) NOT NULL,
  `internacional` bit(1) NOT NULL,
  `preco` double NOT NULL,
  PRIMARY KEY (`id`)
) 

