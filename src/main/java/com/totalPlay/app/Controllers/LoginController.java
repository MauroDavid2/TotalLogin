package com.totalPlay.app.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.totalPlay.app.Entity.Usuario;
import com.totalPlay.app.dao.IUsuarioDAO;
import com.totalPlay.app.service.IUsuarioService;

@Controller
public class LoginController {

	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IUsuarioDAO UsuarioRepo;
	
	
	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("usuario",new Usuario());
		return "login";
	}
	
	@GetMapping("/auth/registro")
	public String registroForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		
		return "registro";
	}
	
	@PostMapping("/auth/registro")
	public String Registro(@Valid @ModelAttribute Usuario usuario, BindingResult result,Model model) {
		Usuario use= UsuarioRepo.findByUsername(usuario.getUsername());
		if(result.hasErrors()) {
			return "redirect:/auth/registro";
		}else {
			model.addAttribute("usuario",usuarioService.registrar(usuario));
			
		}
		return "redirect:/auth/login";
		
	}
}
