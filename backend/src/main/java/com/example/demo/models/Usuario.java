package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuarios")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String apelido;
    private String senha;
    private String email;

    public Integer getId() { return id; }

    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
