package br.com.fiap.validator;

import java.util.regex.Pattern;


public class UsuarioValidator {

    private static final String CPF_REGEX = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";

    public static boolean hasValidCpf(String cpf) {
        return Pattern.compile(CPF_REGEX).matcher(cpf).matches();
    }
}
