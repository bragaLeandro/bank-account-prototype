package br.com.fiap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "chave_pix")
public class ChavePix {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="chave_pix_id")
    private UUID id;
    private String tipo;
    private boolean ativa;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;
    private String valorChave;

}
