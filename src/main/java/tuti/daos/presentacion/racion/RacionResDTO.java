package tuti.daos.presentacion.racion;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

public class RacionResDTO extends RepresentationModel<RacionResDTO> {

    private Integer id;

    // cantidad producida (stock preparado)
    private Integer stockPreparado;

    // cantidad restante (solo lectura en la API)
    private Integer stockRestante;

    private Integer recetaId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPreparacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaVencimiento;

    // GETTERS y SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockPreparado() {
        return stockPreparado;
    }

    public void setStockPreparado(Integer stockPreparado) {
        this.stockPreparado = stockPreparado;
    }

    public Integer getStockRestante() {
        return stockRestante;
    }

    public void setStockRestante(Integer stockRestante) {
        this.stockRestante = stockRestante;
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
