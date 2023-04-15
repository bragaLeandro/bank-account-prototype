package br.com.fiap.service;

import br.com.fiap.entity.Produto;
import br.com.fiap.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public void createProduct(Produto product) {
        produtoRepository.save(product);
    }

//    public boolean productIsActive(String nome) {
//    }

    public Produto findById(UUID uuid) {
        return produtoRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }
}
