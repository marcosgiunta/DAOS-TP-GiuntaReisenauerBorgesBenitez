package tuti.daos.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import tuti.daos.accesoDatos.RacionRepositorio;
import tuti.daos.accesoDatos.RecetaRepository;
import tuti.daos.entidades.Preparacion;
import tuti.daos.entidades.Receta;
import tuti.daos.Excepciones.Excepcion;
import tuti.daos.presentacion.racion.RacionReqDTO;
import tuti.daos.presentacion.racion.RacionResDTO;

@Service
public class RacionServiceImpl implements RacionService {

    @Autowired
    private RacionRepositorio racionRepositorio;

    @Autowired
    private RecetaRepository recetaRepository;

    // ======================================================
    // LISTAR TODO (solo no eliminadas)
    // ======================================================
    @Override
    public List<RacionResDTO> findAll() {
        List<Preparacion> lista = racionRepositorio.findByEliminadoFalse();
        List<RacionResDTO> respuesta = new ArrayList<>();

        for (Preparacion p : lista) {
            respuesta.add(mapToDTO(p));
        }

        return respuesta;
    }

    // ======================================================
    // BUSCAR POR ID (no devuelve eliminadas)
    // ======================================================
    @Override
    public RacionResDTO findById(Integer id) {

        Preparacion p = racionRepositorio.findById(id)
                .filter(prep -> !prep.isEliminado())
                .orElseThrow(() -> new Excepcion(
                        "404",
                        "Ración no encontrada",
                        400   
                ));

        return mapToDTO(p);
    }

    // ======================================================
    // CREAR RACION
    // ======================================================
    @Override
    public RacionResDTO create(RacionReqDTO dto) {

        // Validar receta
        Receta receta = recetaRepository.findById(dto.getRecetaId())
                .orElseThrow(() -> new Excepcion(
                        "404",
                        "La receta indicada no existe",
                        400
                ));

        // Validar fechas
        if (dto.getFechaVencimiento().before(dto.getFechaPreparacion())) {
            throw new Excepcion(
                    "400",
                    "La fecha de vencimiento no puede ser anterior a la fecha de preparación",
                    400
            );
        }

        Preparacion p = new Preparacion();
        p.setReceta(receta);
        p.setFechaPreparacion(dto.getFechaPreparacion());
        p.setFechaVencimiento(dto.getFechaVencimiento());
        p.setTotalRacionesPreparadas(dto.getStockPreparado());
        p.setStockRacionesRestantes(dto.getStockPreparado()); // stock inicial
        p.setEliminado(false);

        Preparacion guardada = racionRepositorio.save(p);

        return mapToDTO(guardada);
    }

    // ======================================================
    // ACTUALIZAR RACION (no permite actualizar eliminadas)
    // ======================================================
    @Override
    public RacionResDTO update(Integer id, RacionReqDTO dto) {

        Preparacion p = racionRepositorio.findById(id)
                .filter(prep -> !prep.isEliminado())
                .orElseThrow(() -> new Excepcion(
                        "404",
                        "Ración no encontrada",
                        400
                ));

        Receta receta = recetaRepository.findById(dto.getRecetaId())
                .orElseThrow(() -> new Excepcion(
                        "404",
                        "La receta indicada no existe",
                        400
                ));

        if (dto.getFechaVencimiento().before(dto.getFechaPreparacion())) {
            throw new Excepcion(
                    "400",
                    "La fecha de vencimiento no puede ser anterior a la fecha de preparación",
                    400
            );
        }

        p.setReceta(receta);
        p.setFechaPreparacion(dto.getFechaPreparacion());
        p.setFechaVencimiento(dto.getFechaVencimiento());
        p.setTotalRacionesPreparadas(dto.getStockPreparado());
        p.setStockRacionesRestantes(dto.getStockPreparado());

        Preparacion guardada = racionRepositorio.save(p);

        return mapToDTO(guardada);
    }

    // ======================================================
    // BORRAR 
    // ======================================================
    @Override
    public void delete(Integer id) {

        Preparacion p = racionRepositorio.findById(id)
                .filter(prep -> !prep.isEliminado())
                .orElseThrow(() -> new Excepcion(
                        "404",
                        "Ración no encontrada",
                        400
                ));

        p.setEliminado(true);
        racionRepositorio.save(p);
    }

    // ======================================================
    // BUSCAR POR RECETA (no devuelve eliminadas)
    // ======================================================
    @Override
    public List<RacionResDTO> findByRecetaId(Integer recetaId) {

        List<Preparacion> lista = racionRepositorio.findByRecetaIdAndEliminadoFalse(recetaId);
        List<RacionResDTO> respuesta = new ArrayList<>();

        for (Preparacion p : lista) {
            respuesta.add(mapToDTO(p));
        }

        return respuesta;
    }

    // ======================================================
    // MAPEO ENTIDAD → DTO
    // ======================================================
    private RacionResDTO mapToDTO(Preparacion p) {

        RacionResDTO dto = new RacionResDTO();

        dto.setId(p.getId());
        dto.setRecetaId(p.getReceta().getId());
        dto.setFechaPreparacion(p.getFechaPreparacion());
        dto.setFechaVencimiento(p.getFechaVencimiento());
        dto.setStockPreparado(p.getTotalRacionesPreparadas());
        dto.setStockRestante(p.getStockRacionesRestantes());

        return dto;
    }
}
