package br.com.fiap.service;

import br.com.fiap.entity.Produto;
import br.com.fiap.entity.Saldo;
import br.com.fiap.entity.Usuario;
import br.com.fiap.repository.UsuarioRepository;
import br.com.fiap.validator.user.UserValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final SaldoService saldoService;
    private final List<UserValidator> userValidators;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, SaldoService saldoService, List<UserValidator> userValidators) {
        this.usuarioRepository = usuarioRepository;
        this.saldoService = saldoService;
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

    public void activateUser(UUID uuid) {
        Usuario user = this.findById(uuid);

        if (user.isAtivo()) {
            throw new IllegalArgumentException("Usuário já está ativo");
        }

        user.setAtivo(true);

        usuarioRepository.save(user);
    }

    public void removeProductFromUser(UUID userId, UUID productId) {
        Usuario usuario = this.findById(userId);

        Produto produto = usuario.getProdutos().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado na lista de produtos do usuário"));

        usuario.getProdutos().remove(produto);

        usuarioRepository.save(usuario);
    }

    public Usuario findById(UUID uuid) {
        return usuarioRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public List<Usuario> findAllUsers() {
        return usuarioRepository.findAll();
    }
}
