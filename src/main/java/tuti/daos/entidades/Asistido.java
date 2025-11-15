package tuti.daos.entidades;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.persistence.*;  

/*
 * Esta clase representa a un Asistido - hereda de Persona)
 */

@Entity
public class Asistido  extends Persona {

	//Fecha en que se registra al asistido
	@PastOrPresent(message = "La fecha no puede ser posterior a la actual")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaRegistro;

    //Nro de la Familia a la que pertenece el Asistido (ID de Familia)
	//@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "familia_id") 
    //private Familia familia;

	@ManyToOne
    @JoinColumn(name = "ciudad_id", nullable = false)
    private Ciudad ciudad;

    //Este campo se utiliza para el borrado lógico
	//private Boolean deshabilitado = false;
    
	
	public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

   // public Boolean getDeshabilitado() {
		//return deshabilitado;
	//}
    
	//public void setDeshabilitado(Boolean deshabilitado) {
		//this.deshabilitado = deshabilitado;
	//}
	
	//public Familia getFamilia() {
		//return familia;
	//}

	//public void setFamilia(Familia familia) {
		//this.familia = familia;
	//}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

}
