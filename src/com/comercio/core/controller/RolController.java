
package com.comercio.core.controller;


import com.comercio.core.dao.RolDaoImpl;

import com.comercio.core.model.Rol;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class RolController implements Initializable {
    private enum ACCIONES {NUEVO, EDITAR};
     @FXML private TableView<Rol> tblRoles;
  @FXML private TableColumn<Rol,String> colDescripcion;
  @FXML private TableColumn<Rol,Number> colCodigo;
  @FXML private TextField txtDescripcion;
  @FXML private Button btnNuevo;
  @FXML private Button btnGuardar;
  @FXML private Button btnCancelar;
  @FXML private Button btnEliminar;
  @FXML private Button btnEditar;
  private ACCIONES accion;
 private ObservableList<Rol> listaRoles;
  private  RolDaoImpl rolDao= new RolDaoImpl();
  private Rol elementoSeleccionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getRoles();//To change body of generated methods, choose Tools | Templates.
        enlazarDatos();
        enlazarColumnas();
    
    }
    public void seleccionarElemento(){
        
        if(tblRoles.getSelectionModel().getSelectedItem()!= null){
            elementoSeleccionado= tblRoles.getSelectionModel().getSelectedItem();
            txtDescripcion.setText(elementoSeleccionado.getDescripcion());
        }
    }
    public void getRoles(){
        
        listaRoles= FXCollections.observableArrayList(rolDao.findAllRoles());
    }
    public void enlazarDatos(){
        
        tblRoles.setItems(listaRoles);
       
    }
  public void enlazarColumnas(){
      
      colCodigo.setCellValueFactory(cellData->cellData.getValue().codigoRol());
      colDescripcion.setCellValueFactory(cellData->cellData.getValue().descripcion());
  }

  public  void  nuevo (){
      
      txtDescripcion.setEditable(true);
      txtDescripcion.setText("");
      btnNuevo.setDisable(true);
      btnGuardar.setDisable(false);
      btnCancelar.setDisable(false);
      btnEliminar.setDisable(false);
      accion= ACCIONES.NUEVO;
      
      
  } 
  
  public void guardar(){
      
      try {
          switch  (accion){
              
              
              case NUEVO:
                  Rol rol= new Rol();
                  rol.setDescripcion(txtDescripcion.getText());
                  rolDao.saveRol(rol);
                  listaRoles.add(rol);
                 break;
              case EDITAR:
                  elementoSeleccionado.setDescripcion(txtDescripcion.getText());
                  rolDao.saveRol(elementoSeleccionado);
                  listaRoles.set(tblRoles.getSelectionModel().getSelectedIndex(), elementoSeleccionado);
                  break;
                  
                  
             }
          
          txtDescripcion.setEditable(false);
     txtDescripcion.setText("");
    btnNuevo.setDisable(false);
    btnGuardar.setDisable(true);
    btnEliminar.setDisable(false);
    btnCancelar.setDisable(true);
    btnEditar.setDisable(false);
          }catch(Exception e){
                 e.printStackTrace();
                                        
          }
      }
      public void cancelar(){
          
          txtDescripcion.setText("");
          btnCancelar.setDisable(true);
          btnNuevo.setDisable(false);
          btnGuardar.setDisable(true);
         btnEliminar.setDisable(false);
         btnEditar.setDisable(false);
      }
public void eliminar(){
             
             if (tblRoles.getSelectionModel().getSelectedItem() != null){
                 
                 int respuesta= JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION);
                if(respuesta== JOptionPane.YES_OPTION){
                    
                    rolDao.deleteRol(tblRoles.getSelectionModel().getSelectedItem());
                    listaRoles.remove(tblRoles.getSelectionModel().getSelectedItem());
                }else{
                    
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }
             txtDescripcion.setText("");
             txtDescripcion.setText("");
         btnCancelar.setDisable(true);
         btnNuevo.setDisable(false);
         btnGuardar.setDisable(true);
         btnEliminar.setDisable(false);
         btnEditar.setDisable(false);
             }
         
     }
public void modificar(){
        if( tblRoles.getSelectionModel().getSelectedItem() != null){
            
            
        
         txtDescripcion.setEditable(true);
         btnNuevo.setDisable(true);
         btnEliminar.setDisable(true);
         btnEditar.setDisable(true);
         btnGuardar.setDisable(false);
         btnCancelar.setDisable(false);
         accion= ACCIONES.EDITAR;
        }else {
            
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
        }
        
       }
}

