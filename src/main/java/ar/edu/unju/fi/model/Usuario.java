package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

@Component
public class Usuario {
private Long dni;
private Long id;
private String usuario;
private String nombre;
private String apellido;
private String email;
private String contraseña;
private String estado;
public Usuario(){

}
public Long getDni() {
  return dni;
}
public void setDni(Long dni) {
  this.dni = dni;
}
public Long getId() {
  return id;
}
public void setId(Long id) {
  this.id = id;
}
public String getUsuario() {
  return usuario;
}
public void setUsuario(String usuario) {
  this.usuario = usuario;
}
public String getNombre() {
  return nombre;
}
public void setNombre(String nombre) {
  this.nombre = nombre;
}
public String getApellido() {
  return apellido;
}
public void setApellido(String apellido) {
  this.apellido = apellido;
}
public String getEmail() {
  return email;
}
public void setEmail(String email) {
  this.email = email;
}
public String getContraseña() {
  return contraseña;
}
public void setContraseña(String contraseña) {
  this.contraseña = contraseña;
}
public String getEstado() {
  return estado;
}
public void setEstado(String estado) {
  this.estado = estado;
}
}

