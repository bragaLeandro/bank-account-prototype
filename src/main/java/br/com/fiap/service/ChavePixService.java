package br.com.fiap.service;

import br.com.fiap.entity.ChavePix;
import br.com.fiap.repository.ChavePixRepository;
import br.com.fiap.validator.pix.ChavePixValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChavePixService {

    private final ChavePixRepository chavePixRepository;
    private final List<ChavePixValidator> chavePixValidators;

    @Autowired
    public ChavePixService(ChavePixRepository chavePixRepository, List<ChavePixValidator> chavePixValidators) {
        this.chavePixRepository = chavePixRepository;
        this.chavePixValidators = chavePixValidators;
    }


    public ChavePix createChavePix(ChavePix chavePix) {

        chavePixValidators.forEach(validator -> validator.validate(chavePix));
        return chavePixRepository.save(chavePix);
    }

}
