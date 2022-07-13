package ar.edu.unju.fi.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class UsuarioPeliculas {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idUsuarioPeli;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dni")
  private Usuario usuario;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name ="idPelis")
  private Peliculas pelis;
  public UsuarioPeliculas(Long idUsuarioPeli, Usuario usuario, Peliculas pelis) {
    this.idUsuarioPeli = idUsuarioPeli;
    this.usuario = usuario;
    this.pelis = pelis;
  }
  public UsuarioPeliculas() {
  }
  public Long getIdUsuarioPeli() {
    return idUsuarioPeli;
  }
  public void setIdUsuarioPeli(Long idUsuarioPeli) {
    this.idUsuarioPeli = idUsuarioPeli;
  }
  public Usuario getUsuario() {
    return usuario;
  }
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  public Peliculas getPelis() {
    return pelis;
  }
  public void setPelis(Peliculas pelis) {
    this.pelis = pelis;
  }
}
