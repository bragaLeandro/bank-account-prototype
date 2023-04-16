package br.com.fiap.validator.pix.implementation;

import br.com.fiap.constants.ChavePixConstants;
import br.com.fiap.entity.ChavePix;
import br.com.fiap.validator.pix.ChavePixValidator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class KeyTypeValidator extends ChavePixValidator {

    private static final Set<String> validKeyTypes = new HashSet<>(Arrays.asList(
            ChavePixConstants.CPF, ChavePixConstants.EMAIL,
            ChavePixConstants.EVP, ChavePixConstants.TELEFONE));

    @Override
    public void validate(ChavePix chavePix) {
        if (!isValidType(chavePix.getTipo()))
            throw new IllegalArgumentException("Tipo de chave não suportada");
    }

    private static boolean isValidType(String keyPixType) {
        return validKeyTypes.contains(keyPixType);
    }
}
