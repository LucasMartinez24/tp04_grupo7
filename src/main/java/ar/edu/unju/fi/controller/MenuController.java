package ar.edu.unju.fi.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;

@Controller
public class MenuController {
  private static final Log grupo7=LogFactory.getLog(MenuController.class);
  @Autowired
  Usuario listaPersona;
  @Autowired
  IUsuarioService usuarioService;
  @GetMapping({"/index","/home","/"})
  public String getmenu(){
    return "index";
  }
  @GetMapping("/login")
  public ModelAndView getlogin(){
    grupo7.info("bienvenido");
    ModelAndView vista= new ModelAndView("Login");
    vista.addObject("usuario1", listaPersona);
    return vista;
  }
  @PostMapping("/formulario")
  public String saveUser(@Valid @ModelAttribute("usuario1") Usuario user, BindingResult resultado, Model model){
    grupo7.info("registro");
    if (resultado.hasErrors()) {
      grupo7.fatal("Error de validación");
      model.addAttribute("usuario1", user);
      return "Login";
    }
    try {
      usuarioService.guardarUsuario(user);
      grupo7.info("guardado correctamente");
    } catch (Exception e) {
			model.addAttribute("unUsuario", user);
			grupo7.error("saliendo del metodo");
			return "Login";	
    }
		model.addAttribute("unUsuario", user);			
		return "Login";
  }
}
