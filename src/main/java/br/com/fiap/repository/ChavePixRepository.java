package br.com.fiap.repository;

import br.com.fiap.entity.ChavePix;
import br.com.fiap.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChavePixRepository extends JpaRepository<ChavePix, UUID> {

    List<ChavePix> findChavePixesByUsuario(Usuario usuario);

    Optional<ChavePix> findChavePixByValorChave(String valorChave);
}