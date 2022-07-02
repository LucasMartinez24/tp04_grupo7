package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Usuario;

@Service
public interface IUsuarioService {
  public void guardarUsuario( Usuario usuario);
	public void eliminarUsuario(Long id) throws Exception;
	public void modificarUsuario(Usuario usuario);
	public List<Usuario> listarUsuarios(); 
	public Usuario buscarUsuario(Long id) throws Exception; 
}
