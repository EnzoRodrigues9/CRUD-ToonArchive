package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
    origins = { "http://localhost:4200" },
    allowedHeaders = "*",
    methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS },
    allowCredentials = "true",
    maxAge = 3600
)
@RestController
@RequestMapping("/usuario")
public class UsuarioRest {

    @Autowired
    private UsuarioDAO usuarioDAO;

    // CREATE
    @PostMapping
    public void post(@RequestBody Usuario usuario) {
        this.usuarioDAO.save(usuario);
    }

    // READ
    @GetMapping
    public List<Usuario> get() {
        return this.usuarioDAO.findAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public void put(@PathVariable("id") Integer id, @RequestBody Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioDAO.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setUsuario(usuarioAtualizado.getUsuario());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha());
            usuarioDAO.save(usuario);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        usuarioDAO.deleteById(id);
    }
}
