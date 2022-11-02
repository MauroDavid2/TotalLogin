package com.totalPlay.app.service;

import java.util.List;

import com.totalPlay.app.Entity.Categorias;
import com.totalPlay.app.Entity.Producto;



public interface IProductoService {

	public List<Producto> getAll();
	
	public void save (Producto producto);
	public Producto getById(Long id);
	public void delete(Long id);
	
	public void save(Categorias categoria);
	public List<Categorias> getListCategorias();
	public Categorias GetCategoriaById(Long id);
	
}
