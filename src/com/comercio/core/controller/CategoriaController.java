
package com.comercio.core.controller;

import com.comercio.core.dao.CategoriaDaoImpl;
import com.comercio.core.model.Categoria;
import com.comercio.core.model.Producto;
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


public class CategoriaController implements Initializable {
    
  @FXML private TableView<Categoria> tblCategorias;
  @FXML private TableColumn<Categoria,String> colDescripcionc;
  @FXML private TableColumn<Categoria,Number> colCodigo;
  @FXML private TextField txtDescripcion;
  @FXML private Button btnNuevo;
  @FXML private Button btnGuardar;
  @FXML private Button btnCancelar;
  @FXML private Button btnEliminar;
 private ObservableList<Categoria> listaCategorias;
  private  CategoriaDaoImpl categoriaDao= new CategoriaDaoImpl();
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      getCategorias();
    enlazarDatosc();
    enlazarColumnasc();//To change body of generated methods, choose Tools | Templates.
    }
    
    public void getCategorias(){
        
    listaCategorias= FXCollections.observableArrayList(categoriaDao.findAllCategoria());
    }
    
    public void enlazarDatosc(){
        
   tblCategorias.setItems(listaCategorias);
    }
    
    public void enlazarColumnasc(){
        
     colCodigo.setCellValueFactory(cellData->cellData.getValue().codigoCategoria());
    colDescripcionc.setCellValueFactory(cellData->cellData.getValue().descripcion());
    }
     public void nuevo(){

     txtDescripcion.setDisable(false);
    btnNuevo.setDisable(true);
    btnGuardar.setDisable(false);
    btnCancelar.setDisable(false);
    btnEliminar.setDisable(false);
    
}
     public void guardar(){
         
          try {  Categoria categoria = new Categoria();
    categoria.setDescripcion(txtDescripcion.getText());
    categoriaDao.saveCategoria(categoria);
    listaCategorias.add(categoria);
    txtDescripcion.setDisable(true);
    btnNuevo.setDisable(false);
    btnGuardar.setDisable(true);
    btnEliminar.setDisable(false);
    btnCancelar.setDisable(true);
  }catch (Exception e){
      
      e.printStackTrace();
  }
     }
  
         public void cancelar(){
             
         txtDescripcion.setText("");
         btnCancelar.setDisable(true);
         btnNuevo.setDisable(false);
         btnGuardar.setDisable(true);
         btnEliminar.setDisable(false);
       
         
         }
         public void eliminar(){
             
             if (tblCategorias.getSelectionModel().getSelectedItem() != null){
                 
                 int respuesta= JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION);
                if(respuesta== JOptionPane.YES_OPTION){
                    
                    categoriaDao.deleteCategoria(tblCategorias.getSelectionModel().getSelectedItem());
                    listaCategorias.remove(tblCategorias.getSelectionModel().getSelectedItem());
                }else{
                    
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }
             
             
             }
         
     }
     
}
