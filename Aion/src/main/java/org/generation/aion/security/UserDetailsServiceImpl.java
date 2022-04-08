package org.generation.aion.security;

import org.generation.aion.model.Usuario;
import org.generation.aion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);

        usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

        return usuario.map(UserDetailsImpl::new).get();
    }
}
