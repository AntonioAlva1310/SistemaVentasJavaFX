
package com.comercio.core.controller;

import com.comercio.core.dao.CategoriaDaoImpl;
import com.comercio.core.dao.ProductoDaoImpl;
import com.comercio.core.model.Categoria;
import com.comercio.core.model.Producto;
import static java.lang.Integer.toString;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javax.swing.JOptionPane;

public class ProductoController implements Initializable{
    @FXML private TableView<Producto> tblProductos;
   @FXML private TableColumn<Producto,Number>colCodigo;
    @FXML private TableColumn<Producto,String>colDescripcion;
 @FXML private TableColumn<Producto,String> colCategoria;
   @FXML private TableColumn<Producto,Number> colPrecioUnitario;
@FXML private TableColumn<Producto,Number> colPrecioMayoristas;
@FXML private TableColumn<Producto, Number> colExistencias;
 @FXML private TextField txtDescripcion;
    @FXML private TextField txtPrecioUnitario;
    @FXML private TextField txtPrecioMayoristas;
    @FXML private TextField txtExistencias;
   // @FXML private TextField txtCategorias;
    //@FXML private ChoiceBox choiceCategorias;
    @FXML private TextField txtImagen;
    @FXML private Button btnNuevo;
    @FXML private Button btnCancelar;
    @FXML private Button btnEliminar;
    @FXML private Button btnGuardar;
    @FXML private ComboBox cbxCategorias;
    
    
 private ObservableList<Producto> listaProductos;
    private ProductoDaoImpl productoDao= new ProductoDaoImpl();
   private ObservableList<Categoria> listaCategorias;
   private ObservableList<String> listaDescripciones;
    private  CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
    private CategoriaController categoriaController = new CategoriaController();
    private Categoria categoria = new Categoria();
       
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getProductos(); //To change body of generated methods, choose Tools | Templates.
         enlazarDatos();
         enlazarColumnas();
         mostrarCategorias();
         
    }
    public void getProductos(){
        
        listaProductos= FXCollections.observableArrayList(productoDao.findAllProductos());
    }
      public void enlazarDatos(){
          
          tblProductos.setItems(listaProductos);
      }
    
       public void enlazarColumnas(){
           colCodigo.setCellValueFactory(cellData->cellData.getValue().codigoProducto());
           colDescripcion.setCellValueFactory(cellData->cellData.getValue().descripcion());
     colCategoria.setCellValueFactory(cellData->cellData.getValue().getCategoria().descripcion());
      colPrecioUnitario.setCellValueFactory(cellData->cellData.getValue().precioUnitario());
     colPrecioMayoristas.setCellValueFactory(cellData->cellData.getValue().precioMayorista());
     colExistencias.setCellValueFactory(cellData->cellData.getValue().existencias());
       }

    public void nuevo(){
        
    txtDescripcion.setDisable(false);
    txtPrecioUnitario.setDisable(false);
    txtPrecioMayoristas.setDisable(false);
    txtExistencias.setDisable(false);
    txtImagen.setDisable(false);
   
    
    btnNuevo.setDisable(true);
    btnGuardar.setDisable(false);
    btnCancelar.setDisable(false);
    btnEliminar.setDisable(true);
}
public void guardar(){
    
  try {  Producto producto = new Producto();
       /* for (int i = 0; i < listaCategorias.size(); i++) {
          if(listaCategorias.get(i).getDescripcion().equals(cbxCategorias.getSelectionModel().getSelectedItem())){    
              
             producto.setCategoria(listaCategorias.get(i));
          } 
       }*/
      
       
        for (Categoria categoria: listaCategorias)
             
          if(categoria.getDescripcion().equals(cbxCategorias.getSelectionModel().getSelectedItem())){
                
                 producto.setCategoria(categoria);
                
            }
       
    producto.setDescripcion(txtDescripcion.getText());
    producto.setPrecioUnitario(Double.parseDouble(txtPrecioUnitario.getText()));
    producto.setPrecioMayorista(Double.parseDouble(txtPrecioMayoristas.getText()));
    producto.setExistencias(Integer.parseInt(txtExistencias.getText()));
    producto.setImagen(txtImagen.getText());
 
    
    productoDao.saveProducto(producto);
    listaProductos.add(producto);
    txtDescripcion.setDisable(true);
    txtPrecioUnitario.setDisable(true);
    txtPrecioMayoristas.setDisable(true);
    txtExistencias.setDisable(true);
    txtImagen.setDisable(true);
    
    btnNuevo.setDisable(false);
    btnGuardar.setDisable(true);
    btnEliminar.setDisable(true);
    btnCancelar.setDisable(true);
  }catch (Exception e){
      
      e.printStackTrace();
  }
  
}

 public void cancelar(){
     
     txtDescripcion.setText("");
     txtDescripcion.setDisable(true);
     txtPrecioUnitario.setDisable(true);
    txtPrecioMayoristas.setDisable(true);
    txtExistencias.setDisable(true);
    txtImagen.setDisable(true);
     btnNuevo.setDisable(false);
     btnGuardar.setDisable(true);
     btnCancelar.setDisable(true);
    btnEliminar.setDisable(false);
   
 }
  
 public void eliminar(){
     
     if (tblProductos.getSelectionModel().getSelectedItem() != null){
         
         int respuesta= JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el registro?", "eliminar", JOptionPane.YES_NO_OPTION);
         if (respuesta== JOptionPane.YES_OPTION){
             
             productoDao.deleteProducto(tblProductos.getSelectionModel().getSelectedItem());
             listaProductos.remove(tblProductos.getSelectionModel().getSelectedItem());
             
         }
         
         }else {
     
     JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
        }
 }
     public void mostrarCategorias(){
      // listaCategorias= FXCollections.observableArrayList()
     //   List<String> field1List = entities.stream().map(YourEntity::getField1).collect(Collectors.toList());
      //listaCategorias= FXCollections.observableArrayList(categoriaDao.findAllCategoria().iterator().next().getDescripcion());
     //  
       // choiceCategorias.setId("categorias");
       
     listaCategorias= FXCollections.observableArrayList(categoriaDao.findAllCategoria());
   //listaDescripciones =FXCollections.observableArrayList( listaCategorias.stream().map(categoria-> categoria.getDescripcion()).collect(Collectors.toList()));
   //  choiceCategorias.setItems(listaDescripciones);
   
   listaDescripciones =FXCollections.observableArrayList( listaCategorias.stream().map(categoria-> categoria.getDescripcion()).collect(Collectors.toList()));
         listaDescripciones =FXCollections.observableArrayList( listaCategorias.stream().map(categoria-> categoria.getDescripcion()).collect(Collectors.toList()));     
   
   cbxCategorias.setItems( listaDescripciones);
     
          }
     }
     
     
