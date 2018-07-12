
package com.comercio.core.dao;

import com.comercio.core.db.Conexion;
import java.util.List;

import com.comercio.core.model.Rol;
public class RolDaoImpl implements RolDao {     
    @Override
    public List<Rol> findAllRoles() {
        return (List<Rol>)Conexion.getInstancia().findAll(Rol.class);
    }
    @Override
    public Rol findRolById(int codigoRol) {
        return (Rol)Conexion.getInstancia().findById(Rol.class, codigoRol);
    }
    @Override
    public Rol findRolByDescripcion(String descripcion) {
        List<Rol> rol= (List<Rol>)Conexion
                .getInstancia()
                .getEmm()
                .createNamedQuery("select r from Rol r where r.descripcion =: descripcion")
                .setParameter("descripcion",descripcion)
                .getResultList();
        if (!rol.isEmpty()){
            return rol.get(0);
        }
        return null;
    }
    @Override
    public void saveRol(Rol rol) {
        Conexion.getInstancia().save(rol);
    }
    @Override
    public void deleteRol(Rol rol) {
        Conexion.getInstancia().delete(rol);
    }
    @Override
    public void updateRol(Rol rol) {
        Conexion.getInstancia().update(rol);
    }   
}