package tuti.daos.presentacion.racion;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RacionResDTO extends RepresentationModel<RacionResDTO> {

    private Integer id;
    private Integer stockPreparado;
    private Integer stockRestante;
    private Integer recetaId;

    private Date fechaPreparacion;
    private Date fechaVencimiento;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getFechaPreparacion() {
        return fechaPreparacion;
    }

    public void setFechaPreparacion(Date fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}