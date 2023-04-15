package br.com.fiap.repository;

import br.com.fiap.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    Optional<Produto> findProdutoByNome(String nome);

}