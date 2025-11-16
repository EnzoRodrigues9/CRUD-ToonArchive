package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Usuario;             
import com.example.demo.repositories.UsuarioDAO;   

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioRest {

    @Autowired
    private UsuarioDAO dao;

    @PostMapping
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        return dao.save(usuario);
    }
}
