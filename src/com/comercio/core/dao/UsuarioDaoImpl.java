
package com.comercio.core.dao;

import java.util.List;

import com.comercio.core.db.Conexion;
import com.comercio.core.model.Usuario;
import javax.persistence.ParameterMode;
public class UsuarioDaoImpl implements UsuarioDao {
    @Override
    public List<Usuario> findAllUsuarios() {
        return (List<Usuario>)Conexion.getInstancia().findAll(Usuario.class);
    }

    @Override
    public Usuario findByIdUsuario(int codigoUsuario) {
        return (Usuario)Conexion.getInstancia().findById(Usuario.class, codigoUsuario);
    }

    @Override
    public Usuario findByNombreUsuario(String nombre) {
         List<Usuario>usuario;
        usuario = (List<Usuario>) Conexion.getInstancia().getEmm()
                .createNamedQuery("select u from Usuario u where u.nombre =: nombre")
                .setParameter("nombre", nombre)
                .getResultList();
        if (!usuario.isEmpty()) {
            return usuario.get(0);
        }
        return null;
    }

    @Override
    
    public void saveUsuario(Usuario elemento) {
        Conexion.getInstancia().save(elemento);
    }
    @Override
    public void deleteUsuario(Usuario elemento) {
        Conexion.getInstancia().delete(elemento);
    }
    @Override
    public void updateUsuario(Usuario elemento) {
        Conexion.getInstancia().update(elemento);
    }
}