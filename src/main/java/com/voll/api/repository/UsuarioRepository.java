package com.voll.api.repository;

import com.voll.api.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<User, Long> {
	UserDetails findByLogin(String username);
}
