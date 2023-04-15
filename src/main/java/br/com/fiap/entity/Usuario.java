package br.com.fiap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TAB_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String email;
    private String nomeMae;
    private String senha;
    private String telefone;
    private LocalDate dataNascimento;
    private String endereco;

    private String cpf;
    private String rg;
    private boolean pessoaExpostaPoliticamente;
    private double rendaMensal;
    private double valorPatrimonio;
    private LocalDate dataCadastro;
    private LocalDate dataAtualizacao;
    private boolean ativo;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Saldo saldo;

    @ElementCollection
    @CollectionTable(name = "usuario_produtos", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "produto")
    private List<String> produtos;

    public Usuario(UUID id, String nome, String email, String nomeMae, String telefone,
                   LocalDate dataNascimento, String endereco, String cpf, String rg,
                   boolean pessoaExpostaPoliticamente, double rendaMensal, double valorPatrimonio,
                   LocalDate dataCadastro, LocalDate dataAtualizacao, boolean ativo, List<String> produtos) {
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
