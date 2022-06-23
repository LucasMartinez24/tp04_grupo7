package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.controller.PeliculaController;
import ar.edu.unju.fi.model.Pelicula;
import ar.edu.unju.fi.repository.PeliculaRepository;
import ar.edu.unju.fi.service.IPeliculaService;
import ar.edu.unju.fi.util.ListaPelicula;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPeliculaServiceImp implements IPeliculaService{
  private static final Log Grupo7 = LogFactory.getLog(PeliculaController.class);
	
	@Autowired
	ListaPelicula list;
	@Autowired
	PeliculaRepository PeliculaRepository;

	@Override
	public void guardarPeliculas( Pelicula pelicula){
		PeliculaRepository.save(pelicula);
	}

	@Override
	public void eliminarPelicula(Long id) throws Exception{
		Pelicula auxiliar =new Pelicula();
		auxiliar=buscarPelicula(id);
		PeliculaRepository.delete(auxiliar);	
	}

	@Override
	public void modificarPelicula(Pelicula pelicula) {
		PeliculaRepository.save(pelicula);
	}

	@Override
	public List<Pelicula> listarPeliculas() {
		Grupo7.info("ingresando al metodo mostrar");
		List<Pelicula> auxiliar = new ArrayList<>();
		auxiliar=(List<Pelicula>) PeliculaRepository.findAll();	
		return auxiliar;
	}

	

	@Override
	public Pelicula buscarPelicula(Long id) throws Exception {
		Pelicula peliculaEncontrada = new Pelicula();
    peliculaEncontrada=PeliculaRepository.findById(id).orElseThrow(()->new Exception("Pelicula no encontrada"));
		return peliculaEncontrada;
	}

}