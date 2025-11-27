package tuti.daos.presentacion.asistencias;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import tuti.daos.entidades.EntregaAsistencia;

public class AsistenciasResponseDTO extends RepresentationModel<AsistenciasResponseDTO> {

    private Integer id;
    private Integer idAsistido;
    private Integer idRacionEntregada;
    private Date fechaEntrega;

    public AsistenciasResponseDTO() {}

    public AsistenciasResponseDTO(EntregaAsistencia asistencia)  {
        this.id = asistencia.getId();
        this.idAsistido = asistencia.getIdAsistido();
        this.idRacionEntregada = asistencia.getIdRacionEntregada();
        this.fechaEntrega = asistencia.getFechaEntrega();
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdAsistido() {
        return idAsistido;
    }

    public Integer getIdRacionEntregada() {
        return idRacionEntregada;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }
}
