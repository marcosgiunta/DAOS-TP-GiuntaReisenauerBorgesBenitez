package tuti.daos.presentacion.asistido;

import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

//DTO para crear o actualizar un asistido

public class AsistidoRequestDTO {

    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreCompleto;

    @Min(value = 7000000, message = "El DNI no puede ser menor a 7000000")
    @Max(value = 99999999, message = "El DNI no puede ser mayor a 99999999")
    @Positive(message = "El ID debe ser un número positivo")
    private Integer dni;

    
    private String domicilio;

    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private Date fechaNacimiento;

    
    private Integer ciudadId;

    @Max(value = 130, message = "La edad no puede ser mayor a 130 años")
    @NotNull(message = "La edad es obligatoria")
    @Positive(message = "La edad debe ser un número positivo")
    private Integer edad;

    // --- Getters y Setters ---
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

}
