package br.com.fiap.repository;

import br.com.fiap.entity.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChavePixRepository extends JpaRepository<ChavePix, UUID> {
}