
package com.comercio.core.dao;

import com.comercio.core.model.Producto;
import java.util.List;

public interface ProductoDao {
    public List<Producto> findAllProductos();
    public Producto findProductoById (int id);
    public Producto findProductoByDescripcion (String descripcion);
    public void saveProducto (Producto elemento);
    public void deleteProducto (Producto elemento);
    public void updateProducto (Producto elemento);
    
    
}
