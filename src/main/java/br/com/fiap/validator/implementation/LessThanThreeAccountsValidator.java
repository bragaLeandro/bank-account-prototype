package br.com.fiap.validator.implementation;

import br.com.fiap.entity.Usuario;
import br.com.fiap.validator.UserValidator;
import org.springframework.stereotype.Component;

@Component
public class LessThanThreeAccountsValidator extends UserValidator {

    @Override
    public void validate(Usuario user) {
        if (this.hasThreeAccounts(user.getCpf()))
            throw new IllegalArgumentException("Não é possível ter mais de 3 contas para o mesmo cpf");
    }
    public boolean hasThreeAccounts(String cpf) {
        return usuarioRepository.findUsuariosByCpf(cpf).size() >= 3;
    }
}
