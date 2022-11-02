package com.totalPlay.app.service;

import com.totalPlay.app.Entity.Usuario;

public interface IUsuarioService {
	
	 public Usuario findByUsername(String username);
	 public Usuario registrar(Usuario u);
}
