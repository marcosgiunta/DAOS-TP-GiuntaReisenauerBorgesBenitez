package tuti.daos.presentacion.asistencias;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public class AsistenciasRequestDTO {

    @NotNull(message = "El ID de la persona asistida es obligatorio")
    @Positive(message = "El ID del asistido debe ser un número positivo")
    private Integer asistido;

    @Positive(message = "El ID de la ración debe ser un número positivo")
    @NotNull(message = "El ID de la ración entregada es obligatorio")
    private Integer idRacionEntregada;

    @NotNull(message = "La fecha de entrega es obligatoria")
    @PastOrPresent(message = "La fecha de entrega no puede ser futura")
    private Date fechaEntrega;

    public Integer getAsistido() {
        return asistido;
    }

    public void setAsistido(Integer asistido) {
        this.asistido = asistido;
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
