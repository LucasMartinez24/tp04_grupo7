package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.model.Peliculas;
import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.model.ValoracionUsuarioPeliculas;

public interface ValoracionUsuarioPeliculasService {
  public void guardarValoracion(ValoracionUsuarioPeliculas valoracion) throws Exception;
  public List<ValoracionUsuarioPeliculas> ListarValoracion(Peliculas peliculas);
  public Boolean Existe(Usuario usuario) throws Exception;
  public Long promedio(List<ValoracionUsuarioPeliculas> lista);
}
