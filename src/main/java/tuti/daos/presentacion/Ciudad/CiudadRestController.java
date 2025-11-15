package tuti.daos.presentacion.Ciudad;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tuti.daos.entidades.Ciudad;
import tuti.daos.servicios.CiudadService;


@RestController
@RequestMapping("/ciudades")
public class CiudadRestController {

    @Autowired
    private CiudadService ciudadServicio;

    @GetMapping("/{id}")
    public ResponseEntity<CiudadResponseDTO> getCiudad(@PathVariable Integer id) {
        
        Ciudad ciudad = ciudadServicio.buscarPorId(id);

        CiudadResponseDTO dto = new CiudadResponseDTO(ciudad);

        // Agregamos el link HATEOAS a sí misma
        Link selfLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CiudadRestController.class)
                        .getCiudad(id)
        ).withSelfRel();

        dto.add(selfLink);
        return ResponseEntity.ok(dto);
    }

    
    
}
