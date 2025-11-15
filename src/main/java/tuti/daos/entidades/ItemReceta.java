package tuti.daos.entidades;
import jakarta.persistence.*;

@Entity
public class ItemReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double cantidad;
    private Integer calorias;
    private boolean eliminado = false;
    
    @ManyToOne
    @JoinColumn(name = "ingredientes_id")
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    public Integer getCalorias() {
        return calorias;
    }
    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Ingrediente getIngrediente() {
    return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
    
    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

}
