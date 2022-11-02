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
import com.totalPlay.app.service.ICategoriasServices;
 @Controller
 @RequestMapping("/categoria")
public class CategoriaController {
	

	@Autowired
	private ICategoriasServices categoriaService;

	@GetMapping(value = "/create")
	public String create(Model model) {
		Categorias categoria = new Categorias();
		model.addAttribute("categoria", categoria);
		
		return "Catego/Create";
	}
	
	@PostMapping(value="/insert")
	public String insert(@Valid @ModelAttribute("categoria")
	Categorias categoria, BindingResult result, Model model) {
		if(result.hasErrors()){
	
		return "Catego/create";
		}
		categoriaService.save(categoria);
		return"redirect:/categoria/listarAsync";
		
		
	
		
       	}
	
	
		//apis
	@GetMapping(value = "/api/listadoCategoria", produces = {"application/json" })
	public @ResponseBody Map<String, List<Categorias>> apiListar()
	throws InterruptedException{
		Thread.sleep(1000);
		Map<String, List<Categorias>> map = new HashMap<String, List<Categorias>>();
		map.put("data", categoriaService.getAll());
		System.out.println(categoriaService.getAll());
		return map;
	}
		
		@GetMapping(value = "/edit/{id}")
		public String edit(@PathVariable(value="id")Long id, Model model)
		{
			
				Categorias categoria = null;
    			if(id > 0) {
				
				categoria = categoriaService.getById(id);
				if(categoria == null) {
					return "redirect:/categoria/listar";			
				}
			}
			else {
				return  "redirect:/categoria/listar";
		}
	
			model.addAttribute("categoria", categoria);
			return "Catego/Create";		
		}
		
		
		@DeleteMapping(value= "/api/delete/{id}", produces = {"application/json"})
		public @ResponseBody Map<String, String> apiDelete(
				@PathVariable(value = "id")
				Long id, Model model
				){
			categoriaService.delete(id);
			
			Map<String, String>resultados= new HashMap<String, String>();
			
			resultados.put("success", "true");
			resultados.put("message", "Curso borrado correctamente");
			return  resultados;
			
				}
		
		@GetMapping(value = "/listarAsync")
		public String listar(Model model) throws InterruptedException {

			return "Catego/indexAsync";
		}

}
