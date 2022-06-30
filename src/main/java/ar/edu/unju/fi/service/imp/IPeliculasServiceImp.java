package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.controller.PeliculaController;
import ar.edu.unju.fi.model.Peliculas;
import ar.edu.unju.fi.repository.PeliculasRepository;
import ar.edu.unju.fi.service.IPeliculasService;
import ar.edu.unju.fi.util.ListaPeli;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPeliculasServiceImp implements IPeliculasService{
  private static final Log LUCAS = LogFactory.getLog(PeliculaController.class);
	
	@Autowired
	ListaPeli list;
	@Autowired
	PeliculasRepository peliculasRepository;

	@Override
	public void guardarPeliculas( Peliculas peliculas){
		peliculasRepository.save(peliculas);
	}

	@Override
	public void eliminarPeliculas(Long id) throws Exception{
		Peliculas auxiliar =new Peliculas();
		auxiliar=buscarPeliculas(id);
		peliculasRepository.delete(auxiliar);	
	}

	@Override
	public void modificarPeliculas(Peliculas peliculas) {
		peliculasRepository.save(peliculas);
	}

	@Override
	public List<Peliculas> listarPeliculas() {
		LUCAS.info("ingresando al metodo mostrar");
		List<Peliculas> auxiliar = new ArrayList<>();
		auxiliar=(List<Peliculas>) peliculasRepository.findAll();	
		return auxiliar;
	}

	

	@Override
	public Peliculas buscarPeliculas(Long id) throws Exception {
		Peliculas cursoEncontrado = new Peliculas();
		cursoEncontrado=peliculasRepository.findById(id).orElseThrow(()->new Exception("curso no encontrado"));
		return cursoEncontrado;
	}

}
