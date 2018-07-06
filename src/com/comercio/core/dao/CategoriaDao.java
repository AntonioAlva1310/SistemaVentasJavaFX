
package com.comercio.core.dao;

import com.comercio.core.model.Categoria;
import java.util.List;

public interface CategoriaDao {
    
   public List<Categoria> findAllCategoria();
   public Categoria findCategoriaById(int id);
   public Categoria findCategoriaByDescripcion(String descripcion);
   public void saveCategoria(Categoria elemento);
   public void deleteCategoria(Categoria elemento);
   public void updateCategoria(Categoria elemento);
   
}
