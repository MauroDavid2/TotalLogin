package com.totalPlay.app.service;

import java.util.List;

import com.totalPlay.app.Entity.Categorias;


public interface ICategoriasServices {
	
	public List<Categorias>getAll();
	public void save(Categorias categorias);
	public Categorias getById(Long id);
	public void delete(Long id);
	

}
