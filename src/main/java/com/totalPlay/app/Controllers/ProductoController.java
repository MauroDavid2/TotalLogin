package com.totalPlay.app.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.totalPlay.app.Entity.Categorias;
import com.totalPlay.app.Entity.Producto;
import com.totalPlay.app.service.IProductoService;

@Controller
@RequestMapping("/Productos")
public class ProductoController {
@Autowired
private IProductoService productoService;

@GetMapping(value = "/create")
public String create(Model model) {
	Producto producto= new Producto();
	model.addAttribute("producto", producto);
	List<Categorias> categorias = productoService.getListCategorias();
	model.addAttribute("categorias", categorias);
	return "Produc/Create";
}


@PostMapping(value = "/insert")
public String insert(@Valid @ModelAttribute("producto")
Producto producto,
BindingResult result,
Model model) {
	if(producto.getCategorias().getId()==null) {
		FieldError error = new FieldError("Producto",
				"categoria",
				"Debes seleccionar una categoria para el producto");
		result.addError(error);
	}
	if(result.hasErrors()) {
		model.addAttribute("categorias",
				productoService.getListCategorias());
		return"Produc/Create";
	}
	productoService.save(producto);
	
	Categorias categoria= productoService.GetCategoriaById(producto.getCategorias().getId());
	
	productoService.save(categoria);
	return "redirect:/Productos/listarAsync";
}

@GetMapping(value = "/listarAsync")
public String listar(Model model) throws InterruptedException {

	return "Produc/indexAsync";
}


@GetMapping(value = "/api/listadoProducto",
produces = {"application/json"})
public @ResponseBody Map<String, List<Producto>> apiListar() 
throws InterruptedException{
Thread.sleep(1500);
Map<String, List<Producto>> map = new HashMap<String, List<Producto>>();
map.put("data", productoService.getAll());
System.out.println(productoService.getAll());
return map;
}


@DeleteMapping(value= "/api/delete/{id}", produces = {"application/json"})
public @ResponseBody Map<String, String> apiDelete(
		@PathVariable(value = "id")
		Long id, Model model
		){
	productoService.delete(id);
	
	Map<String, String>resultados= new HashMap<String, String>();
	
	resultados.put("success", "true");
	resultados.put("message", "Curso borrado correctamente");
	return  resultados;
	
		}


@GetMapping(value = "/edit/{id}")
public String edit(@PathVariable(value = "id") Long id,
		Model model)
{
	List<Categorias>categoria = productoService.getListCategorias();
	Producto producto = null;
	if(id > 0) {
		//recuperamos el objeto categoria que quereos editar
		producto = productoService.getById(id);
		if(producto == null) {
			return "redirect:/Productos/listar";
		}
	}
	else {
		return "redirect:/Productos/listar";
	}
	//le pasamos el objeto categoria
	model.addAttribute("producto", producto);
	model.addAttribute("categorias", categoria);
	return "Produc/Create";
}

	
}
