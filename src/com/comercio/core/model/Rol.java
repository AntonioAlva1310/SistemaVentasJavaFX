
package com.comercio.core.model;

import java.io.Serializable;
import java.util.Set;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name = "rol")
@NamedQueries({@NamedQuery(name="Rol.findAll", query= "from Rol")})
public class Rol implements Serializable{
    
    private final IntegerProperty codigo;
    private final StringProperty descripcion;
    private Set<Usuario> usuarios;
    
    public Rol () {
        
        this.codigo= new SimpleIntegerProperty();
        this.descripcion= new SimpleStringProperty();
        
    }
    public Rol(int  codigo, String descripcion){
        
        this.codigo= new SimpleIntegerProperty(codigo);
        this.descripcion= new SimpleStringProperty(descripcion);
        

    }
     public IntegerProperty codigo() {
        return codigo;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo_rol")
    public int getCodigo(){
        return this.codigo.get();
    }
    public void setCodigo(int codigo){
        this.codigo.set(codigo);
    }
    @Column(name="descripcion")
    public String getDescripcion(){
        return this.descripcion.get();
    }
    public void setDescripcion(String descripcion){
        this.descripcion.set(descripcion);
    }
    public StringProperty descripcion() {        
        return descripcion;
    }
    @OneToMany(mappedBy  = "rol")
    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
}


