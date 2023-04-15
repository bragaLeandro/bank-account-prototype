package br.com.fiap.repository;

import br.com.fiap.entity.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaldoRepository extends JpaRepository<Saldo, UUID> {
}