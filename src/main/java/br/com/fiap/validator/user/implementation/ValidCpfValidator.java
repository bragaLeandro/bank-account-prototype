package br.com.fiap.validator.user.implementation;

import br.com.fiap.entity.Usuario;
import br.com.fiap.validator.user.UserValidator;
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

    //TODO: Change to correct validator just like pixCpfValidator
    public static boolean hasValidCpf(String cpf) {
        return Pattern.compile(CPF_REGEX).matcher(cpf).matches();
    }

}
