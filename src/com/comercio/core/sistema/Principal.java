/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comercio.core.sistema;

import com.comercio.core.controller.CategoriaController;
import com.comercio.core.controller.ProductoController;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Principal extends Application {
    private Stage escenarioPrincipal;
    private final String PAQUETE_VISTA = "/com/comercio/core/view/";
    
    
    public void start(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("Sistema Ventas V 1.0");
       
       //mostrarHelloWorld();
       //mostrarRelacionar();
 //  mostrarVentanaProductos();
   mostrarVentanaCategorias();
        this.escenarioPrincipal.show();
    }

    
 /*  public void mostrarRelacionar(){
        try{
            RelacionarController relacionar = (RelacionarController)cambiarEscena("RelacionarView.fxml",600,400);
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/
    public void mostrarVentanaProductos(){
        try{
            ProductoController productoController= (ProductoController)
                             cambiarEscena("ProductosView.fxml",700,550);
                                    
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void mostrarVentanaCategorias(){
        
        try{
            CategoriaController categoriaController= (CategoriaController)
                                    cambiarEscena("CategoriasView.fxml",600,400);
        }catch (Exception e){
        
        e.printStackTrace();
    }
    }
    //Metodo para cambiar el escenario principal
    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws IOException{
        Initializable resultado = null;
        //Cargador del archivo FXML
        FXMLLoader cargadorFXML = new FXMLLoader();
        //Asignacion del archivo logico
        InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA + fxml);
        //Cargdor de fabrica para cargar el archivo FXML
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        //Direccion de la ruta del archivo FXML
        cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA + fxml));        
        //Creacion de la escena
        Scene escena = new Scene((AnchorPane)cargadorFXML.load(archivo),ancho,alto); 
        //Asignacion de la escena en el escenario principal
        this.escenarioPrincipal.setScene(escena);
        //Ajustar el tamaño del escenario a la escena
        this.escenarioPrincipal.sizeToScene();
        //Retornar el objeto initializable del controlador
        resultado = (Initializable)cargadorFXML.getController();
        return resultado;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
