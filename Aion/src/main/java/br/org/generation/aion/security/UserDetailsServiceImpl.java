package br.org.generation.aion.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.generation.aion.model.Usuario;
import br.org.generation.aion.repository.UsuarioRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);
	
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " Este usuario não foi encontrado"));
		
		return usuario.map(UserDetailsImpl::new).get();
	}

