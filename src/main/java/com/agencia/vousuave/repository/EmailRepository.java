package com.agencia.vousuave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agencia.vousuave.entity.Email;

public interface EmailRepository extends JpaRepository<Email, Integer>{

}
