package br.com.fiap.service;

import br.com.fiap.entity.Produto;
import br.com.fiap.entity.Saldo;
import br.com.fiap.entity.Usuario;
import br.com.fiap.repository.ProdutoRepository;
import br.com.fiap.repository.UsuarioRepository;
import br.com.fiap.validator.UsuarioValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    SaldoService saldoService;

    @Autowired
    ProdutoService produtoService;

    public void createUser(Usuario user) {

        if (this.emailAlreadyExists(user.getEmail()))
            throw new IllegalArgumentException("Email já cadastrado");

        if (this.hasThreeAccounts(user.getCpf()))
            throw new IllegalArgumentException("Não é possível ter mais de 3 contas para o mesmo cpf");

        if (!UsuarioValidator.hasValidCpf(user.getCpf()))
            throw new IllegalArgumentException("CPF inválido");

        for (String p : user.getProdutos()) {
            produtoService.findBy
        }

        Saldo initialBalance = saldoService.setInicialBalance(user);
        user.setSaldo(initialBalance);

        usuarioRepository.save(user);
    }

    public void inactivateUser(UUID uuid) {
        Usuario user = this.findById(uuid);

        if (!user.isAtivo()) {
            throw new IllegalArgumentException("Usuário já está inativo");
        }

        user.setAtivo(false);

        usuarioRepository.save(user);
    }

    public Usuario findById(UUID uuid) {
        return usuarioRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public Collection<Usuario> findAllUsers() {
        return usuarioRepository.findAll();
    }

    public boolean emailAlreadyExists(String email) {
        return usuarioRepository.findUsuarioByEmail(email).isPresent();
    }

    public boolean hasThreeAccounts(String cpf) {
        return usuarioRepository.findUsuariosByCpf(cpf).size() >= 3;
    }

}
