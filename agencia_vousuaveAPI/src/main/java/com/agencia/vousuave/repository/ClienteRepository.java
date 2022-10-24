package com.agencia.vousuave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agencia.vousuave.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	
}
