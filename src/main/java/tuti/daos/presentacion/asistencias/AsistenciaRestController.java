package tuti.daos.presentacion.asistencias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.Link;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestBody;
import tuti.daos.Excepciones.Excepcion;
import tuti.daos.entidades.EntregaAsistencia;
import tuti.daos.servicios.AsistenciaService;

@RestController
@RequestMapping("/asistencia")
@Tag(name = "Asistencia", description = "Asistencias de la ONG")
public class AsistenciaRestController {

	@Autowired
	private AsistenciaService asistenciaServicio;

	// GET TODAS
	@Operation(summary = "Obtiener los datos de todas las asistencias prestadas",
			description = "Retorna los datos completos de todas las asistencias.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Lista de asistencias obtenida"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AsistenciasResponseDTO>> mostrarAsistencias() {

		List<EntregaAsistencia> asistencias = asistenciaServicio.getAll();
		List<AsistenciasResponseDTO> asistenciasDto = new ArrayList<>();

		for (EntregaAsistencia asistencia : asistencias) {
			asistenciasDto.add(buildResponse(asistencia));
		}

		return new ResponseEntity<>(asistenciasDto, HttpStatus.OK);
	}

	// GET POR ID
	@Operation(summary = "Obtiene una asistencia por su ID",
			description = "Retorna los datos de la asistencia si existe.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Asistencia encontrada"),
			@ApiResponse(responseCode = "404", description = "Asistencia no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@GetMapping("/{id}")
	public ResponseEntity<AsistenciasResponseDTO> mostrarAsistencias(@PathVariable Integer id) {

		var asistenciaOpt = asistenciaServicio.getById(id);

		if (asistenciaOpt.isEmpty()) {
			throw new Excepcion("Asistencia", "No existe una asistencia con ese id", 404);
		}

		AsistenciasResponseDTO asistenciasDto = buildResponse(asistenciaOpt.get());
		return ResponseEntity.ok(asistenciasDto);
	}

	@GetMapping("/asistidos/{id}/asistencias")
    public ResponseEntity<List<AsistenciasResponseDTO>> mostrarAsistenciasPorAsistido(@PathVariable Integer id) {
    var lista = asistenciaServicio.findByAsistidoId(id); // implementá este repo/servicio
    var dtoList = lista.stream().map(this::buildResponse).collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
   }  

	// POST CREAR
	@Operation(summary = "Agrega una nueva asistencia",
			description = "Agrega una nueva asistencia al sistema y retorna sus datos completos con enlaces HATEOAS.")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Asistencia creada"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@PostMapping
	public ResponseEntity<AsistenciasResponseDTO> agregarAsistencia(
			@Validated @RequestBody AsistenciasRequestDTO request) {

		AsistenciasResponseDTO respuesta = buildResponse(asistenciaServicio.add(request));

		return ResponseEntity
				.created(URI.create(respuesta.getRequiredLink("self").getHref()))
				.body(respuesta);
	}

	// PUT ACTUALIZAR
	@Operation(summary = "Actualiza los datos de una asistencia existente",
			description = "Actualiza los datos y retorna los datos completos con enlaces HATEOAS.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Asistencia actualizada"),
			@ApiResponse(responseCode = "404", description = "Asistencia no existente"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@PutMapping("/{id}")
	public ResponseEntity<AsistenciasResponseDTO> actualizar(
			@PathVariable Integer id,
			@Validated @RequestBody AsistenciasRequestDTO request) {

		AsistenciasResponseDTO respuesta = buildResponse(asistenciaServicio.update(id, request));
		return ResponseEntity.ok(respuesta);
	}

	// DELETE
	@Operation(summary = "Elimina una asistencia por ID",
			description = "Elimina una asistencia si existe. Si no existe, retorna un error 404.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Asistencia eliminada"),
			@ApiResponse(responseCode = "404", description = "Asistencia no encontrada"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarAsistencia(@PathVariable int id) {
		if (!asistenciaServicio.getById(id).isPresent()) {
			throw new Excepcion("Asistencia", "No existe asistencia con ese ID", 404);
		}

		asistenciaServicio.delete(id);
		return ResponseEntity.ok().build();
	}

	private AsistenciasResponseDTO buildResponse(EntregaAsistencia pojo) {
		try {
			AsistenciasResponseDTO dto = new AsistenciasResponseDTO(pojo);

			// self
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(AsistenciaRestController.class)
							.mostrarAsistencias(pojo.getId()))
					.withSelfRel();
			dto.add(selfLink);

			// S04 – aún no disponible 
			/*
			Link s04Link = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(S04RestController.class)
							.getS04PorAsistencia(pojo.getId()))
					.withRel("s04");
			dto.add(s04Link);
			*/

			return dto;

		} catch (Exception e) {
			throw new Excepcion("Asistencia",
					"Error al construir los enlaces HATEOAS: " + e.getMessage(), 500);
		}
	}
}
