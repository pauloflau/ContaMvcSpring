package com.jmp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmp.entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Usuario findByEmail(String email);
	Usuario findByEmailAndSenha(String email, String senha);
		
}
