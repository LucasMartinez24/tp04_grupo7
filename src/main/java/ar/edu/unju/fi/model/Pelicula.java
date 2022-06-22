package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

@Component
public class Pelicula {
  private String titulo;
  private String genero;
  private String duracion;
  private String portada;
  private String descripcion;
  private long id_pelicula;
  public Pelicula(){
    
  }
  public String getTitulo() {
    return titulo;
  }
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public String getGenero() {
    return genero;
  }
  public void setGenero(String genero) {
    this.genero = genero;
  }
  public String getDuracion() {
    return duracion;
  }
  public void setDuracion(String duracion) {
    this.duracion = duracion;
  }
  public String getPortada() {
    return portada;
  }
  public void setPortada(String portada) {
    this.portada = portada;
  }
  public String getDescripcion() {
    return descripcion;
  }
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  public long getId_pelicula() {
    return id_pelicula;
  }
  public void setId_pelicula(long id_pelicula) {
    this.id_pelicula = id_pelicula;
  }
}
