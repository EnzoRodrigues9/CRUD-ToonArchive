package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Usuario; 

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByApelido(String apelido);
}
