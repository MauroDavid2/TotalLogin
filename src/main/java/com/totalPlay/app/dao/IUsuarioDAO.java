package com.totalPlay.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.totalPlay.app.Entity.Usuario;

@Repository
public interface IUsuarioDAO  extends JpaRepository<Usuario,Long>{
	
	//hacer la busqueda por este campo
 public Usuario findByUsername(String username);
}
