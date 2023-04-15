package br.com.fiap.service;

import br.com.fiap.entity.Saldo;
import br.com.fiap.entity.Usuario;
import br.com.fiap.repository.UsuarioRepository;
import br.com.fiap.validator.UserValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SaldoService saldoService;
    @Autowired
    private ProdutoService produtoService;
    private final List<UserValidator> userValidators;
    @Autowired
    public UsuarioService(List<UserValidator> userValidators) {
        this.userValidators = userValidators;
    }

    public void createUser(Usuario user) {

        userValidators.forEach(validator -> validator.validate(user));

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



}
