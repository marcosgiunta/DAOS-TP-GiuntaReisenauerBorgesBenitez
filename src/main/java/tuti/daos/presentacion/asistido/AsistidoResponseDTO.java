package tuti.daos.presentacion.asistido;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import tuti.daos.entidades.Asistido;


//DTO para devolver datos de un asistido


public class AsistidoResponseDTO extends RepresentationModel<AsistidoResponseDTO> {

	private Integer id;
	private String nombreCompleto;
	private int dni;
    private String domicilio;
    private Date fechaNacimiento;
    private Integer ciudadId;
    private int edad;

    
    public AsistidoResponseDTO() {}

	public AsistidoResponseDTO(Asistido asistido)  {
		super();
		this.nombreCompleto = asistido.getNombre();
		this.dni = asistido.getDni();
		this.id = asistido.getId();
        this.fechaNacimiento = asistido.getFechaNacimiento();
        this.domicilio = asistido.getDomicilio();
        this.ciudadId = asistido.getCiudad() != null ? asistido.getCiudad().getId() : null;
        this.edad = asistido.getEdad();
	}

	public Integer getId() {
		return id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public int getDni() {
		return dni;
	}

    public String getDomicilio() {
        return domicilio;
    }
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public Integer getCiudadId() {
        return ciudadId;
    }
    public Integer getEdad() {
        return edad;
    }

	@Override
	public String toString() {
		return "ID:" + id + " - " + "NOMBRE COMPLETO:" + nombreCompleto + " - " + "DNI:" + dni  + " - " + "FECHA DE NACIMIENTO:" + fechaNacimiento  + " - " + "EDAD:" + edad + " - " + "CIUDAD:" + ciudadId + " - " + "DOMICILIO:" + domicilio;
	}

}

