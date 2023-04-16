package br.com.fiap.dto;

import br.com.fiap.entity.Produto;
import br.com.fiap.entity.Usuario;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class UsuarioDto {

    private UUID id;
    private String nome;
    private String email;
    private String nomeMae;
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
    private List<Produto> produtos;
    private String senha;

    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setNomeMae(nomeMae);
        usuario.setTelefone(telefone);
        usuario.setDataNascimento(dataNascimento);
        usuario.setEndereco(endereco);
        usuario.setCpf(cpf);
        usuario.setRg(rg);
        usuario.setPessoaExpostaPoliticamente(pessoaExpostaPoliticamente);
        usuario.setRendaMensal(rendaMensal);
        usuario.setValorPatrimonio(valorPatrimonio);
        usuario.setDataCadastro(dataCadastro);
        usuario.setDataAtualizacao(dataAtualizacao);
        usuario.setAtivo(ativo);
        usuario.setProdutos(produtos);
        usuario.setSenha(senha);
        return usuario;
    }
}
