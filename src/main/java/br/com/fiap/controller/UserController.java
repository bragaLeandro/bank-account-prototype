package br.com.fiap.controller;

import br.com.fiap.constants.CommonConstants;
import br.com.fiap.dto.UsuarioDto;
import br.com.fiap.dto.UsuarioResponseDto;
import br.com.fiap.entity.Usuario;
import br.com.fiap.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UsuarioService usuarioService;

    @Autowired
    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UsuarioDto user) {
        try {
            this.usuarioService.createUser(user.toEntity());
            return ResponseEntity.ok(CommonConstants.SUCCESS_MESSAGE);
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping("/inactivate")
    public ResponseEntity<String> inactivateUser(@RequestParam("id") UUID uuid) {
        try {
            this.usuarioService.inactivateUser(uuid);
            return ResponseEntity.ok("Usuario desativado");
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping("/activate")
    public ResponseEntity<String> activateUser(@RequestParam("id") UUID uuid) {
        try {
            this.usuarioService.activateUser(uuid);
            return ResponseEntity.ok("Usuario ativado");
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/all")
    public List<UsuarioResponseDto> findAll() {
        try {
            List<Usuario> usuarios = this.usuarioService.findAllUsers();

            return usuarios.stream()
                    .map(UsuarioResponseDto::fromEntity)
                    .toList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
