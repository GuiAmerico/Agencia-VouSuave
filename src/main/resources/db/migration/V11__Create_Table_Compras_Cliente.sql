DROP TABLE IF EXISTS `compras_cliente`;
CREATE TABLE `compras_cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_compra` datetime DEFAULT NULL,
  `cliente_id` int DEFAULT NULL,
  `pacote_id` int DEFAULT NULL,
  `passagem_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1hx4jgdys4u70t2yo1xked61n` (`cliente_id`),
  KEY `FKej891g6hnvjmeijw8loffbtdj` (`pacote_id`),
  KEY `FKg9kiic78bquwpmf3k5k1ujco5` (`passagem_id`),
  CONSTRAINT `FK1hx4jgdys4u70t2yo1xked61n` FOREIGN KEY (`cliente_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKej891g6hnvjmeijw8loffbtdj` FOREIGN KEY (`pacote_id`) REFERENCES `pacote` (`id`),
  CONSTRAINT `FKg9kiic78bquwpmf3k5k1ujco5` FOREIGN KEY (`passagem_id`) REFERENCES `passagem` (`id`)
)
