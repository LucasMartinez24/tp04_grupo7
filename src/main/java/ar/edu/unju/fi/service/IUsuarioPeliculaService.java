package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.model.UsuarioPeliculas;

public interface IUsuarioPeliculaService {
  public List<UsuarioPeliculas> listarUsuarioPeliculas(Usuario usuario);
  public void agregarentrada(UsuarioPeliculas usuarioPeliculas);
  public Boolean existe(UsuarioPeliculas usuarioPeliculas);
}
