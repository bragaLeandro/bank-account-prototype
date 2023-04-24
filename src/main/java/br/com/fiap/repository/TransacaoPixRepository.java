package br.com.fiap.repository;

import br.com.fiap.entity.TransacaoPix;
import br.com.fiap.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransacaoPixRepository extends JpaRepository<TransacaoPix, UUID> {

    List<TransacaoPix> findTransacaoPixesByCreditor(Usuario user);
    List<TransacaoPix> findTransacaoPixesByDebitor(Usuario user);

}