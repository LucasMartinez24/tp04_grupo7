package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.controller.UsuarioController;
import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.repository.UsuarioRepository;
import ar.edu.unju.fi.util.ListaUsuario;
import ar.edu.unju.fi.service.IUsuarioService;

@Service
public class IUsuarioServiceImp implements IUsuarioService{
  private static final Log LUCAS = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	ListaUsuario list;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Override
	public void guardarUsuario(Usuario usuario) {
		String pw=usuario.getContraseña();
		BCryptPasswordEncoder coder= new BCryptPasswordEncoder(4);
		usuario.setContraseña(coder.encode(pw));
		usuarioRepository.save(usuario);
	}

	@Override
	public void eliminarUsuario(Long id) throws Exception{
		Usuario auxiliar =new Usuario();
		auxiliar=buscarUsuario(id);
		usuarioRepository.delete(auxiliar);	
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		LUCAS.info("ingresando al metodo mostrar");
		List<Usuario> auxiliar = new ArrayList<>();
		auxiliar=(List<Usuario>) usuarioRepository.findAll();	
		return auxiliar;
	}

	

	@Override
	public Usuario buscarUsuario(Long id) throws Exception {
		Usuario usuarioEncontrado = new Usuario();
		usuarioEncontrado=usuarioRepository.findById(id).orElseThrow(()->new Exception("usuario no encontrado"));
		return usuarioEncontrado;
	}
}