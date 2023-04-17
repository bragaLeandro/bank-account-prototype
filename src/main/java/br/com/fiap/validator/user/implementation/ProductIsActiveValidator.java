package br.com.fiap.validator.user.implementation;

import br.com.fiap.entity.Produto;
import br.com.fiap.entity.Usuario;
import br.com.fiap.service.ProdutoService;
import br.com.fiap.validator.user.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductIsActiveValidator extends UserValidator {

    private final ProdutoService produtoService;

    @Autowired
    public ProductIsActiveValidator(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    @Override
    public void validate(Usuario user) {
        for (Produto newProduct : user.getProdutos()) {
            Produto product = produtoService.findByName(newProduct.getNome());
            if (!product.isHabilitado())
                throw new IllegalArgumentException("O produto " + newProduct.getNome() + " não está habilitado");
        }
    }
}
