
package com.comercio.core.dao;

import com.comercio.core.db.Conexion;
import com.comercio.core.model.Categoria;
import java.util.List;
import javax.persistence.Query;

public class CategoriaDaoImpl implements CategoriaDao{

    @Override
    public List<Categoria> findAllCategoria() {
        return (List<Categoria>)Conexion.getInstancia().findAll(Categoria.class); //Esto es reflexion preguntar
    }

    @Override
    public Categoria findCategoriaById(int id) {
        return (Categoria)Conexion.getInstancia().findById(Categoria.class, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria findCategoriaByDescripcion(String descripcion) {
        List<Categoria> categoria= (List<Categoria>)Conexion.getInstancia().getEmm()
                        .createNamedQuery("select c from Categoria c where c.descripcion =: descripcion")
                        .setParameter("descripcion", descripcion)
                                .getResultList();
        if(!categoria.isEmpty()){
            return categoria.get(0);
        }
        
        return null;
    }
   

    @Override
    public void saveCategoria(Categoria elemento) {
        Conexion.getInstancia().save(elemento); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCategoria(Categoria elemento) {
        Conexion.getInstancia().delete(elemento); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCategoria(Categoria elemento) {
        Conexion.getInstancia().update(elemento); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
