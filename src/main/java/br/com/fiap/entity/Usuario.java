package br.com.fiap.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "TAB_USER")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="usuario_id")
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nomeMae;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    @Column(nullable = false)
    private boolean pessoaExpostaPoliticamente;

    @Column(nullable = false)
    private double rendaMensal;

    @Column(nullable = false)
    private double valorPatrimonio;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @Column(nullable = false)
    private LocalDate dataAtualizacao;

    @Column(nullable = false)
    private boolean ativo;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Saldo saldo;

    @ManyToMany
    @JoinTable(name = "usuario_produtos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;

    public Usuario(UUID id, String nome, String email, String nomeMae, String telefone,
                   LocalDate dataNascimento, String endereco, String cpf, String rg,
                   boolean pessoaExpostaPoliticamente, double rendaMensal, double valorPatrimonio,
                   LocalDate dataCadastro, LocalDate dataAtualizacao, boolean ativo, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.nomeMae = nomeMae;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.cpf = cpf;
        this.rg = rg;
        this.pessoaExpostaPoliticamente = pessoaExpostaPoliticamente;
        this.rendaMensal = rendaMensal;
        this.valorPatrimonio = valorPatrimonio;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
        this.ativo = ativo;
        this.produtos = produtos;
    }

    @PrePersist
    private void prePersist() {
        this.dataCadastro = LocalDate.now();
        this.dataAtualizacao = LocalDate.now();
    }
}
