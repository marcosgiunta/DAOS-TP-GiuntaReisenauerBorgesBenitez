package tuti.daos.presentacion.asistencias;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import tuti.daos.entidades.EntregaAsistencia;

public class AsistenciasResponseDTO extends RepresentationModel<AsistenciasResponseDTO> {

    private Integer id;
    private Integer asistido;
    private Integer idRacionEntregada;
    private LocalDate fechaEntrega;

    public AsistenciasResponseDTO() {}

    public AsistenciasResponseDTO(EntregaAsistencia asistencia)  {
        this.id = asistencia.getId();
        this.asistido = asistencia.getAsistido() != null ? asistencia.getAsistido().getId() : null;
        this.idRacionEntregada = asistencia.getIdRacionEntregada();
        this.fechaEntrega = asistencia.getFechaEntrega();
    }

    public Integer getId() {
        return id;
    }

    public Integer getAsistido() {
        return asistido;
    }

    public Integer getIdRacionEntregada() {
        return idRacionEntregada;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }
}
