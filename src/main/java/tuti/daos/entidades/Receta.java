package tuti.daos.entidades;

// import java.util.ArrayList;
// import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    @Column(name = "peso_racion", nullable = false)
    private Double pesoRacion;
    
    @Column(name = "calorias_racion", nullable = false)
    private Integer caloriasRacion;
   
    /*
     * PARA COMPLETAR CUANDO SE CREE LA ENTIDAD RACION
    	@OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    	private List<Racion> raciones = new ArrayList<>();
    */

    public Receta() {}

	public Receta(Integer id, String nombre, Double pesoRacion, Integer caloriasRacion) {
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
