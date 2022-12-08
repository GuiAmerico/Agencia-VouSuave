package com.agencia.vousuave.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agencia.vousuave.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	Optional<Usuario> findByNome(String nome);
	Boolean existsByNome(String nome);
	Boolean existsByEmail(String email);
	Boolean existsByCelular(String celular);
}
