package tuti.daos.presentacion.racion;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

public class RacionReqDTO {

    @NotNull
    @Positive
    private Integer stockPreparado;

    @NotNull
    private Integer recetaId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPreparacion;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaVencimiento;

    // GETTERS y SETTERS

    public Integer getStockPreparado() {
        return stockPreparado;
    }

    public void setStockPreparado(Integer stockPreparado) {
        this.stockPreparado = stockPreparado;
    }

    public Integer getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Integer recetaId) {
        this.recetaId = recetaId;
    }

    public Date getFechaPreparacion() {
        return fechaPreparacion;
    }

    public void setFechaPreparacion(Date fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
