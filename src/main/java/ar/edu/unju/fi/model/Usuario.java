package ar.edu.unju.fi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;


@Entity
@Component
@Table(name = "Usuarios")
public class Usuario {
  @Id
  @NotEmpty
  @Min(value=1000000,message = "El DNI debe ser mayor al millon")
  @Max(value = 999999999, message = "El DNI debe ser menor a 999999999")
  private Long dni;
  @Size(min=3, max=100, message="EL nombre de usuario debe tener 3 caracteres minimo, maximo 100")
  @NotEmpty(message="El nombre de usuario no puede estar vacio")
  @Column(name = "nombre")
  private String usuario;
  @NotEmpty(message="El email no puede estar vacio")
  @Column(name = "email")
  private String email;
  @NotEmpty(message="No puede estar vacio")
  @Column(name = "contraseña")
  private String contraseña;
  @NotEmpty(message = "Elija uno")
  private String tipo;
  private Boolean estado;
  public Usuario(
      @NotEmpty @Min(value = 1000000, message = "El DNI debe ser mayor al millon") @Max(value = 999999999, message = "El DNI debe ser menor a 999999999") Long dni,
      @Size(min = 3, max = 100, message = "EL nombre de usuario debe tener 3 caracteres minimo, maximo 100") @NotEmpty(message = "El nombre de usuario no puede estar vacio") String usuario,
      @NotEmpty(message = "El email no puede estar vacio") String email,
      @NotEmpty(message = "No puede estar vacio") String contraseña, @NotEmpty(message = "Elija uno") String tipo,
      Boolean estado) {
    this.dni = dni;
    this.usuario = usuario;
    this.email = email;
    this.contraseña = contraseña;
    this.tipo = tipo;
    this.estado = estado;
  }
  public Usuario(){
    
  }
  public Boolean getEstado() {
    return estado;
  }
  public void setEstado(Boolean estado) {
    this.estado = estado;
  }
  public String getTipo() {
    return tipo;
  }
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
  public Long getDni() {
    return dni;
  }
  public void setDni(Long dni) {
    this.dni = dni;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public void setContraseña(String contraseña) {
    this.contraseña = contraseña;
  }
  public String getContraseña() {
    return contraseña;
  }
 
}
