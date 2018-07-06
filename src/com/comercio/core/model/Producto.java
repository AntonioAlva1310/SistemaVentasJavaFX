

package com.comercio.core.model;

import java.io.Serializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity 
@Table (name= "producto")
@NamedQueries ({@NamedQuery(name="Producto.findAll",query="from Producto")})
public class Producto implements Serializable {
    private  IntegerProperty codigoProducto;
    private final StringProperty descripcion;
    private final DoubleProperty precioUnitario;
    private final DoubleProperty precioMayorista;
    private final IntegerProperty existencias;
    private final StringProperty imagen;
    private Categoria categoria;
    
    
    public Producto() {
        this.codigoProducto = new SimpleIntegerProperty();
        this.descripcion = new SimpleStringProperty("");
        this.precioUnitario = new SimpleDoubleProperty();
        this.precioMayorista= new SimpleDoubleProperty();
        this.existencias= new SimpleIntegerProperty();
        this.imagen= new SimpleStringProperty("");
    }

    public Producto(int codigoProducto, String descripcion, double precioUnitario, double precioMayorista, int existencias,String imagen) {
        this.codigoProducto = new SimpleIntegerProperty(codigoProducto);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.precioUnitario= new SimpleDoubleProperty(precioUnitario);
        this.precioMayorista= new SimpleDoubleProperty(precioMayorista);
        this.existencias=new SimpleIntegerProperty(existencias);
        this.imagen= new SimpleStringProperty(imagen);
        
    }

    @Override
    public String toString() {
        return "Producto{" + "codigoProducto=" + codigoProducto + ", descripcion=" + descripcion + ", precioUnitario=" + precioUnitario + ", precioMayorista=" + precioMayorista + ", existencias=" + existencias + ", imagen=" + imagen + ", categoria=" + categoria + '}';
    }

   
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name="codigo_producto")
      public int getCodigoProducto(){
          return codigoProducto.get();     
      }
      public void setCodigoProducto(int codigoProducto){
          
          this.codigoProducto.set(codigoProducto);
      }
      public IntegerProperty codigoProducto(){
          
          return this.codigoProducto;
      }
      
      @Column(name="descripcion")
      public String getDescripcion(){
          
          return descripcion.get();
      }
      public void setDescripcion(String descripcion){
          this.descripcion.set(descripcion); 
      
      }
      public StringProperty descripcion(){
          return this.descripcion;
      }
      @Column(name="precio_unitario")
      public double getPrecioUnitario(){
          
          return precioUnitario.get();
      }
      public void setPrecioUnitario(double precioUnitario){
          this.precioUnitario.set(precioUnitario); 
      
      }
      public DoubleProperty precioUnitario(){
          return this.precioUnitario;
      }
      
      @Column(name="precio_mayorista")
      public double getPrecioMayorista(){
          
          return precioMayorista.get();
      }
      public void setPrecioMayorista(double precioMayorista){
          this.precioMayorista.set(precioMayorista); 
      
      }
      public DoubleProperty precioMayorista(){
          return this.precioMayorista;
      }
      @Column(name="existencias")
      public int getExistencias(){
          return existencias.get();
          
      }
      public void setExistencias (int existencias){
          
         this.existencias.set(existencias);
      }
      
      public IntegerProperty existencias(){
          return this.existencias;
      }
      @Column(name="imagen")
      public String getImagen(){
          
          return imagen.get();
      }
      public void setImagen(String imagen){
          this.imagen.set(imagen); 
      
      }
      public StringProperty imagen(){
          return this.imagen;
      }
      
      @ManyToOne(fetch= FetchType.EAGER)
      @JoinColumn(name="codigo_categoria", updatable=false, insertable=true, nullable=false)
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    
}
    
    
     

