
package com.comercio.core.dao;

import com.comercio.core.db.Conexion;
import com.comercio.core.model.Producto;
import java.util.List;

public class ProductoDaoImpl implements ProductoDao {

    @Override
    public List<Producto> findAllProductos() {
        return (List<Producto>)Conexion.getInstancia().findAll(Producto.class); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Producto findProductoById(int id) {
        return (Producto)Conexion.getInstancia().findById(Producto.class, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Producto findProductoByDescripcion(String descripcion) {
       List<Producto> producto=(List<Producto>)Conexion.getInstancia().getEmm()
                               .createNamedQuery("select p from Producto p where p.descripcion =: descripcion")
                               .setParameter("descripcion", descripcion)
                               .getResultList();//To change body of generated methods, choose Tools | Templates.
   
      if (!producto.isEmpty()){
          
          return producto.get(0);
      }
      return null;
    }

    @Override
    public void saveProducto(Producto elemento) {
        Conexion.getInstancia().save(elemento); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProducto(Producto elemento) {
        Conexion.getInstancia().delete(elemento); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProducto(Producto elemento) {
       Conexion.getInstancia().update(elemento); //To change body of generated methods, choose Tools | Templates.
    }
    
}
