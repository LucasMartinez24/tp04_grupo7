package ar.edu.unju.fi.service;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.model.UsuarioPeliculas;

public interface IUsuarioPeliculaService {
  public void guardarValoracion(UsuarioPeliculas unaValoracion);
	public UsuarioPeliculas crearValoracion();
  public Integer verificarValoracionAnterio(Usuario turista, Long id);
}
