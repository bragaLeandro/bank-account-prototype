package br.com.fiap.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @Column(nullable = false)
    private LocalDate dataAtualizacao;

    @Column(nullable = false)
    private boolean habilitado;

    @PrePersist
    private void prePersist() {
        this.dataCadastro = LocalDate.now();
        this.dataAtualizacao = LocalDate.now();
    }

}
