package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.model.Peliculas;

public interface IPeliculasService {
  public void guardarPeliculas( Peliculas peliculas);
	public void eliminarPeliculas(Long id) throws Exception;
	public void modificarPeliculas(Peliculas peliculas);
	public List<Peliculas> listarPeliculas(); 
	public Peliculas buscarPeliculas(Long id) throws Exception; 
}
