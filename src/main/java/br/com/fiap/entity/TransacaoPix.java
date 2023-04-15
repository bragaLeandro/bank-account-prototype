package br.com.fiap.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "transacao_pix")
public class TransacaoPix {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_debitor_id")
    private Usuario debitor;

    @ManyToOne
    @JoinColumn(name = "usuario_creditor_id")
    private Usuario creditor;

    @ManyToOne
    @JoinColumn(name = "chave_pix_id")
    private ChavePix chavePix;

    private double valor;
    private LocalDateTime dataHoraTransacao;
    private String status;
    private String descricao;

    @PrePersist
    private void prePersist() {
        this.dataHoraTransacao = LocalDateTime.now();
    }
}

