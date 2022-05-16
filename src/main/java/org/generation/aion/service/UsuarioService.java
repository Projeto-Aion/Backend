package org.generation.aion.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.generation.aion.model.Usuario;
import org.generation.aion.model.UsuarioLogin;
import org.generation.aion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private String criptografarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(senha); //devolve senha criptografada

    }

    public Optional<Usuario>cadastrarUsuario (Usuario usuario){

        if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            return Optional.empty();

        }

        usuario.setSenha(criptografarSenha(usuario.getSenha()));

        return Optional.of(usuarioRepository.save(usuario));

    }

    private boolean compararSenhas(String senhaDigitada, String senhaDoBanco) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaDigitada, senhaDoBanco);
    }

    private String geradorBasicToken(String usuario, String senha) {

        String token = usuario + ":" + senha;

        byte[] tokenBase64 = Base64. encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(tokenBase64);

    }

    public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
        Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());


        if (usuario.isPresent()) {
            if (compararSenhas(usuarioLogin.get().getSenha(),usuario.get().getSenha())) {
                usuarioLogin.get().setId(usuario.get().getId());
                usuarioLogin.get().setNome(usuario.get().getNome());
                usuarioLogin.get().setFoto(usuario.get().getFoto());
                usuarioLogin.get().setTipo(usuario.get().getTipo());
                usuarioLogin.get().setImgfundo(usuario.get().getImgfundo());
                usuarioLogin.get().setNickname(usuario.get().getNickname());
                usuarioLogin.get().setToken(geradorBasicToken(usuarioLogin.get().getUsuario(),usuarioLogin.get().getSenha()));
                usuarioLogin.get().setSenha(usuario.get().getSenha());

                return usuarioLogin;
            }
        }
        return Optional.empty();
    }

    public Optional<Usuario> atualizarUsuario(Usuario usuario) {

        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            Optional<Usuario> buscar=usuarioRepository.findByUsuario(usuario.getUsuario());

            if (buscar.isPresent()) {
                if (buscar.get().getId() != usuario.getId())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe, tente novamente!", null);
            }

            usuario.setSenha(criptografarSenha(usuario.getSenha()));
            return Optional.of(usuarioRepository.save(usuario));
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado, tente novamente!", null);
    }

}
