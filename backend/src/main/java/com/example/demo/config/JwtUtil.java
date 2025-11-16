package com.example.demo.config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final long EXPIRATION = 3600000; // 1h
    private static final Key SECRET = Keys.hmacShaKeyFor("MINHA_CHAVE_SECRETA_256BITS_MINHA_CHAVE".getBytes());

    // GERAR TOKEN
    public String gerarToken(String usuario) {
        return Jwts.builder()
                .setSubject(usuario)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SECRET)
                .compact();
    }

    // VALIDAR TOKEN
    public String validarToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
