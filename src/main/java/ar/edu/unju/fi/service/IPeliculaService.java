package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.model.Pelicula;

public interface IPeliculaService {
  public void guardarPeliculas( Pelicula pelicula);
	public void eliminarPelicula(Long id) throws Exception;
	public void modificarPelicula(Pelicula pelicula);
	public List<Pelicula> listarPeliculas(); 
	public Pelicula buscarPelicula(Long id) throws Exception; 
}