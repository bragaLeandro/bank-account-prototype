package br.com.fiap.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
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
