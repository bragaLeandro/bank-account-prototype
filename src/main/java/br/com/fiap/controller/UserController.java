package br.com.fiap.controller;

import br.com.fiap.constants.CommonConstants;
import br.com.fiap.dto.UsuarioDto;
import br.com.fiap.entity.Usuario;
import br.com.fiap.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody UsuarioDto user) {
        try {
            this.usuarioService.createUser(user.toEntity());
            return ResponseEntity.ok(CommonConstants.SUCCESS_MESSAGE);
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        }
    }

    @PutMapping("/inactivate")
    public ResponseEntity inactivateUser(@RequestParam("id") UUID uuid) {
        try {
            this.usuarioService.inactivateUser(uuid);
            return ResponseEntity.ok("Usuario desativado");
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        }
    }

    @GetMapping("/all")
    public Collection<Usuario> findAll() {
        return this.usuarioService.findAllUsers();
    }
}
