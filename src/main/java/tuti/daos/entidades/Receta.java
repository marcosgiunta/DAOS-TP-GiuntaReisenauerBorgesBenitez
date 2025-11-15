package tuti.daos.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private boolean eliminada = false; 
   
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Preparacion> preparaciones;

   
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemReceta> ingredientes = new ArrayList<>();
    
    @Transient
    public Integer getCaloriasTotales() {
    	if (ingredientes == null) return 0;
    	return ingredientes.stream().mapToInt(ItemReceta::getCalorias).sum();
    }

    public Integer getId() {
        return id;
    }   
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
    }
    
    public boolean getEliminada() {
        return eliminada;
    }

    public void setEliminada(boolean eliminada) {
    	this.eliminada = eliminada;
    }
    
    public List<ItemReceta> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<ItemReceta> ingrediente) {
    	this.ingredientes = ingrediente;
    }
}
