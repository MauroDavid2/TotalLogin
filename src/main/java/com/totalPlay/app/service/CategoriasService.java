package com.totalPlay.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalPlay.app.Entity.Categorias;
import com.totalPlay.app.dao.CategoriasRepository;


@Component
public class CategoriasService  implements ICategoriasServices{

	@Autowired
	CategoriasRepository CategoriasRepo;
	
	@Override
	public List<Categorias> getAll() {
		// TODO Auto-generated method stub
		return (List<Categorias>)CategoriasRepo.findAll();
	}

	@Override
	public void save(Categorias categorias) {
		// TODO Auto-generated method stub
		CategoriasRepo.save(categorias);
	}

	@Override
	public Categorias getById(Long id) {
		// TODO Auto-generated method stub
		return CategoriasRepo.findById(id).orElse(null);
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		CategoriasRepo.deleteById(id);
	}

}
