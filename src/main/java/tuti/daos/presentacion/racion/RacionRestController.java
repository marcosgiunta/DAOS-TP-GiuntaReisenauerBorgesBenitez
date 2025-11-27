package tuti.daos.presentacion.racion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import tuti.daos.servicios.RacionService;

@RestController
@RequestMapping("/raciones")
@Validated
@Tag(name = "Raciones", description = "Operaciones sobre el stock de raciones preparadas")
public class RacionRestController {


    @Autowired
    private RacionService racionService;


    // ======================================================
    // LISTAR TODAS
    // ======================================================
    @GetMapping
    public ResponseEntity<List<RacionResDTO>> findAll() {
        List<RacionResDTO> lista = racionService.findAll();

        // agregar links HATEOAS
        lista.forEach(r -> {
            Link selfLink = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(RacionRestController.class)
                            .findById(r.getId()))
                    .withSelfRel();

            r.add(selfLink);
        });

        return ResponseEntity.ok(lista);
    }


    // ======================================================
    // BUSCAR POR ID
    // ======================================================
    @GetMapping("/{id}")
    public ResponseEntity<RacionResDTO> findById(@PathVariable Integer id) {
        RacionResDTO dto = racionService.findById(id);

        // links
        Link allLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(RacionRestController.class)
                        .findAll())
                .withRel("lista_raciones");

        dto.add(allLink);

        return ResponseEntity.ok(dto);
    }


    // ======================================================
    // BUSCAR RACIONES POR RECETA
    // ======================================================
    @GetMapping("/receta/{recetaId}")
    public ResponseEntity<List<RacionResDTO>> findByReceta(@PathVariable Integer recetaId) {
        List<RacionResDTO> lista = racionService.findByRecetaId(recetaId);

        lista.forEach(r -> {
            Link selfLink = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(RacionRestController.class)
                            .findById(r.getId()))
                    .withSelfRel();
            r.add(selfLink);
        });

        return ResponseEntity.ok(lista);
    }


    // ======================================================
    // CREAR RACION
    // ======================================================
    @PostMapping
    public ResponseEntity<RacionResDTO> create(@Valid @RequestBody RacionReqDTO dto) {

        RacionResDTO creada = racionService.create(dto);

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(RacionRestController.class)
                        .findById(creada.getId()))
                .withSelfRel();

        creada.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }


    // ======================================================
    // ACTUALIZAR RACION
    // ======================================================
    @PutMapping("/{id}")
    public ResponseEntity<RacionResDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody RacionReqDTO dto) {

        RacionResDTO actualizada = racionService.update(id, dto);

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(RacionRestController.class)
                        .findById(actualizada.getId()))
                .withSelfRel();

        actualizada.add(selfLink);

        return ResponseEntity.ok(actualizada);
    }


    // ======================================================
    // ELIMINAR (DELETE)
    // ======================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        racionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
