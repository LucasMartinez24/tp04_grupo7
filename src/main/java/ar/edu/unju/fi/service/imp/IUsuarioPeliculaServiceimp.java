package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.model.UsuarioPeliculas;
import ar.edu.unju.fi.repository.UsuarioPeliculasRepository;
import ar.edu.unju.fi.service.IUsuarioPeliculaService;
@Service
public class IUsuarioPeliculaServiceimp implements IUsuarioPeliculaService{
  @Autowired
  UsuarioPeliculas nuevo;
  @Autowired
  UsuarioPeliculasRepository usuarioPeliculasRepository;
  @Override
  public void guardarValoracion(UsuarioPeliculas unaValoracion) {
    usuarioPeliculasRepository.save(unaValoracion);
  }

  @Override
  public UsuarioPeliculas crearValoracion() {
    return nuevo;
  }

  @Override
  public Integer verificarValoracionAnterio(Usuario usuario, Long id) {
    System.out.println("dentro de verificacion de valoracion");
		List<UsuarioPeliculas> todasLasValoraciones=(List<UsuarioPeliculas>) usuarioPeliculasRepository.findAll();
		int i;
		Integer contadorDeComentariosEnUnapelicula=0;
		
		for(i=0;i<usuarioPeliculasRepository.count();i++) {
			if(todasLasValoraciones.get(i).getValoracion()!=null) {
				
				if(todasLasValoraciones.get(i).getPelis().getId()==id) {
					
					if(todasLasValoraciones.get(i).getUsuario().getDni()==usuario.getDni()) {
						contadorDeComentariosEnUnapelicula++;
					}
				}
			}	
		}
		return contadorDeComentariosEnUnapelicula;
  }
  
}
