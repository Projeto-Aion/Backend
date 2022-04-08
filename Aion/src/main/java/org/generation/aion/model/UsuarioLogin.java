package org.generation.aion.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLogin {

    private Long id;

    private String nome;

    private String usuario;

    private String nickname;

    private String foto;

    private String senha;

    private String telefone;

    private String token;
}
