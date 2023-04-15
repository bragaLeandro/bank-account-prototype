package br.com.fiap.validator.implementation;

import br.com.fiap.entity.Usuario;
import br.com.fiap.validator.UserValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidCpfValidator extends UserValidator {

    private static final String CPF_REGEX = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";
    @Override
    public void validate(Usuario user) {
        if (!hasValidCpf(user.getCpf()))
            throw new IllegalArgumentException("CPF inválido");
    }
    public static boolean hasValidCpf(String cpf) {
        return Pattern.compile(CPF_REGEX).matcher(cpf).matches();
    }

}
