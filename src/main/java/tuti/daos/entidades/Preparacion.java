package tuti.daos.entidades;
import jakarta.persistence.*;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Preparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPreparacion;

    private Integer stockRacionesRestantes;
    private Integer TotalRacionesPreparadas;
    private boolean eliminado = false;
   
    @Transient
    private Integer caloriasPorRacion;

    
    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    public Receta getReceta() {
        return receta;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaPreparacion() {
        return fechaPreparacion;
    }
    public void setFechaPreparacion(Date fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }

    public Integer getStockRacionesRestantes() {
        return stockRacionesRestantes;
    }
    public void setStockRacionesRestantes(Integer stockRacionesRestantes) {
        this.stockRacionesRestantes = stockRacionesRestantes;
    }
    public Integer getTotalRacionesPreparadas() {
        return TotalRacionesPreparadas;
    }
    public void setTotalRacionesPreparadas(Integer totalRacionesPreparadas) {
        TotalRacionesPreparadas = totalRacionesPreparadas;
    }
	public void setReceta(Receta receta) {
		this.receta = receta;
	}
	
	public boolean isEliminado() {
	    return eliminado;
	}

	public void setEliminado(boolean eliminado) {
	    this.eliminado = eliminado;
	}
	
	public Integer getCaloriasPorRacion() {
	    return caloriasPorRacion;
	}

	public void setCaloriasPorRacion(Integer caloriasPorRacion) {
	    this.caloriasPorRacion = caloriasPorRacion;
	}

}