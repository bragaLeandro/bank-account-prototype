package br.com.fiap.validator.pix.implementation;

import br.com.fiap.constants.ChavePixConstants;
import br.com.fiap.entity.ChavePix;
import br.com.fiap.validator.pix.ChavePixValidator;
import org.springframework.stereotype.Component;

@Component
public class ValidCpfPixValidator extends ChavePixValidator {

    @Override
    public void validate(ChavePix chavePix) {
        String tipoChave = chavePix.getTipo();
        if (isCpf(tipoChave) && !hasValidCpf(removePonctuation(chavePix.getValorChave())))
            throw new IllegalArgumentException("CPF inválido");
    }

    public static String removePonctuation(String cpf) {
        return cpf.replaceAll("[.-]", "");
    }
    private static boolean hasValidCpf(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.chars().allMatch(c -> c == cpf.charAt(0))) {
            return false;
        }

        int[] digits = cpf.chars().map(Character::getNumericValue).toArray();

        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += digits[i] * (10 - i);
            sum2 += digits[i] * (11 - i);
        }

        int calculatedDigit1 = 11 - (sum1 % 11);
        if (calculatedDigit1 >= 10) {
            calculatedDigit1 = 0;
        }
        int calculatedDigit2 = 11 - ((sum2 + calculatedDigit1 * 2) % 11);
        if (calculatedDigit2 >= 10) {
            calculatedDigit2 = 0;
        }

        return calculatedDigit1 == digits[9] && calculatedDigit2 == digits[10];
    }

    private boolean isCpf(String tipoChave) {
        return ChavePixConstants.CPF.equalsIgnoreCase(tipoChave);
    }

}
