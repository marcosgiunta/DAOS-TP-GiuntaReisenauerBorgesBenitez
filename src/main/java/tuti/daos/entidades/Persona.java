package tuti.daos.entidades;
import jakarta.persistence.*;
import java.util.Date;

/*
 * Esta clase representa a una persona (Asistido y voluntario heredan de Persona)
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
	private Integer dni;
    
	private String domicilio;
    
	private String nombreCompleto;
    
	private Date fechaNacimiento;

    private Integer edad;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getDni() {
        return dni;
    }
    public void setDni(Integer dni) {
        this.dni = dni;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public String getNombre() {
        return nombreCompleto;
    }
    public void setNombre(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    
}
