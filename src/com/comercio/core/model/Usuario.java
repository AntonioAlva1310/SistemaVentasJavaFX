/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comercio.core.model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
@Entity
@Table(name="usuario")
@NamedQueries({@NamedQuery(name="Usuario.findAll",query="from Usuario")})
public class Usuario implements Serializable {
    private final IntegerProperty codigoUsuario;
    private final StringProperty nombre;
    private final StringProperty login;
    private final StringProperty password;
    private final StringProperty email;    
    private Rol rol;
    public Usuario() {
        this.codigoUsuario = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty("");
        this.login = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
    }    
    public Usuario(int codigoUsuario, String nombre, String login, String password, String email) {
        this.codigoUsuario = new SimpleIntegerProperty(codigoUsuario);
        this.nombre = new SimpleStringProperty(nombre);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.email = new SimpleStringProperty(email);
    }
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo_usuario")
    public int getCodigoUsuario() {
        return codigoUsuario.get();
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario.set(codigoUsuario);
    }
    
    public IntegerProperty codigoUsuario(){
        return this.codigoUsuario;
    }
    @Column(name="nombre")
    public String getNombre() {
        return nombre.get();
    }
    
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    public StringProperty nombre(){
        return this.nombre;
    }

    @Column(name="login")
    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public StringProperty login(){
        return this.login;
    }

    @Column(name="password")
    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
    
    public StringProperty password(){
        return this.password;
    }

    @Column(name="email")
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
    public StringProperty email(){
        return this.email;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="codigo_rol", updatable=false, insertable=true, nullable=false )
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
   
}

