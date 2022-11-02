package com.totalPlay.app.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="Catego")
public class Categorias {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull(message="")
	private String Descripcion;
	
	
	@JsonBackReference
	@OneToMany(mappedBy= "categorias", cascade = CascadeType.ALL)
	private Set<Producto> productos= new HashSet<>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescripcion() {
		return Descripcion;
	}


	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}


	public Set<Producto> getProductos() {
		return productos;
	}


	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}


	public Categorias(Long id, @NotNull(message = "") String descripcion, Set<Producto> productos) {
		super();
		this.id = id;
		Descripcion = descripcion;
		this.productos = productos;
	}


	public Categorias() {
		super();
	}
	
	
	
}
