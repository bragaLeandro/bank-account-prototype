package br.com.fiap.dto;

import br.com.fiap.entity.TransacaoPix;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransacaoPixTransactionDto {

    private double valorTransferencia;
    private String status;
    private LocalDateTime dataTransacao;

    public static TransacaoPixTransactionDto fromEntity(TransacaoPix transacaoPix) {
        TransacaoPixTransactionDto dto = new TransacaoPixTransactionDto();
        dto.setValorTransferencia(transacaoPix.getValor());
        dto.setDataTransacao(transacaoPix.getDataHoraTransacao());
        dto.setStatus(transacaoPix.getStatus());
        return dto;
    }
}
