package br.com.fiap.dto;

import br.com.fiap.entity.ChavePix;
import lombok.Data;

@Data
public class TransacaoPixDto {

    private double valorTransferencia;
    private ChavePix debitor;
    private ChavePix creditor;

}
