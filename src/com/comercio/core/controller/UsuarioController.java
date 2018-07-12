
package com.comercio.core.controller;

import com.comercio.core.dao.RolDaoImpl;
import com.comercio.core.dao.UsuarioDaoImpl;
import com.comercio.core.model.Rol;
import com.comercio.core.model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;


public class UsuarioController implements Initializable{
    private enum ACCIONES {NUEVO,EDITAR};
    
@FXML private TableView<Usuario> tblUsuarios;
@FXML private TableColumn<Usuario,Number>colCodigo;
@FXML private TableColumn<Usuario,String>colNombre;
@FXML private TableColumn<Usuario,String>colEmail;
@FXML private TableColumn<Usuario,String>colLogin;
@FXML private TableColumn<Usuario,String>colPassword;
@FXML private TableColumn<Usuario,String>colRol;
@FXML private TextField txtDescripcion;
@FXML private TextField txtEmail;
@FXML private TextField txtLogin;
@FXML private TextField txtPassword;
@FXML private ComboBox  cmbRoles;

@FXML private Button btnNuevo;
    @FXML private Button btnCancelar;
    @FXML private Button btnEliminar;
    @FXML private Button btnGuardar;
    
    @FXML private Button btnEditar;
    private ACCIONES accion;
    
   
private ObservableList<Usuario> listaUsuarios;
private UsuarioDaoImpl usuarioDao= new UsuarioDaoImpl();
private Usuario elementoSeleccionado= new Usuario();
private ObservableList<Rol> listaRoles;
private RolDaoImpl rolDao= new RolDaoImpl();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      try { 
          getUsuarios();
          enlazarDatos();
         enlazarColumnas();
           mostrarRoles();
    }catch (Exception e){
        
        e.printStackTrace();
       }
    }
    
    public void getUsuarios(){
        
        listaUsuarios= FXCollections.observableArrayList(usuarioDao.findAllUsuarios());
    }
    
    public void enlazarDatos(){
        
        tblUsuarios.setItems(listaUsuarios);
    }
    
    public void enlazarColumnas(){
        colCodigo.setCellValueFactory(cellData->cellData.getValue().codigoUsuario());
colNombre.setCellValueFactory(cellData->cellData.getValue().nombre());
colEmail.setCellValueFactory(cellData->cellData.getValue().email());
colLogin.setCellValueFactory(cellData->cellData.getValue().login());
colPassword.setCellValueFactory(cellData->cellData.getValue().password());
colRol.setCellValueFactory(cellData->cellData.getValue().getRol().descripcion());
        
    }
    public void mostrarRoles(){
         
         listaRoles= FXCollections.observableArrayList(rolDao.findAllRoles());
         
         cmbRoles.setItems(listaRoles);
        
     }
    public void nuevo(){
        
    txtDescripcion.setEditable(true);
    txtEmail.setEditable(true);
    txtLogin.setEditable(true);
    txtPassword.setEditable(true);
    
    cmbRoles.setDisable(false);
   
    
    btnNuevo.setDisable(true);
    btnGuardar.setDisable(false);
    btnCancelar.setDisable(false);
    btnEliminar.setDisable(true);
    
    accion= ACCIONES.NUEVO;
      }
    
    public void guardar(){
        
        
        if (cmbRoles.getSelectionModel().getSelectedItem()==null){
            
            JOptionPane.showMessageDialog(null, "Debe seleccionar un ro");
        }else if (txtDescripcion.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "Debe ingresar un usuario");
            
        }else {
            try {
                switch (accion){
                    
                    case NUEVO:
                        Usuario usuario= new Usuario();
                        usuario.setRol((Rol) cmbRoles.getSelectionModel().getSelectedItem());
                        usuario.setNombre(txtDescripcion.getText());
                        usuario.setEmail(txtEmail.getText());
                        usuario.setLogin(txtLogin.getText());
                        usuario.setPassword(txtPassword.getText());
                        
                        usuarioDao.saveUsuario(usuario);
                        listaUsuarios.add(usuario);
                        break;
                        
                    case EDITAR:
                        elementoSeleccionado.setNombre(txtDescripcion.getText());
                        elementoSeleccionado.setEmail(txtEmail.getText());
                        elementoSeleccionado.setLogin(txtLogin.getText());
                        elementoSeleccionado.setPassword(txtPassword.getText());
                        elementoSeleccionado.setRol((Rol) cmbRoles.getSelectionModel().getSelectedItem());
                        usuarioDao.saveUsuario(elementoSeleccionado);
                        listaUsuarios.set(tblUsuarios.getSelectionModel().getSelectedIndex(), elementoSeleccionado);
                
                
                }
                 txtDescripcion.setEditable(false);
           txtEmail.setEditable(false);
           txtLogin.setEditable(false);
          txtPassword.setEditable(false);
          cmbRoles.setDisable(false);
          btnNuevo.setDisable(false);
          btnGuardar.setDisable(true);
          btnEliminar.setDisable(false);
          btnCancelar.setDisable(true);
          btnEditar.setDisable(false);
       
                
            }catch (Exception e){
                e.printStackTrace();
                
            }
        }
    }
    public void cancelar(){
     
     txtDescripcion.setText("");
     txtDescripcion.setEditable(false);
     txtEmail.setEditable(false);
    txtLogin.setEditable(false);
    txtPassword.setEditable(false);
    
    cmbRoles.setDisable(true);
     btnNuevo.setDisable(false);
     btnGuardar.setDisable(true);
     btnCancelar.setDisable(true);
    btnEliminar.setDisable(false);
    btnEditar.setDisable(false);
    
   
 }
    
     public void eliminar(){
         
       if (tblUsuarios.getSelectionModel().getSelectedItem()!=null){
           
           int respuesta= JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el registo", "eliminar", JOptionPane.YES_NO_OPTION);
           if(respuesta==JOptionPane.YES_OPTION){
               
               usuarioDao.deleteUsuario(tblUsuarios.getSelectionModel().getSelectedItem());
               listaUsuarios.remove(tblUsuarios.getSelectionModel().getSelectedItem());
           }
       }else {
           
           JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
           
       }
    
     }
     
     
     public void seleccionarElemento(){
         
         if(tblUsuarios.getSelectionModel().getSelectedItem() != null){
             
             elementoSeleccionado= tblUsuarios.getSelectionModel().getSelectedItem();
             txtDescripcion.setText(elementoSeleccionado.getNombre());
             txtEmail.setText(elementoSeleccionado.getEmail());
             txtLogin.setText(elementoSeleccionado.getLogin());
             txtPassword.setText(elementoSeleccionado.getPassword());
             cmbRoles.setValue(elementoSeleccionado.getRol());
             
         }
     }
     
     public void modificar(){
         
         if (tblUsuarios.getSelectionModel().getSelectedItem()!= null){
             
             txtDescripcion.setEditable(true);
             txtEmail.setEditable(true);
             txtLogin.setEditable(true);
             txtPassword.setEditable(true);
             cmbRoles.setDisable(false);
             btnNuevo.setDisable(true);
             btnEliminar.setDisable(true);
             btnEditar.setDisable(true);
             btnGuardar.setDisable(false);
             btnCancelar.setDisable(false);
             accion= ACCIONES.EDITAR;
         }else{
             
             JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
         }
     }
}
