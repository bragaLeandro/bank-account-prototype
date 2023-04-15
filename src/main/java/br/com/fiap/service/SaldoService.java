package br.com.fiap.service;

import br.com.fiap.entity.Saldo;
import br.com.fiap.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public class SaldoService {

    public Saldo setInicialBalance(Usuario user) {
        Saldo inicialBalance = new Saldo();
        inicialBalance.setUsuario(user);
        inicialBalance.setValor(100);
        return inicialBalance;
    }
}
