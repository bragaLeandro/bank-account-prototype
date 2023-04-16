package br.com.fiap.service;

import br.com.fiap.dto.TransacaoPixDto;
import br.com.fiap.entity.Saldo;
import br.com.fiap.entity.Usuario;
import br.com.fiap.repository.ChavePixRepository;
import br.com.fiap.repository.SaldoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransacaoPixService {

    private final SaldoRepository saldoRepository;
    private final ChavePixRepository chavePixRepository;

    public TransacaoPixService(SaldoRepository saldoRepository, ChavePixRepository chavePixRepository) {
        this.saldoRepository = saldoRepository;
        this.chavePixRepository = chavePixRepository;
    }

    @Transactional
    public void makeTransference(TransacaoPixDto transacaoPixDto) {
        Usuario usuarioDebitor = chavePixRepository.findChavePixByValorChave(transacaoPixDto.getDebitor().getValorChave()).get().getUsuario();
        Usuario usuarioCreditor = chavePixRepository.findChavePixByValorChave(transacaoPixDto.getCreditor().getValorChave()).get().getUsuario();

        if (!usuarioDebitor.isAtivo() || !usuarioCreditor.isAtivo()) {
            throw new IllegalArgumentException("Ambos os usuários devem estar ativos");
        }

        Saldo saldoDebitor = usuarioDebitor.getSaldo();
        Saldo saldoCreditor = usuarioCreditor.getSaldo();

        if (saldoDebitor.getValor() <= 0) {
            throw new IllegalArgumentException("Saldo indisponível");
        }

        if (saldoDebitor.getValor() < transacaoPixDto.getValorTransferencia()) {
            throw new IllegalArgumentException("Saldo indisponível");
        }

        saldoDebitor.setValor(saldoDebitor.getValor() - transacaoPixDto.getValorTransferencia());
        saldoCreditor.setValor(saldoCreditor.getValor() + transacaoPixDto.getValorTransferencia());

        saldoRepository.save(saldoDebitor);
        saldoRepository.save(saldoCreditor);
    }
}
