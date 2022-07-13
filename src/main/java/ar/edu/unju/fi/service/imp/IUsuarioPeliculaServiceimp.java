package ar.edu.unju.fi.service.imp;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.model.UsuarioPeliculas;
import ar.edu.unju.fi.repository.PeliculasRepository;
import ar.edu.unju.fi.repository.UsuarioPeliculasRepository;
import ar.edu.unju.fi.service.IUsuarioPeliculaService;
@Service
public class IUsuarioPeliculaServiceimp implements IUsuarioPeliculaService{
  @Autowired
  UsuarioPeliculasRepository usuarioPeliculasRepository;
  @Autowired
  PeliculasRepository peliculasRepository;
  @Override
	public List<UsuarioPeliculas> listarUsuarioPeliculas(Usuario usuario) {
		List<UsuarioPeliculas> auxiliar = new ArrayList<>();
    List<UsuarioPeliculas> aux2=new ArrayList<>();
		auxiliar=(List<UsuarioPeliculas>) usuarioPeliculasRepository.findAll();
    for (int i = 0; i < auxiliar.size(); i++) {
      if(auxiliar.get(i).getUsuario().equals(usuario)){
        aux2.add(auxiliar.get(i));
      }
    }
		return aux2;
	}

  @Override
  public void agregarentrada(UsuarioPeliculas usuarioPeliculas) {
    List<UsuarioPeliculas> listausuario=new ArrayList<>();
    Boolean existe=false;
    listausuario=listarUsuarioPeliculas(usuarioPeliculas.getUsuario());
    for (int i = 0; i < listausuario.size(); i++) {
      if(listausuario.get(i).getPelis().equals(usuarioPeliculas.getPelis())){
        System.out.println("Existe");
        existe=true;
      }
    }
    if(existe!=true){
      usuarioPeliculasRepository.save(usuarioPeliculas);
    }
  }

  @Override
  public Boolean existe(UsuarioPeliculas usuarioPeliculas) {
    List<UsuarioPeliculas> listausuario=new ArrayList<>();
    Boolean existe=false;
    listausuario=listarUsuarioPeliculas(usuarioPeliculas.getUsuario());
    for (int i = 0; i < listausuario.size(); i++) {
      if(listausuario.get(i).getPelis().equals(usuarioPeliculas.getPelis())){
        System.out.println("Existe");
        existe=true;
      }
    }
    return existe;
  }
}
