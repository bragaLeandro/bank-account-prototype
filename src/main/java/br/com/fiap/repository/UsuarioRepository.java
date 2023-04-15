package br.com.fiap.repository;

import br.com.fiap.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findUsuarioByEmail(String email);

    Collection<Usuario> findUsuariosByCpf(String cpf);
}