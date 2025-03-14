package com.jmp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmp.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	
}
