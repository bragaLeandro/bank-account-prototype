package br.com.fiap.validator.pix.implementation;

import br.com.fiap.entity.ChavePix;
import br.com.fiap.validator.pix.ChavePixValidator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChavePixAlreadyExistsValidator extends ChavePixValidator {

    @Override
    public void validate(ChavePix chavePix) {
        Optional<ChavePix> pixExistente = chavePixRepository.findChavePixByValorChave(chavePix.getValorChave());
        if (pixExistente.isPresent())
            throw new IllegalArgumentException("Chave pix já foi cadastrada");
    }
}
