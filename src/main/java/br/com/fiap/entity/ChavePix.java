package br.com.fiap.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "chave_pix")
public class ChavePix {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String tipo;
    private boolean ativa;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String valorChave;
    private String chaveEVP;

}
