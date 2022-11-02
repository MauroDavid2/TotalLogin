package com.totalPlay.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.totalPlay.app.Entity.Usuario;
import com.totalPlay.app.dao.IUsuarioDAO;

@Service
public class UserDetailsServiceimpl implements UserDetailsService {
//usa para autentificar lo que es el usuario
	@Autowired
	private IUsuarioDAO usuarioDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//aqui almacena el usuario
		Usuario  usuario = usuarioDao.findByUsername(username);
		UserBuilder builder = null;
		
		if(usuario != null) {
			//si el usuario es diferent
			builder =  User.withUsername(username);
			//habilita el disable
			builder.disabled(false);
			//le pasamos el password del usuario
			builder.password(usuario.getPassword());
			//aqui se le pasa la autoridad  para dar accebilidad a los usuarios
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
			
		}else {
			//si no lo encuentra se manda aqui
			throw new UsernameNotFoundException ("usuario no encontrado");
		}
		
		//retorna  y construya este user;
		return builder.build();
	}

}
