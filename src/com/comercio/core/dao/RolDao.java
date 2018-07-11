
package com.comercio.core.dao;
import com.comercio.core.model.Rol;
import java.util.List;

public interface RolDao {
    public List<Rol> findAllRoles();
    public Rol findRolById(int codigoRol);
    public Rol findRolByDescripcion(String descripcion);
    public void saveRol(Rol rol);
    public void deleteRol(Rol rol);
    public void updateRol(Rol rol);
}
