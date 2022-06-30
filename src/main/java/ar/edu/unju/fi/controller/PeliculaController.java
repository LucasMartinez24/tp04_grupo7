package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ar.edu.unju.fi.model.Peliculas;
import ar.edu.unju.fi.service.ICursoService;
import ar.edu.unju.fi.util.ListaPeli;

@Controller
public class PeliculaController {
  private static final Log LUCAS=LogFactory.getLog(UsuarioController.class);

  @Autowired
  Peliculas nuevoCurso;
  
  @Autowired
  ListaPeli list;
  
  @Autowired
  ICursoService cursoService;

  @GetMapping("/formulariopeliculas")
  public ModelAndView addPeli(){
    ModelAndView vista= new ModelAndView("formulariopeliculas");
    vista.addObject("Peli", nuevoCurso);
    vista.addObject("editMode",false);
    return vista;
  }
  @PostMapping("/formulariopeliculas")
  public String savePeli(@Valid @ModelAttribute("Peli") Peliculas peliculas, BindingResult resultado, Model model){
    LUCAS.info(resultado.getFieldError());
    if (resultado.hasErrors()) {
      LUCAS.fatal("Error de validación en guardar peli");
      model.addAttribute("Peli", peliculas);
      return "formulariopeliculas";
    }
    try {
      cursoService.guardarCurso(peliculas);
    } catch (Exception e) {
      model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("unaPeli", peliculas);
			LUCAS.error("saliendo del metodo");
			return "formulariopeliculas";	
    }
    model.addAttribute("formUsuarioErrorMessage", "curso guardado correctamente");
		model.addAttribute("unaPeli", peliculas);			
		return "formulariopeliculas";
  }
  @GetMapping("/listapeliculas")
  public ModelAndView getlista(){
    ModelAndView vista = new ModelAndView("ListadoPe");
    vista.addObject("listaPelis", cursoService.listarCursos());
    LUCAS.info("Ingresando al metodo listar Pelis");
    return vista;
  }
  @GetMapping("/editarPeli/{id}")
  public ModelAndView editPeli(@PathVariable(name="id") Long id) throws Exception{
    Peliculas peliculaencontrada = new Peliculas();
    peliculaencontrada=cursoService.buscarCurso(id);    
    ModelAndView encontrado = new ModelAndView("formulariopeliculas");
    encontrado.addObject("Peli", peliculaencontrada);
    LUCAS.fatal("Saliendo del metodo encontrado");
    encontrado.addObject("editMode",true);
    return encontrado;
  }
  @PostMapping("/modificarpeliculas")
  public ModelAndView modPeli(@ModelAttribute("peli") Peliculas peliculas){
    cursoService.modificarCurso(peliculas);;
    ModelAndView vista = new ModelAndView("ListadoPe");
    vista.addObject("listaPelis", cursoService.listarCursos());
    vista.addObject("formUsuarioErrorMessage","Peli Guardada Correctamente");
    return vista;
  }
  @GetMapping("/eliminarPeli/{id}")
	public String eliminar(@PathVariable Long id, Model model) {	
		try {
		cursoService.eliminarCurso(id);
		}catch(Exception e) {
			LUCAS.error("encontrando curso");
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			return "redirect:/formulariopeliculas";			
		}
		return "redirect:/listapeliculas";
	}
}
