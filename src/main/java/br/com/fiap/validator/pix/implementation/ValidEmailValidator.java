package br.com.fiap.validator.pix.implementation;

import br.com.fiap.constants.ChavePixConstants;
import br.com.fiap.entity.ChavePix;
import br.com.fiap.validator.pix.ChavePixValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidEmailValidator extends ChavePixValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}(\\.[A-Za-z]{2,})?$";

    @Override
    public void validate(ChavePix chavePix) {
        String tipoChave = chavePix.getTipo();
        if (isEmail(tipoChave) && !hasValidEmail(chavePix.getValorChave()))
            throw new IllegalArgumentException("Email inválido");
    }

    private static boolean hasValidEmail(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    private boolean isEmail(String email) {
        return ChavePixConstants.EMAIL.equalsIgnoreCase(email);
    }
}
