package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.model.UsuarioPeliculas;

public interface IUsuarioPeliculaService {
  public Integer verificarValoracionAnterio(Usuario usuario, Long id);
  public List<UsuarioPeliculas> listarUsuarioPeliculas();
  /*public UsuarioPeliculas eliminarUsuarioPeliculas(Long id) throws Exception;
  public UsuarioPeliculas buscarUsuarioPeliculas(Long id) throws Exception;*/
}
