package tuti.daos.presentacion.receta;

public class RecetaResDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private boolean eliminada = false;
    
    public RecetaResDTO() {}

	public RecetaResDTO(Integer id, String nombre, String descripcion, boolean eliminada) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.eliminada = eliminada;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEliminada() {
		return eliminada;
	}

	public void setEliminada(boolean eliminada) {
		this.eliminada = eliminada;
	}
    
    
}
