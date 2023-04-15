package br.com.fiap.repository;

import br.com.fiap.entity.TransacaoPix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransacaoPixRepository extends JpaRepository<TransacaoPix, UUID> {
}