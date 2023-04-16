package br.com.fiap.validator.pix;

import br.com.fiap.entity.ChavePix;
import br.com.fiap.repository.ChavePixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class ChavePixValidator {

    @Autowired
    protected ChavePixRepository chavePixRepository;

    public abstract void validate(ChavePix chavePix);
}
