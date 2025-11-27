package tuti.daos.presentacion.asistencias;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AsistenciasRequestDTO {

    @NotNull(message = "El ID de la persona asistida es obligatorio")
    private Integer idAsistido;

    @Positive(message = "El ID de la ración debe ser un número positivo")
    @NotNull(message = "El ID de la ración entregada es obligatorio")
    private Integer idRacionEntregada;

    @NotNull(message = "La fecha de entrega es obligatoria")
    private Date fechaEntrega;

    public Integer getIdAsistido() {
        return idAsistido;
    }

    public void setIdAsistido(Integer idAsistido) {
        this.idAsistido = idAsistido;
    }

    public Integer getIdRacionEntregada() {
        return idRacionEntregada;
    }

    public void setIdRacionEntregada(Integer idRacionEntregada) {
        this.idRacionEntregada = idRacionEntregada;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}
