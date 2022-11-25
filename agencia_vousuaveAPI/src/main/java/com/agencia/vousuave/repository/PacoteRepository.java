package com.agencia.vousuave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agencia.vousuave.entity.Pacote;

public interface PacoteRepository extends JpaRepository<Pacote, Integer>{
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/repository/PacoteRepository.java

	@Query(value = "\r\n"
			+ "SELECT \r\n"
			+ "p.id,p.diaria,p.guia_turismo,p.hotel,psg.caminho,psg.destino,psg.desconto,p.preco,psg.id_tp\r\n"
			+ "FROM pacote p  \r\n"
			+ "INNER JOIN passagem AS psg ON p.destino = psg.id", nativeQuery = true)
	List<Pacote> findPacotes();
=======
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/repository/PacoteRepository.java
}
