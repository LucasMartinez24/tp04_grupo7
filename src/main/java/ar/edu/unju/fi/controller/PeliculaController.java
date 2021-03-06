package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ar.edu.unju.fi.model.Peliculas;
import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.model.UsuarioPeliculas;
import ar.edu.unju.fi.model.ValoracionUsuarioPeliculas;
import ar.edu.unju.fi.repository.UsuarioPeliculasRepository;
import ar.edu.unju.fi.service.IPeliculasService;
import ar.edu.unju.fi.service.IUsuarioPeliculaService;
import ar.edu.unju.fi.service.IUsuarioService;
import ar.edu.unju.fi.service.ValoracionUsuarioPeliculasService;
import ar.edu.unju.fi.util.ListaPeli;

@Controller
public class PeliculaController {
  private static final Log LUCAS = LogFactory.getLog(PeliculaController.class);

  @Autowired
  Peliculas peliculas;

  @Autowired
  ListaPeli list;

  @Autowired
  IPeliculasService peliculasService;
  @Autowired
  IUsuarioPeliculaService usuarioPeliculaService;
  @Autowired
  IUsuarioService usuarioService;
  @Autowired
  UsuarioPeliculasRepository usuarioPeliculasRepository;
  @Autowired
  ValoracionUsuarioPeliculasService valoracionUsuarioPeliculasService;
  @GetMapping("/formulariopeliculas")
  public ModelAndView addPeli() {
    ModelAndView vista = new ModelAndView("formulariopeliculas");
    vista.addObject("Peli", peliculas);
    vista.addObject("editMode",false);
    return vista;
  }

  @PostMapping(value="/formulariopeliculas",consumes= "multipart/form-data")
  public String savePeli(@Valid @ModelAttribute("Peli") Peliculas peliculas,BindingResult resultado,@RequestParam("file") MultipartFile file, Model model) {
    LUCAS.info(peliculas.getId());
    if (resultado.hasErrors()) {
      LUCAS.fatal("Error de validaci??n en guardar peli");
      model.addAttribute("Peli", peliculas);
      return "formulariopeliculas";
    }
    try {
      LUCAS.info("Guardando...");
      byte[] content = file.getBytes();
      String base64 = Base64.getEncoder().encodeToString(content);
      peliculas.setPortada(base64);
      peliculas.setEstado(true);
      peliculasService.guardarPeliculas(peliculas);
    } catch (Exception e) {
      model.addAttribute("formUsuarioErrorMessage", e.getMessage());
      model.addAttribute("unaPeli", peliculas);
      LUCAS.error("saliendo del metodo");
      return "formulariopeliculas";
    }
    model.addAttribute("formUsuarioErrorMessage", "curso guardado correctamente");
    model.addAttribute("unaPeli", peliculas);
    return "redirect:/listapeliculas2";
  }
  @GetMapping("/listapeliculas")
  public ModelAndView getlista() throws Exception {
    ModelAndView vista = new ModelAndView("ListadoPe");
    vista.addObject("listaPelis", peliculasService.listarPeliculas());
    LUCAS.info("Ingresando al metodo listar Pelis");
    return vista;
  }
  @GetMapping("/listapeliculas2")
  public ModelAndView getlista2() {
    ModelAndView vista = new ModelAndView("ListadoPe2");
    vista.addObject("listaPelis", peliculasService.listarPeliculas());
    LUCAS.info("Ingresando al metodo listar Pelis");
    return vista;
  }

  @GetMapping("/editarPeli/{id}")
  public ModelAndView editPeli(@PathVariable(name = "id") Long id) throws Exception {
    Peliculas peliculaencontrada = new Peliculas();
    peliculaencontrada = peliculasService.buscarPeliculas(id);
    ModelAndView encontrado = new ModelAndView("formulariopeliculas");
    encontrado.addObject("Peli", peliculaencontrada);
    LUCAS.fatal("Saliendo del metodo encontrado pelis ");
    encontrado.addObject("editMode", true);
    return encontrado;
  }

  @PostMapping(value="/modificarpeliculas",consumes= "multipart/form-data")
  public ModelAndView modPeli(@ModelAttribute("Peli") Peliculas peliculas,@RequestParam("file") MultipartFile file, Model model) {
    ModelAndView vista2 = new ModelAndView("formulariopeliculas");
    try{
      byte[] content = file.getBytes();
      String base64 = Base64.getEncoder().encodeToString(content);
      peliculas.setPortada(base64);
      peliculasService.modificarPeliculas(peliculas);
    }catch(Exception e){
      model.addAttribute("formUsuarioErrorMessage", e.getMessage());
      model.addAttribute("unaPeli", peliculas);
      LUCAS.error("saliendo del metodo");
      return vista2;
    }
    ModelAndView vista = new ModelAndView("ListadoPe2");
    vista.addObject("listaPelis", peliculasService.listarPeliculas());
    vista.addObject("formUsuarioErrorMessage", "Peli Guardada Correctamente");
    return vista;
  }

  @GetMapping("/eliminarPeli/{id}")
  public String eliminar(@PathVariable Long id, Model model) {
    try {
      peliculasService.eliminarPeliculas(id);
    } catch (Exception e) {
      LUCAS.error("encontrando curso");
      model.addAttribute("formUsuarioErrorMessage", e.getMessage());
      return "redirect:/formulariopeliculas";
    }
    return "redirect:/listapeliculas2";
  }
  @GetMapping("/descripcion/{id}")
  public ModelAndView descrip(@PathVariable(name = "id") Long id) throws Exception{
    Peliculas peliculaencontrada = new Peliculas();
    ValoracionUsuarioPeliculas valoracionUsuarioPeliculas =new ValoracionUsuarioPeliculas();
    ModelAndView encontrado = new ModelAndView("pelicula");
    peliculaencontrada = peliculasService.buscarPeliculas(id);
    encontrado.addObject("movie", peliculaencontrada);
    Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
    Usuario userEnSesion = usuarioService.buscarUsuario(Long.parseLong(userDetail.getUsername()));
    LUCAS.info(userEnSesion.getDni());
    valoracionUsuarioPeliculas.setUsuario(userEnSesion);
    valoracionUsuarioPeliculas.setPelis(peliculaencontrada);
    encontrado.addObject("valoracion", valoracionUsuarioPeliculas);
    encontrado.addObject("listacomentarios", valoracionUsuarioPeliculasService.ListarValoracion(peliculaencontrada));
    encontrado.addObject("promedio", valoracionUsuarioPeliculasService.promedio(valoracionUsuarioPeliculasService.ListarValoracion(peliculaencontrada)));
    LUCAS.info(peliculaencontrada.getDescripcion());
    LUCAS.fatal("Saliendo del metodo encontrado pelis ");
    return encontrado;
  }
  @PostMapping("/valorar")
  public String valorar(@ModelAttribute("valoracion") ValoracionUsuarioPeliculas unaValoracion) throws Exception{
    LUCAS.info(unaValoracion.getUsuario());
    valoracionUsuarioPeliculasService.guardarValoracion(unaValoracion);
    return "redirect:/listapeliculas";
  }
  @GetMapping("/entradas")
  public ModelAndView MisEntradas() throws Exception{
    ModelAndView vista= new ModelAndView("misEntradas");
    Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
    Usuario userEnSesion = usuarioService.buscarUsuario(Long.parseLong(userDetail.getUsername()));
    vista.addObject("entradas", usuarioPeliculaService.listarUsuarioPeliculas(userEnSesion));
    LUCAS.info("Ingresando al metodo listar entradas");
    return vista;
  }
  @GetMapping("/comprarEntrada/{id}")
  public String comprarar(@PathVariable Long id, Model model) throws Exception {
    Peliculas peliculas=new Peliculas();
    UsuarioPeliculas usuarioPeliculas=new UsuarioPeliculas();
    peliculas=peliculasService.buscarPeliculas(id);
    Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
    Usuario userEnSesion = usuarioService.buscarUsuario(Long.parseLong(userDetail.getUsername()));
    usuarioPeliculas.setPelis(peliculas);
    usuarioPeliculas.setUsuario(userEnSesion);
    usuarioPeliculaService.agregarentrada(usuarioPeliculas);
    return "redirect:/entradas";
  }
}

