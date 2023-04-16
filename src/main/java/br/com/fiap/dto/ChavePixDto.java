package br.com.fiap.dto;

import br.com.fiap.entity.ChavePix;
import br.com.fiap.entity.Usuario;
import lombok.Data;

import java.util.UUID;

@Data
public class ChavePixDto {

    private UUID id;
    private String tipo;
    private boolean ativa;
    private UUID usuarioId;
    private String valorChave;

    public ChavePix toEntity(Usuario usuario) {
        ChavePix chavePix = new ChavePix();
        chavePix.setId(id);
        chavePix.setTipo(tipo);
        chavePix.setAtiva(ativa);
        chavePix.setUsuario(usuario);
        chavePix.setValorChave(valorChave);
        return chavePix;
    }

    public static ChavePixDto fromEntity(ChavePix chavePix) {
        ChavePixDto dto = new ChavePixDto();
        dto.setId(chavePix.getId());
        dto.setTipo(chavePix.getTipo());
        dto.setAtiva(chavePix.isAtiva());
        dto.setUsuarioId(chavePix.getUsuario().getId());
        dto.setValorChave(chavePix.getValorChave());
        return dto;
    }
}
