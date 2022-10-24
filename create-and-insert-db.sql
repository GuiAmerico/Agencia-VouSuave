CREATE DATABASE agencia_vousuave;
USE agencia_vousuave;

INSERT INTO Passagem (origem,destino,tipo_passagem,caminho,preco,desconto,disponibilidade)
    VALUES
        ("Rio de Janeiro-RJ","Salvador","AVIAO","https://cdn.discordapp.com/attachments/1028712344110514176/1033439347703296000/Salvador.jpg",839,0.10,null),
        ("Rio de Janeiro-RJ","Maceió","AVIAO","https://cdn.discordapp.com/attachments/1028712344110514176/1033439346331758754/Maceio.jpg",719,0.05,null),
        ("Rio de Janeiro-RJ","Fortaleza","AVIAO","https://cdn.discordapp.com/attachments/1028712344110514176/1033438806998786219/Fortaleza.jpg",889,0.15,null),
        ("Rio de Janeiro-RJ","Foz do Iguaçu","AVIAO","https://cdn.discordapp.com/attachments/1028712344110514176/1033438807263031446/FozDoIguacu.jpg",679,0.05,null),
        ("Rio de Janeiro-RJ","Recife","AVIAO","https://cdn.discordapp.com/attachments/1028712344110514176/1033439347451645982/Recife.jpg",799,0.10,null),
        ("Rio de Janeiro-RJ","São Paulo","AVIAO","https://cdn.discordapp.com/attachments/1028712344110514176/1033439347942359221/SaoPaulo.jpg",656,0,null),
        ("Rio de Janeiro-RJ","Belo Horizonte","ONIBUS","https://cdn.discordapp.com/attachments/1028712344110514176/1033438804926795776/BeloHorizonte.jpg",334,0,null),
        ("Rio de Janeiro-RJ","Vila Velha","ONIBUS","https://cdn.discordapp.com/attachments/1028712344110514176/1033439348135317514/VilaVelha.jpg",299,0.04,null),
        ("Rio de Janeiro-RJ","Natal","ONIBUS","https://cdn.discordapp.com/attachments/1028712344110514176/1033439346885406830/Natal.jpg",521,0.08,null),
        ("Rio de Janeiro-RJ","Curitiba","ONIBUS","https://cdn.discordapp.com/attachments/1028712344110514176/1033438806789062686/Curitiba.jpg",445,0.05,null),
        ("Rio de Janeiro-RJ","Manaus","ONIBUS","https://cdn.discordapp.com/attachments/1028712344110514176/1033439346591797278/Manaus.jpg",615,0.10,null),
        ("Rio de Janeiro-RJ","Campinas","ONIBUS","https://cdn.discordapp.com/attachments/1028712344110514176/1033438805799211138/Campinas.jpg",449,0.05,null),
        ("Rio de Janeiro(RJ)","Rio de Janeiro(RJ)","CRUZEIRO","https://cdn.discordapp.com/attachments/1028712344110514176/1033438806021525604/cruzeiro1.jpg",1639,0.10,"29/10"),
        ("Santos(SP)","Santos(SP)","CRUZEIRO","https://cdn.discordapp.com/attachments/1028712344110514176/1033438806310920283/cruzeiro2.jpg",1789,0.10,"11/11"),
        ("Rio de Janeiro(RJ)","Itajaí(BC)","CRUZEIRO","https://cdn.discordapp.com/attachments/1028712344110514176/1033438806537416725/cruzeiro3.jpg",1458,0,"07/12");


INSERT INTO Pacote(destino,diaria,caminho,preco,hotel,guia_turismo,internacional,desconto)
	VALUES
		("Gramado",3,"https://cdn.discordapp.com/attachments/1028712344110514176/1034154285526700083/Gramado.jpg",1879,"Hotel 1",TRUE,FALSE,0.10),
		("Pernambuco",5,"https://cdn.discordapp.com/attachments/1028712344110514176/1034154285740593152/Pernambuco1.jpg",1999,"Hotel 2",TRUE,FALSE,0.03),
		("Bonito",4,"https://cdn.discordapp.com/attachments/1028712344110514176/1034154285132431392/Bonito1.png",2019,"Hotel 3",TRUE,FALSE,0.4),
		("Tóquio",5,"https://cdn.discordapp.com/attachments/1028712344110514176/1034155266905743510/Tokyo.jpg",3893,"Hotel 4",TRUE,TRUE,0.02),
		("Bali",3,"https://cdn.discordapp.com/attachments/1028712344110514176/1034155267492958310/Bali.jpg",4121,"Hotel 5",TRUE,TRUE,0.5),
		("Barcelona",4,"https://cdn.discordapp.com/attachments/1028712344110514176/1034154284482310226/Barcelona.jpg",4312,"Hotel 6",TRUE,TRUE,0.5),
		("Disney",4,"https://cdn.discordapp.com/attachments/1028712344110514176/1034158363623305256/Disney.jpg",3499,"Hotel 7",TRUE,TRUE,0.12),
		("Buenos Aires",5,"https://cdn.discordapp.com/attachments/1028712344110514176/1034155267224514650/BuenoAires.jpg",2590,"Hotel 8",TRUE,TRUE,0.01),
		("Roma",3,"https://cdn.discordapp.com/attachments/1028712344110514176/1034154286030012466/Roma.jpg",3889,"Hotel 9",TRUE,TRUE,0.13);          
	
