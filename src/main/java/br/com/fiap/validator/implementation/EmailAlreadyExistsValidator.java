package br.com.fiap.validator.implementation;

import br.com.fiap.entity.Usuario;
import br.com.fiap.service.UsuarioService;
import br.com.fiap.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
