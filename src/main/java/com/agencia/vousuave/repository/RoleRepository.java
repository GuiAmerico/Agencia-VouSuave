package com.agencia.vousuave.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agencia.vousuave.entity.Role;
import com.agencia.vousuave.enums.ERole;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByName(ERole name);
}
