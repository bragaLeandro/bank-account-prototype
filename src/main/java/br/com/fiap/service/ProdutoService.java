package br.com.fiap.service;

import br.com.fiap.entity.Produto;
import br.com.fiap.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public void createProduct(Produto product) {
        produtoRepository.save(product);
    }

    public void inactivateProduct(UUID uuid) {
        Produto product = this.findById(uuid);

        if (!product.isHabilitado()) {
            throw new IllegalArgumentException("Produto já está desabilitado");
        }

        product.setHabilitado(false);

        produtoRepository.save(product);
    }

    public void activateProduct(UUID uuid) {
        Produto product = this.findById(uuid);

        if (product.isHabilitado()) {
            throw new IllegalArgumentException("Produto já está habilitado");
        }

        product.setHabilitado(true);

        produtoRepository.save(product);
    }

    public Produto findById(UUID uuid) {
        return produtoRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    public Produto findByName(String nome) {
        return produtoRepository.findProdutoByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("Produto " + nome + " não encontrado"));
    }

    public Collection<Produto> findAll() {
        return produtoRepository.findAll();
    }
}
