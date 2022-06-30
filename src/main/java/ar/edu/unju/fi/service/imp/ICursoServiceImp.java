package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.controller.PeliculaController;
import ar.edu.unju.fi.model.Peliculas;
import ar.edu.unju.fi.repository.CursoRepository;
import ar.edu.unju.fi.service.ICursoService;
import ar.edu.unju.fi.util.ListaPeli;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICursoServiceImp implements ICursoService{
  private static final Log LUCAS = LogFactory.getLog(PeliculaController.class);
	
	@Autowired
	ListaPeli list;
	@Autowired
	CursoRepository cursoRepository;

	@Override
	public void guardarCurso( Peliculas curso){
		cursoRepository.save(curso);
	}

	@Override
	public void eliminarCurso(Long id) throws Exception{
		Peliculas auxiliar =new Peliculas();
		auxiliar=buscarCurso(id);
		cursoRepository.delete(auxiliar);	
	}

	@Override
	public void modificarCurso(Peliculas curso) {
		cursoRepository.save(curso);
	}

	@Override
	public List<Peliculas> listarCursos() {
		LUCAS.info("ingresando al metodo mostrar");
		List<Peliculas> auxiliar = new ArrayList<>();
		auxiliar=(List<Peliculas>) cursoRepository.findAll();	
		return auxiliar;
	}

	

	@Override
	public Peliculas buscarCurso(Long id) throws Exception {
		Peliculas cursoEncontrado = new Peliculas();
		cursoEncontrado=cursoRepository.findById(id).orElseThrow(()->new Exception("curso no encontrado"));
		return cursoEncontrado;
	}

}
