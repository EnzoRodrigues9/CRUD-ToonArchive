package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtUtil;
import com.example.demo.models.Usuario;          
import com.example.demo.repositories.UsuarioDAO; 


@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthRest {

    @Autowired
    private UsuarioDAO dao;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Map<String, Object> login(@RequestBody Usuario u) {

        Optional<Usuario> opt = dao.findByApelido(u.getApelido());

        if (opt.isPresent()) {
            Usuario user = opt.get();

            if (user.getSenha().equals(u.getSenha())) {

                String token = jwtUtil.gerarToken(user.getApelido());

                Map<String, Object> resp = new HashMap<>();
                resp.put("token", token);
                resp.put("apelido", user.getApelido());

                return resp;
            }
        }

        throw new RuntimeException("Credenciais inválidas");
    }
}
