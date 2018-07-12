
package com.comercio.core.dao;

import com.comercio.core.model.Usuario;
import java.util.List;

public interface UsuarioDao {
    public List<Usuario> findAllUsuarios();
    public Usuario findByIdUsuario(int codigoUsuario);
    public Usuario findByNombreUsuario(String nombre);
   
    public void saveUsuario(Usuario elemento);
    public void deleteUsuario(Usuario elemento);
    public void updateUsuario(Usuario elemento);
}
