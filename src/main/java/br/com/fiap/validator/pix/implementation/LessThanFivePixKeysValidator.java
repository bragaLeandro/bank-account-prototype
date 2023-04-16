package br.com.fiap.validator.pix.implementation;

import br.com.fiap.entity.ChavePix;
import br.com.fiap.validator.pix.ChavePixValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessThanFivePixKeysValidator extends ChavePixValidator {

    @Override
    public void validate(ChavePix chavePix) {
        List<ChavePix> chavesPixDoUsuario = chavePixRepository.findChavePixesByUsuario(chavePix.getUsuario());
        if (chavesPixDoUsuario.size() >= 5) {
            throw new IllegalStateException("O usuário já possui 5 chaves Pix cadastradas");
        }
    }
}
