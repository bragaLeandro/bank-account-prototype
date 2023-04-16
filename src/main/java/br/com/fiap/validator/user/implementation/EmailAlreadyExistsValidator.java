package br.com.fiap.validator.user.implementation;

import br.com.fiap.entity.Usuario;
import br.com.fiap.validator.user.UserValidator;
import org.springframework.stereotype.Component;

@Component
public class EmailAlreadyExistsValidator extends UserValidator {

    @Override
    public void validate(Usuario user) {
        if (this.emailAlreadyExists(user.getEmail()))
            throw new IllegalArgumentException("Email já cadastrado");
    }
    public boolean emailAlreadyExists(String email) {
        return usuarioRepository.findUsuarioByEmail(email).isPresent();
    }
}
