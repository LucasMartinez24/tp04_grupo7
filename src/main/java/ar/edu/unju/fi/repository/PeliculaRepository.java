package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.model.Pelicula;

public interface PeliculaRepository extends CrudRepository <Pelicula,Long>{
  
}