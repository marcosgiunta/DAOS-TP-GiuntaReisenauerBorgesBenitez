package tuti.daos.entidades;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class EntregaAsistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fecha;
    private Integer cantidadRaciones;

    @ManyToOne
    @JoinColumn(name = "preparacion_id")
    private Preparacion preparacion;


    public Preparacion getPreparacion() {
        return preparacion;
    }
    public void setPreparacion(Preparacion preparacion) {
        this.preparacion = preparacion;
    }
  

    public Integer getId() {
        return id;
    }   
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public Integer getCantidadRaciones() {
        return cantidadRaciones;
    }
    public void setCantidadRaciones(Integer cantidadRaciones) {
        this.cantidadRaciones = cantidadRaciones;
    }
 

    
}