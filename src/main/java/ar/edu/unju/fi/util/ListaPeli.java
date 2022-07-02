package ar.edu.unju.fi.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Peliculas;

@Component
public class ListaPeli {
  private List<Peliculas> lista = new ArrayList<>();
  
  public ListaPeli(){
    
  }

  public ListaPeli(List<Peliculas> lista) {
    this.lista = lista;
  }

  public List<Peliculas> getLista() {
    return lista;
  }

  public void setLista(List<Peliculas> lista) {
    this.lista = lista;
  }
}
