package br.com.fiap.validator.pix.implementation;

import br.com.fiap.constants.ChavePixConstants;
import br.com.fiap.entity.ChavePix;
import br.com.fiap.validator.pix.ChavePixValidator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PixKeyIsEvpValidator extends ChavePixValidator {

    @Override
    public void validate(ChavePix chavePix) {
        if (ChavePixConstants.EVP.equalsIgnoreCase(chavePix.getTipo())) {
            chavePix.setValorChave(UUID.randomUUID().toString());
        }
    }
}
