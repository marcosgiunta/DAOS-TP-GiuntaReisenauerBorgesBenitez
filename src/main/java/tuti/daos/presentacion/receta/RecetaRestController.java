package tuti.daos.presentacion.receta;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import tuti.daos.servicios.RecetaService;

@RestController
@RequestMapping("/recetas")
public class RecetaRestController {
	@Autowired
	private RecetaService recetaService;
	
	// RUTA GET RECETAS
	@GetMapping
	public ResponseEntity<List<EntityModel<RecetaResDTO>>> findAll() {
		List<EntityModel<RecetaResDTO>> recetas = recetaService.findAll().stream()
				.map(this::addLinks)
				.collect(Collectors.toList());
		return ResponseEntity.ok(recetas);
	}
	
	// RUTA GET RECETA
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<RecetaResDTO>> findById(@PathVariable Integer id) {
		RecetaResDTO receta = recetaService.findById(id);
		return ResponseEntity.ok(addLinks(receta));
	}
	
	// RUTA POST
	@PostMapping
	public ResponseEntity<EntityModel<RecetaResDTO>> create(@Valid @RequestBody RecetaReqDTO recetaReqDTO) {
		RecetaResDTO nuevaReceta = recetaService.create(recetaReqDTO);
		EntityModel<RecetaResDTO> recurso = addLinks(nuevaReceta);
		return ResponseEntity.created(URI.create("/recetas/" + nuevaReceta.getId())).body(recurso);
	}
	
	
	// RUTA PUT
	@PutMapping("/{id}")
	public ResponseEntity<EntityModel<RecetaResDTO>> update(@PathVariable Integer id, @Valid @RequestBody RecetaReqDTO recetaReqDTO) {
		RecetaResDTO recetaActualizada = recetaService.update(id, recetaReqDTO);
		return ResponseEntity.ok(addLinks(recetaActualizada));
	}

	
	// RUTA DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		recetaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	private EntityModel<RecetaResDTO> addLinks(RecetaResDTO receta) {
		EntityModel<RecetaResDTO> recurso =EntityModel.of(receta);
		
		// LINK A SI MISMO 
		recurso.add(WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(RecetaRestController.class)
				.findById(receta.getId())
		).withSelfRel());
		
		// LINK PARA CONSULTAR RACIONES DE LA RECETA (ESTO FALTA COMPLETAR CON LO DE RACIONES)
		/*recurso.add(Link.of(
				WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn(
								//tuti.daos.presentacion.racion.RacionRestController.class
						).findByRecetaId(receta.getId())
				).toString(),
				"preparaciones"
		));
		*/
		
		return recurso;
		
	}
	
}
