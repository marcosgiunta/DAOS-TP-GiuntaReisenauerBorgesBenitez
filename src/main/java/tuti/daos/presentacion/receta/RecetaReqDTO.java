package tuti.daos.presentacion.receta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RecetaReqDTO {
    @NotBlank(message = "El nombre de la receta es obligatorio")
    private String nombre;
    
    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso tiene que ser positivo")
    private Double pesoRacion;
    
    @NotNull(message = "Las calorias son obligatorias")
    @Positive(message = "Las calorías tienen que ser positivas")
    private Integer caloriasRacion;
    
    public RecetaReqDTO() {}
    
	public RecetaReqDTO(@NotBlank(message = "El nombre de la receta es obligatorio") String nombre,
			@NotNull(message = "El peso es obligatorio") @Positive(message = "El peso tiene que ser positivo") Double pesoRacion,
			@NotNull(message = "Las calorias son obligatorias") @Positive(message = "Las calorías tienen que ser positivas") Integer caloriasRacion) {
		super();
		this.nombre = nombre;
		this.pesoRacion = pesoRacion;
		this.caloriasRacion = caloriasRacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPesoRacion() {
		return pesoRacion;
	}

	public void setPesoRacion(Double pesoRacion) {
		this.pesoRacion = pesoRacion;
	}

	public Integer getCaloriasRacion() {
		return caloriasRacion;
	}

	public void setCaloriasRacion(Integer caloriasRacion) {
		this.caloriasRacion = caloriasRacion;
	}
    
    
}