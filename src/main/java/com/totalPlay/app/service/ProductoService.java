package com.totalPlay.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalPlay.app.Entity.Categorias;
import com.totalPlay.app.Entity.Producto;
import com.totalPlay.app.dao.CategoriasRepository;
import com.totalPlay.app.dao.ProductoRepository;



@Component
public class ProductoService implements IProductoService {
@Autowired
private ProductoRepository ProductoRepo;

@Autowired
private CategoriasRepository CategoriaRepo;
	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return (List<Producto>)ProductoRepo.findAll();
	}

	@Override
	public void save(Producto producto) {
		// TODO Auto-generated method stub
		ProductoRepo.save(producto);
	}

	@Override
	public Producto getById(Long id) {
		// TODO Auto-generated method stub
		return ProductoRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		ProductoRepo.deleteById(id);
	}

	@Override
	public void save(Categorias categoria) {
		// TODO Auto-generated method stub
			CategoriaRepo.save(categoria);
	}

	@Override
	public List<Categorias> getListCategorias() {
		// TODO Auto-generated method stub
		return (List<Categorias>)CategoriaRepo.findAll();
	}

	@Override
	public Categorias GetCategoriaById(Long id) {
		// TODO Auto-generated method stub
		return CategoriaRepo.findById(id).orElse(null);
	}

}
