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

import ar.edu.unju.fi.model.Pelicula;
import ar.edu.unju.fi.service.IPeliculaService;
import ar.edu.unju.fi.util.ListaPelicula;

@Controller
public class PeliculaController {
  private static final Log Grupo7 = LogFactory.getLog(UsuarioController.class);

  @Autowired
  Pelicula nuevaPelicula;

  @Autowired
  ListaPelicula list;

  @Autowired
  IPeliculaService peliculaService;

  @GetMapping("/formularioPelicula")
  public ModelAndView addPelicula() {
    ModelAndView vista = new ModelAndView("formulariopelicula");
    vista.addObject("pelicula", nuevaPelicula);
    vista.addObject("editMode", false);
    return vista;
  }

  @PostMapping("/formularioPelicula")
  public String saveUser(@Valid @ModelAttribute("pelicula") Pelicula pelicula, BindingResult resultado, Model model) {
    if (resultado.hasErrors()) {
      Grupo7.fatal("Error de validación en guardar pelicula");
      model.addAttribute("pelicula", pelicula);
      return "formulariopelicula";
    }
    try {
      peliculaService.guardarPeliculas(pelicula);
    } catch (Exception e) {
      model.addAttribute("formUsuarioErrorMessage", e.getMessage());
      model.addAttribute("unaPelicula", pelicula);
      Grupo7.error("saliendo del metodo");
      return "formulariopelicula";
    }
    model.addAttribute("formUsuarioErrorMessage", "curso guardado correctamente");
    model.addAttribute("unaPelicula", pelicula);
    return "formulariopelicula";
  }

  @GetMapping("/listapelicula")
  public ModelAndView getlista() {
    ModelAndView vista = new ModelAndView("listadopelicula");
    vista.addObject("listaPelicula", peliculaService.listarPeliculas());
    Grupo7.info("ingresando al metodo listar pelicula");
    return vista;
  }

  @GetMapping("/editarPelicula/{id}")
  public ModelAndView edituser(@PathVariable(name = "id") Long id) throws Exception {
    Pelicula peliculaencontrado = new Pelicula();
    peliculaencontrado = peliculaService.buscarPelicula(id);
    ModelAndView encontrado = new ModelAndView("formulariopelicula");
    encontrado.addObject("pelicula", peliculaencontrado);
    Grupo7.fatal("Saliendo del metodo encontrando pelicula");
    encontrado.addObject("editmode", true);
    return encontrado;
  }

  @PostMapping("/modificarPelicula")
  public ModelAndView modUser(@ModelAttribute("Pelicula") Pelicula pelicula) {
    peliculaService.modificarPelicula(pelicula);
    ;
    ModelAndView vista = new ModelAndView("Listadopelicula");
    vista.addObject("listaPelicula", peliculaService.listarPeliculas());
    vista.addObject("formUsuarioErrorMessage", "curso Guardado Correctamente");
    return vista;
  }

  @GetMapping("/eliminarpelicula/{id}")
  public String eliminar(@PathVariable Long id, Model model){
      try {
        peliculaService.eliminarPelicula(id);
      }catch(Exception e) {
        Grupo7.error("encontrando pelicula");
        model.addAttribute("formUsuarioerrorMessage", e.getMessage());
        return "redirect:/formulariopelicula";
      }
      return "redirect:/listapelicula";
  }
}