package br.com.fiap.validator.user;

import br.com.fiap.entity.Usuario;
import br.com.fiap.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class UserValidator {

    @Autowired
    protected UsuarioRepository usuarioRepository;

    public abstract void validate(Usuario user);
}
