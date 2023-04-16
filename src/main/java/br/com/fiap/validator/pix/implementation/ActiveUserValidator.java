package br.com.fiap.validator.pix.implementation;

import br.com.fiap.entity.ChavePix;
import br.com.fiap.validator.pix.ChavePixValidator;
import org.springframework.stereotype.Component;

@Component
public class ActiveUserValidator extends ChavePixValidator {

    @Override
    public void validate(ChavePix chavePix) {
        if (!chavePix.getUsuario().isAtivo())
            throw new IllegalArgumentException("O usuário não pode estar inativo");
    }
}
