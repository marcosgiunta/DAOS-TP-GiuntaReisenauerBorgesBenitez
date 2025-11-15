package tuti.daos.presentacion.asistido;

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
import org.springframework.web.bind.annotation.RequestBody;
import tuti.daos.Excepciones.Excepcion;
import tuti.daos.entidades.Asistido;
import tuti.daos.presentacion.Ciudad.CiudadRestController;
import tuti.daos.servicios.AsistidoService;

@RestController
@RequestMapping("/asistido")
@Tag(name = "Asistido", description = "Asistido API")
public class AsistidoRestController {

	@Autowired
	private AsistidoService asistidoServicio;

	@Operation(summary = "Obtiene los datos de todos los asistidos ", description = "Retorna los datos completos de todos los asistidos en el sistema con sus respectivos enlaces HATEOAS.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Lista de asistidos obtenida"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})

	// GET trae todos los asistidos
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AsistidoResponseDTO>> mostrarAsistidos() {

		List<Asistido> asistidos = asistidoServicio.getAll();
		List<AsistidoResponseDTO> asistidosDto = new ArrayList<AsistidoResponseDTO>();

		for (Asistido asistido : asistidos) {
			asistidosDto.add(buildResponse(asistido));
		}

		return new ResponseEntity<List<AsistidoResponseDTO>>(asistidosDto, HttpStatus.OK);

	}

	@Operation(summary = "Obtiene un asistido por ID", description = "Retorna los datos completos del asistido si existe. si no existe, retorna un error 400.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Asistido encontrado"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})

	// GET trae un asistido por id
	@GetMapping("/{id}")
	
	public ResponseEntity<AsistidoResponseDTO> mostrarAsistido(@PathVariable int id) {

		if (!asistidoServicio.getById(id).isPresent()) {
			throw new Excepcion("Asistido", "No existe un asistido con ese id", 400);
		}

		Asistido asistido = asistidoServicio.getById(id).get();
		AsistidoResponseDTO asistidoDto = buildResponse(asistido);

		return ResponseEntity.ok(asistidoDto);
	}

	@Operation(summary = "Elimina un asistido por ID", description = "Elimina un asistido si existe.si no existe, retorna un error 400.")

	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Asistido eliminado"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})

	// DELETE elimina un asistido por id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarAsistido(@PathVariable int id) {
		if (!asistidoServicio.getById(id).isPresent()) {
			throw new Excepcion("Asistido", "No existe un asistido con ese id", 400);
		}

		asistidoServicio.delete(id);

		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Agrega un nuevo asistido", description = "Agrega un nuevo asistido al sistema y retorna sus datos completos con enlaces HATEOAS.")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Asistido creado"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})

	// POST agrega un nuevo asistido
	@PostMapping
	public ResponseEntity<AsistidoResponseDTO> agregarAsistido(@Validated @RequestBody AsistidoRequestDTO request) {

		AsistidoResponseDTO respuesta = buildResponse(asistidoServicio.add(request));

		return ResponseEntity
				.created(URI.create(respuesta.getRequiredLink("self").getHref()))
				.body(respuesta);

	}

	@Operation(summary = "Actualiza un asistido existente", description = "Actualiza los datos de un asistido existente y retorna sus datos completos con enlaces HATEOAS.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Asistido actualizado"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos"),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	})

	// PUT actualiza un asistido existente
	@PutMapping("/{id}")
	public ResponseEntity<AsistidoResponseDTO> actualizar(
			@PathVariable Integer id,
			@Validated @RequestBody AsistidoRequestDTO request) {

		AsistidoResponseDTO respuesta = buildResponse(asistidoServicio.update(id, request));

		return ResponseEntity.ok(respuesta);
	}

	// Construye el DTO de respuesta con enlaces HATEOAS
	private AsistidoResponseDTO buildResponse(Asistido pojo) {
		try {
			AsistidoResponseDTO dto = new AsistidoResponseDTO(pojo);

			Link selfLink = WebMvcLinkBuilder.linkTo(AsistidoRestController.class)
					.slash(pojo.getId())
					.withSelfRel();

			Link ciudadLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CiudadRestController.class)
					.getCiudad(pojo.getCiudad().getId()))
					.withRel("ciudad");
			dto.add(selfLink);
			dto.add(ciudadLink);
			return dto;
		} catch (Exception e) {
			throw new Excepcion("Ciudad", "Error al construir los enlaces HATEOAS: " + e.getMessage(), 500);
		}
	}

}
