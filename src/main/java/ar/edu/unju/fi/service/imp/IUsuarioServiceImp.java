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
import ar.edu.unju.fi.service.IUsuarioService;
import ar.edu.unju.fi.util.Lista;

@Service
public class IUsuarioServiceImp implements IUsuarioService{
  private static final Log LUCAS = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	Lista list;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public void guardarUsuario(Usuario usuario) {
		String pw=usuario.getContraseña();
		BCryptPasswordEncoder coder= new BCryptPasswordEncoder(4);
		usuario.setContraseña(coder.encode(pw));
		usuario.setEstado(true);
		usuarioRepository.save(usuario);
	}

	@Override
	public void eliminarUsuario(Long id) throws Exception{
		Usuario auxiliar =new Usuario();
		auxiliar=buscarUsuario(id);
		auxiliar.setEstado(false);
		usuarioRepository.save(auxiliar);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		LUCAS.info("ingresando al metodo mostrar");
		List<Usuario> auxiliar = new ArrayList<>();
		List<Usuario> auxiliar2 = new ArrayList<>();
		auxiliar=(List<Usuario>) usuarioRepository.findAll();	
		for (int i = 0; i < auxiliar.size(); i++) {
			if(auxiliar.get(i).getEstado().equals(true)){
				auxiliar2.add(auxiliar.get(i));
			}
		}
		return auxiliar2;
	}
	@Override
	public Usuario buscarUsuario(Long id) throws Exception {
		Usuario usuarioEncontrado = new Usuario();
		usuarioEncontrado=usuarioRepository.findById(id).orElseThrow(()->new Exception("usuario no encontrado"));
		return usuarioEncontrado;
	}
}
