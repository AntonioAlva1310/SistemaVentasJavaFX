
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
    public Usuario findByEmailUsuario(String email) {
        return (Usuario)Conexion
                .getInstancia()
                .getEmm()
                .createQuery("from Usuario where email =: email")
                .setParameter("email",email);
    }
    @Override
    public Usuario findByLoginAndPassword(String login, String password) {
        List<Usuario> usuarios =
                Conexion
                .getInstancia()
                .getEmm()
                .createStoredProcedureQuery("sp_AutenticarUsuario",Usuario.class)                                                
                .registerStoredProcedureParameter(1,String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2,String.class, ParameterMode.IN)
                .setParameter(1,login)
                .setParameter(2,password)
                .getResultList();
        if(usuarios.isEmpty()){
           return null;
        }else{
           return usuarios.get(0);
        }        
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