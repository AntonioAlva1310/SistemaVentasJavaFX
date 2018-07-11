
package com.comercio.core.controller;

import com.comercio.core.dao.UsuarioDaoImpl;
import com.comercio.core.model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class UsuarioController implements Initializable{
    
@FXML private TableView<Usuario> tblUsuarios;
@FXML private TableColumn<Usuario,Number>colCodigo;
@FXML private TableColumn<Usuario,String>colNombre;
@FXML private TableColumn<Usuario,String>colEmail;
@FXML private TableColumn<Usuario,String>colLogin;
@FXML private TableColumn<Usuario,String>colPassword;
@FXML private TableColumn<Usuario,String>colRol;
                        
    
    
private ObservableList<Usuario> listaUsuarios;
private UsuarioDaoImpl usuarioDao= new UsuarioDaoImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       getUsuarios();
       enlazarDatos();
       enlazarColumnas();
    }
    
    
    public void getUsuarios(){
        
        listaUsuarios= FXCollections.observableArrayList(usuarioDao.findAllUsuarios());
    }
    
    public void enlazarDatos(){
        
        tblUsuarios.setItems(listaUsuarios);
    }
    
    public void enlazarColumnas(){
        colCodigo.setCellValueFactory(cellData->cellData.getValue().codigo());
colNombre.setCellValueFactory(cellData->cellData.getValue().nombre());
colEmail.setCellValueFactory(cellData->cellData.getValue().email());
colLogin.setCellValueFactory(cellData->cellData.getValue().login());
colPassword.setCellValueFactory(cellData->cellData.getValue().password());
colRol.setCellValueFactory(cellData->cellData.getValue().getRol().descripcion());
        
    }
    
}
