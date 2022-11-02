package com.totalPlay.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.totalPlay.app.Entity.Producto;


@Component
public interface ProductoRepository  extends JpaRepository<Producto,Long>{

}
