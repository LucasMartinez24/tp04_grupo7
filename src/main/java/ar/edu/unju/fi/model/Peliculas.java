package ar.edu.unju.fi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.GenerationType;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "Peliculas")
public class Peliculas {
  @Id
  @GeneratedValue
  (strategy=GenerationType.IDENTITY)
  @Column (name = "idPelis", nullable = true)
  private Long id;
  @Size(min=3, max=100, message="EL nombre debe tener 3 caracteres minimo, maximo 100")
	@NotEmpty(message="El nombre no puede estar vacio")
  private String titulo;
  @NotEmpty
  @Min(value=1000000,message = "El DNI debe ser mayor al millon")
  @Max(value = 999999999, message = "El DNI debe ser menor a 999999999")
  private String genero;
  @NotEmpty
  @Min(value=1000000,message = "El DNI debe ser mayor al millon")
  @Max(value = 999999999, message = "El DNI debe ser menor a 999999999")
  private String duracion;
  @Lob
  private String portada;
  @NotEmpty
  @Min(value=1000000,message = "El DNI debe ser mayor al millon")
  @Max(value = 999999999, message = "El DNI debe ser menor a 999999999")
  private String descripcion;
  public Peliculas(Long id,
      @Size(min = 3, max = 100, message = "EL nombre debe tener 3 caracteres minimo, maximo 100") @NotEmpty(message = "El nombre no puede estar vacio") String titulo,
      @NotEmpty @Min(value = 1000000, message = "El DNI debe ser mayor al millon") @Max(value = 999999999, message = "El DNI debe ser menor a 999999999") String genero,
      @NotEmpty @Min(value = 1000000, message = "El DNI debe ser mayor al millon") @Max(value = 999999999, message = "El DNI debe ser menor a 999999999") String duracion,
      @NotEmpty @Min(value = 1000000, message = "El DNI debe ser mayor al millon") @Max(value = 999999999, message = "El DNI debe ser menor a 999999999") String portada,
      @NotEmpty @Min(value = 1000000, message = "El DNI debe ser mayor al millon") @Max(value = 999999999, message = "El DNI debe ser menor a 999999999") String descripcion) {
        super();
    this.id = id;
    this.titulo = titulo;
    this.genero = genero;
    this.duracion = duracion;
    this.portada = portada;
    this.descripcion = descripcion;
  }
  public Peliculas() {
    
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
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
  
}
