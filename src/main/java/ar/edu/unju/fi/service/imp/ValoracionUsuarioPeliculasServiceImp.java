package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Peliculas;
import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.model.ValoracionUsuarioPeliculas;
import ar.edu.unju.fi.repository.ValoracionUsuarioPeliculasRepository;
import ar.edu.unju.fi.service.ValoracionUsuarioPeliculasService;

@Service
public class ValoracionUsuarioPeliculasServiceImp implements ValoracionUsuarioPeliculasService{
  @Autowired
  ValoracionUsuarioPeliculasRepository valoracionUsuarioPeliculasRepository;

  @Override
  public void guardarValoracion(ValoracionUsuarioPeliculas valoracion) throws Exception {
    ValoracionUsuarioPeliculas valoracionEncontrada=new ValoracionUsuarioPeliculas();
    Boolean aux=Existe(valoracion.getUsuario());
    if(aux==true){
      valoracionEncontrada=valoracionUsuarioPeliculasRepository.findByUsuario(valoracion.getUsuario()).orElseThrow(()->new Exception("usuario no encontrado"));
      valoracionEncontrada.setValor(valoracion.getValor());
      valoracionEncontrada.setComentario(valoracion.getComentario());
      valoracionUsuarioPeliculasRepository.save(valoracionEncontrada);
    }else{
      valoracionUsuarioPeliculasRepository.save(valoracion);
    }
  }
  @Override
  public List<ValoracionUsuarioPeliculas> ListarValoracion(Peliculas peliculas) {
    List<ValoracionUsuarioPeliculas> auxiliar = new ArrayList<>();
    List<ValoracionUsuarioPeliculas> aux2=new ArrayList<>();
		auxiliar=(List<ValoracionUsuarioPeliculas>) valoracionUsuarioPeliculasRepository.findAll();
    for (int i = 0; i < auxiliar.size(); i++) {
      if(auxiliar.get(i).getPelis().equals(peliculas)){
        aux2.add(auxiliar.get(i));
      }
    }
		return aux2;
  }
  @Override
  public Boolean Existe(Usuario usuario) throws Exception {
    ValoracionUsuarioPeliculas usuarioEncontrado= new ValoracionUsuarioPeliculas();
    usuarioEncontrado=valoracionUsuarioPeliculasRepository.findByUsuario(usuario).orElse(null);
    if(usuarioEncontrado!=null){
      return true;
    }else{
      return false;
    }
  }
  @Override
  public Long promedio(List<ValoracionUsuarioPeliculas> lista) {
    Long aux=(long) 0;
    Integer aux2=0;
    Integer cont=lista.size();
    for (int i = 0; i < lista.size(); i++) {
      aux2=lista.get(i).getValor()+aux2;
    }
    if(cont!=0){
      aux=(long) (aux2/cont);
    }
    return aux;
  }
  
}
