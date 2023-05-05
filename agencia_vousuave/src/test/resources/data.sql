INSERT INTO `passagem`(`caminho`,`desconto`,`destino`,`disponibilidade`,`origem`,`preco`,`tipo_passagem`)
VALUES('CaminhoImagem',0.05,'Destino',null,'Origem',1000,'AVIAO'),('CaminhoImagem',0.05,'Destino',null,'Origem',1000,'ONIBUS'),('CaminhoImagem',0.05,'Destino',null,'Origem',1000,'CRUZEIRO');


INSERT INTO `pacote`(`caminho`,`desconto`,`destino`,`diaria`,`guia_turismo`,`hotel`,`internacional`,`preco`)
VALUES('CaminhoImagem',0.1,'Destino',1,false,'Hotel 1',false,1000),('CaminhoImagem',0.2,'Destino',2,false,'Hotel 2',false,2000),('CaminhoImagem',0.3,'Destino',3,false,'Hotel 3',false,3000);


INSERT INTO `usuario`(`celular`,`cpf`,`data_nascimento`,`email`,`nome`,`senha`,`status`)
VALUES('21888888888','88913862000',now(),'usuario1@email.com','Usuario 1','654321',true),('21777777777','57677415067',now(),'usuario2@email.com','Usuario 2','987654',true),('21666666666','99828656094',now(),'usuario3@email.com','Usuario 3','000000',true);

INSERT INTO `compras_cliente`(`cliente_id`,`pacote_id`,`passagem_id`)
VALUES(1,1,1),(2,2,2),(3,3,3);