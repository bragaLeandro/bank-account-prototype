package br.com.fiap.validator.user.implementation;

import br.com.fiap.entity.Usuario;
import br.com.fiap.validator.user.UserValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductListValidator extends UserValidator {

    @Override
    public void validate(Usuario user) {
        if (user.getProdutos() == null || user.getProdutos().isEmpty())
            throw new IllegalArgumentException("A lista de produtos está vazia");
    }

}
