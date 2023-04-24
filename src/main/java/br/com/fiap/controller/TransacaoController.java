package br.com.fiap.controller;

import br.com.fiap.dto.TransacaoPixDto;
import br.com.fiap.dto.TransacaoPixTransactionDto;
import br.com.fiap.entity.TransacaoPix;
import br.com.fiap.service.TransacaoPixService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
public class TransacaoController {

    private final TransacaoPixService transacaoPixService;

    public TransacaoController(TransacaoPixService transacaoPixService) {
        this.transacaoPixService = transacaoPixService;
    }

    @PostMapping("/transfer")
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

    @GetMapping("/history")
    public ResponseEntity<List<TransacaoPixTransactionDto>> getHistoryTransactions(@RequestParam("id") UUID userUuid) {
        List<TransacaoPix> transactionsEntity = this.transacaoPixService.getHistoryTransactions(userUuid);
        List<TransacaoPixTransactionDto> transactions = transactionsEntity.stream()
                .map(TransacaoPixTransactionDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactions);
    }
}
