package ar.edu.unju.fi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
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
  @Size(min= 3,max = 100, message = "El genero debe tener mas de 3 caracteres")
  private String genero;
  @NotEmpty
  @Size(min=3,max = 100, message = "La duracion tiene que tener mas de 3 caracteres")
  private String duracion;
  @Lob
  private String portada;
  @NotEmpty
  @Size(min = 3,message = "La descripcion no puede ser menor a 3 caracteres")
  private String descripcion;
  @NotEmpty
  private Long sala;
  @NotEmpty
  private Boolean estado;
  public Peliculas(Long id,
      @Size(min = 3, max = 100, message = "EL nombre debe tener 3 caracteres minimo, maximo 100") @NotEmpty(message = "El nombre no puede estar vacio") String titulo,
      @NotEmpty @Size(min = 3, max = 100, message = "El genero debe tener mas de 3 caracteres") String genero,
      @NotEmpty @Size(min = 3, max = 100, message = "La duracion tiene que tener mas de 3 caracteres") String duracion,
      String portada,
      @NotEmpty @Size(min = 3, message = "La descripcion no puede ser menor a 3 caracteres") String descripcion,
      @NotEmpty Long sala, @NotEmpty Boolean estado) {
    this.id = id;
    this.titulo = titulo;
    this.genero = genero;
    this.duracion = duracion;
    this.portada = portada;
    this.descripcion = descripcion;
    this.sala = sala;
    this.estado = estado;
  }
  public Boolean getEstado() {
    return estado;
  }
  public void setEstado(Boolean estado) {
    this.estado = estado;
  }
  public Long getSala() {
    return sala;
  }
  public void setSala(Long sala) {
    this.sala = sala;
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
