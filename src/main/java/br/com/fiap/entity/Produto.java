package br.com.fiap.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    private LocalDate dataCadastro;

    private LocalDate dataAtualizacao;

    private boolean habilitado;

    @PrePersist
    private void setDefaultDates() {
        this.dataCadastro = LocalDate.now();
        this.dataAtualizacao = LocalDate.now();
    }

}
