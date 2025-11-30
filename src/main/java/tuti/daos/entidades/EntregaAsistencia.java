package tuti.daos.entidades;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class EntregaAsistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asistido_id", nullable = false)
    private Asistido asistido;

    private Integer idRacionEntregada;
    
    private LocalDate fechaEntrega;

    // @ManyToOne
    // @JoinColumn(name = "preparacion_id")
    // private Preparacion preparacion;

    // @ManyToOne
    // @JoinColumn(name = "familia_id")
    // private Familia familia;

    //voluntario id en la base de datos aparece como voluntario_id = null 
    //esto sucede porque para el TP no se pidio en las historias de usuario
    //que se guarde el voluntario que realiza la entrega, por lo tanto no se guarda
    //pero se deja el campo para que en un futuro se pueda implementar 
    // @ManyToOne
    // @JoinColumn(name = "voluntario_id")
    // private Voluntario voluntario;

    // public Preparacion getPreparacion() {
    //     return preparacion;
    // }
    // public void setPreparacion(Preparacion preparacion) {
    //     this.preparacion = preparacion;
    // }
    // public Familia getFamilia() {
    //     return familia;
    // }

    // public void setFamilia(Familia familia) {
    //     this.familia = familia;
    // }

    public Integer getId() {
        return id;
    }

    public Asistido getAsistido() {
        return asistido;
    }

    public void setAsistido(Asistido asistido) {
        this.asistido = asistido;
    }

    public Integer getIdRacionEntregada() {
        return idRacionEntregada;
    }

    public void setIdRacionEntregada(Integer idRacionEntregada) {
        this.idRacionEntregada = idRacionEntregada;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
 
}