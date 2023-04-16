package br.com.fiap.controller;

import br.com.fiap.dto.ChavePixDto;
import br.com.fiap.entity.ChavePix;
import br.com.fiap.entity.Usuario;
import br.com.fiap.service.ChavePixService;
import br.com.fiap.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chavepix")
public class ChavePixController {

    private final ChavePixService chavePixService;
    private final UsuarioService usuarioService;

    @Autowired
    public ChavePixController(ChavePixService chavePixService, UsuarioService usuarioService) {
        this.chavePixService = chavePixService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> createChavePix(@RequestBody ChavePixDto chavePixDto) {
        try {
            Usuario usuario = usuarioService.findById(chavePixDto.getUsuarioId());
            ChavePix chavePix = chavePixDto.toEntity(usuario);
            ChavePix createdChavePix = chavePixService.createChavePix(chavePix);
            ChavePixDto responseChavePixDto = ChavePixDto.fromEntity(createdChavePix);
            return ResponseEntity.ok(responseChavePixDto.getValorChave());
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.badRequest().body(ie.getMessage());
        }
    }
}
