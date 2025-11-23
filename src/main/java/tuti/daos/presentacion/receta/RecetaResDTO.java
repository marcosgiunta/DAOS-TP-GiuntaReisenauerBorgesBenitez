package tuti.daos.presentacion.receta;

import org.springframework.hateoas.RepresentationModel;

public class RecetaResDTO extends RepresentationModel<RecetaResDTO> {
    private Integer id;
    private String nombre;
    private Double pesoRacion;
    private Integer caloriasRacion;
    
    public RecetaResDTO() {}

	public RecetaResDTO(Integer id, String nombre, Double pesoRacion, Integer caloriasRacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pesoRacion = pesoRacion;
		this.caloriasRacion = caloriasRacion;
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