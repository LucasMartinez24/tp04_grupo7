package ar.edu.unju.fi.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Usuario;

@Component
public class Lista {
  private List<Usuario> lista = new ArrayList<>();
  
  public Lista(){
    
  }

  public Lista(List<Usuario> lista) {
    this.lista = lista;
  }

  public List<Usuario> getLista() {
    return lista;
  }

  public void setLista(List<Usuario> lista) {
    this.lista = lista;
  }
}
