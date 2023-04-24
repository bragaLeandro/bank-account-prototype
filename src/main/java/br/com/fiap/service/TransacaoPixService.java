package br.com.fiap.service;

import br.com.fiap.dto.TransacaoPixDto;
import br.com.fiap.entity.Saldo;
import br.com.fiap.entity.TransacaoPix;
import br.com.fiap.entity.Usuario;
import br.com.fiap.repository.ChavePixRepository;
import br.com.fiap.repository.SaldoRepository;
import br.com.fiap.repository.TransacaoPixRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransacaoPixService {

    private final UsuarioService usuarioService;
    private final SaldoRepository saldoRepository;
    private final ChavePixRepository chavePixRepository;
    private final TransacaoPixRepository transacaoPixRepository;

    public TransacaoPixService(UsuarioService usuarioService, SaldoRepository saldoRepository, ChavePixRepository chavePixRepository, TransacaoPixRepository transacaoPixRepository) {
        this.usuarioService = usuarioService;
        this.saldoRepository = saldoRepository;
        this.chavePixRepository = chavePixRepository;
        this.transacaoPixRepository = transacaoPixRepository;
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

        TransacaoPix transacaoPix = new TransacaoPix(usuarioDebitor, usuarioCreditor, "Success", transacaoPixDto.getValorTransferencia());

        List<Saldo> modifiedBalances = Arrays.asList(saldoDebitor, saldoCreditor);

        transacaoPixRepository.save(transacaoPix);
        saldoRepository.saveAll(modifiedBalances);
    }

    public List<TransacaoPix> getHistoryTransactions(UUID userUuid) {
        List<TransacaoPix> transactions = new ArrayList<TransacaoPix>();
        Usuario user = usuarioService.findById(userUuid);

        List<TransacaoPix> creditor = this.transacaoPixRepository.findTransacaoPixesByCreditor(user);
        List<TransacaoPix> debitor = this.transacaoPixRepository.findTransacaoPixesByDebitor(user);

        if (!creditor.isEmpty()) transactions.addAll(creditor);
        if (!debitor.isEmpty()) transactions.addAll(debitor);

        return transactions;
    }
}
