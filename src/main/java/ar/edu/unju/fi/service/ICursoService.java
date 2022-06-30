package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.model.Peliculas;

public interface ICursoService {
  public void guardarCurso( Peliculas curso);
	public void eliminarCurso(Long id) throws Exception;
	public void modificarCurso(Peliculas curso);
	public List<Peliculas> listarCursos(); 
	public Peliculas buscarCurso(Long id) throws Exception; 
}
