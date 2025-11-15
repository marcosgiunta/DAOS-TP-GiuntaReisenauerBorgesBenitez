package tuti.daos.presentacion.Ciudad;

import org.springframework.hateoas.Links;
import tuti.daos.entidades.Ciudad;
import io.swagger.v3.oas.annotations.media.Schema;


import org.springframework.hateoas.RepresentationModel;



public class CiudadResponseDTO extends RepresentationModel<CiudadResponseDTO>{

    private int id;
    private String nombre;

    public CiudadResponseDTO(Ciudad pojo) {
        super();
        this.nombre = pojo.getNombre();
		this.id=pojo.getId();
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	//Esto oculta los links en los ejemplos de respuestas que muestra swagger (para dejar mas limpio el ejemplo). 
	//Pero sí se devuelven los links en la respuesta cuando se ejecuta el servicio realmente
	@Schema(hidden = true)
    @Override
    public Links getLinks() {
        return super.getLinks();
    }
    
}
