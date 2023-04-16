package br.com.fiap.controller;

import br.com.fiap.dto.TransacaoPixDto;
import br.com.fiap.service.TransacaoPixService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransacaoController {

    private final TransacaoPixService transacaoPixService;

    public TransacaoController(TransacaoPixService transacaoPixService) {
        this.transacaoPixService = transacaoPixService;
    }

    @PostMapping
    public ResponseEntity<String> makeTransfer(@RequestBody TransacaoPixDto transacaoPixDto) {
        try {
            this.transacaoPixService.makeTransference(transacaoPixDto);
            return ResponseEntity.ok("Transferência finalizada");
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.badRequest().body(ie.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
