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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;
import ar.edu.unju.fi.util.Lista;

@Controller
public class UsuarioController {
  private static final Log LUCAS=LogFactory.getLog(UsuarioController.class);

  @Autowired
  Usuario listaPersona;
  
  @Autowired
  Lista list;
  
  @Autowired
  IUsuarioService usuarioService;

 /* @GetMapping("/formulario")
  public ModelAndView addUsuario(){
    ModelAndView vista= new ModelAndView();
    vista.addObject("Login");
    vista.addObject("usuario1", listaPersona);
    return vista;
  } */
  
  @GetMapping("/lista")
  public ModelAndView getlista(){
    ModelAndView vista = new ModelAndView("Listado");
    vista.addObject("listaUsuario", usuarioService.listarUsuarios());
    LUCAS.info("Ingresando al metodo");
    return vista;
  }
  @GetMapping("/editarUsuario/{dni}")
  public ModelAndView edituser(@PathVariable(name="dni") Long dni) throws Exception{
    Usuario usuarioencontrado = new Usuario();
    usuarioencontrado=usuarioService.buscarUsuario(dni);    
    ModelAndView encontrado = new ModelAndView("formulario");
    encontrado.addObject("usuario1", usuarioencontrado);
    LUCAS.fatal("Saliendo del metodo encontrado");
    encontrado.addObject("editMode",true);
    return encontrado;
  }
  @PostMapping("/modificarusuario")
  public ModelAndView modUser(@ModelAttribute("usuario1") Usuario user){
    usuarioService.modificarUsuario(user);
    ModelAndView vista = new ModelAndView("Listado");
    vista.addObject("listaUsuario", usuarioService.listarUsuarios());
    vista.addObject("formUsuarioErrorMessage","Usuario Guardado Correctamente");
    return vista;
  }
  @GetMapping("/eliminarUsuario/{id}")
	public String eliminar(@PathVariable Long id, Model model) {	
		try {
		usuarioService.eliminarUsuario(id);
		}catch(Exception e) {
			LUCAS.error("encontrando");
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			return "redirect:/formulario";			
		}
		return "redirect:/lista";
	}
}
