package br.com.fiap.dto;

import br.com.fiap.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {

    private UUID uuid;
    private String nome;
    private LocalDate dataCadastro;
    private LocalDate dataAtualizacao;
    private boolean habilitado;

    public ProdutoDto(String nome, boolean habilitado) {
        this.nome = nome;
        this.habilitado = habilitado;
    }

    public Produto toEntity(ProdutoDto produtoDto) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setHabilitado(habilitado);
        return produto;
    }
}
