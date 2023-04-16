package br.com.fiap.dto;

import br.com.fiap.entity.Produto;
import br.com.fiap.entity.Saldo;
import br.com.fiap.entity.Usuario;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class UsuarioResponseDto {

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
    private Saldo saldo;

    public static UsuarioResponseDto fromEntity(Usuario user) {
        UsuarioResponseDto usuarioDto = new UsuarioResponseDto();
        usuarioDto.setId(user.getId());
        usuarioDto.setNome(user.getNome());
        usuarioDto.setEmail(user.getEmail());
        usuarioDto.setNomeMae(user.getNomeMae());
        usuarioDto.setTelefone(user.getTelefone());
        usuarioDto.setDataNascimento(user.getDataNascimento());
        usuarioDto.setEndereco(user.getEndereco());
        usuarioDto.setCpf(user.getCpf());
        usuarioDto.setRg(user.getRg());
        usuarioDto.setPessoaExpostaPoliticamente(user.isPessoaExpostaPoliticamente());
        usuarioDto.setRendaMensal(user.getRendaMensal());
        usuarioDto.setValorPatrimonio(user.getValorPatrimonio());
        usuarioDto.setAtivo(user.isAtivo());
        usuarioDto.setProdutos(user.getProdutos());
        usuarioDto.setSaldo(user.getSaldo());
        return usuarioDto;
    }
}
