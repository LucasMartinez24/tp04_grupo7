package ar.edu.unju.fi.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;
@Entity
@Component
public class ValoracionUsuarioPeliculas {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idValoracionPelis;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dni")
  private Usuario usuario;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name ="idPelis")
  private Peliculas pelis;
  private Integer valor;
  private String Comentario;
  public ValoracionUsuarioPeliculas(Long idValoracionPelis, Usuario usuario, Peliculas pelis, Integer valor, String comentario) {
    this.idValoracionPelis = idValoracionPelis;
    this.usuario = usuario;
    this.pelis = pelis;
    this.valor = valor;
    Comentario = comentario;
  }
  public String getComentario() {
    return Comentario;
  }
  public void setComentario(String comentario) {
    Comentario = comentario;
  }
  public ValoracionUsuarioPeliculas() {
  }
  public Long getIdValoracionPelis() {
    return idValoracionPelis;
  }
  public void setIdValoracionPelis(Long idValoracionPelis) {
    this.idValoracionPelis = idValoracionPelis;
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
  public Integer getValor() {
    return valor;
  }
  public void setValor(Integer valor) {
    this.valor = valor;
  }
}
