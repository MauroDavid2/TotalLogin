package com.totalPlay.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.totalPlay.app.Entity.Usuario;
import com.totalPlay.app.dao.IUsuarioDAO;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
	
	
@Autowired
private IUsuarioDAO usuarioDao;
//usamos para encriptar la contraseña
@Autowired
private BCryptPasswordEncoder passwordEncoder;

	

	@Override
	public Usuario registrar(Usuario u) {
		// TODO Auto-generated method stub
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		
		return usuarioDao.save(u);
	}



	@Override
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return usuarioDao.findByUsername(username);
	}

}
