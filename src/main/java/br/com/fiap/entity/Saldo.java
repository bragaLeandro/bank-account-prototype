package br.com.fiap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "saldo")
public class Saldo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private double valor;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}

