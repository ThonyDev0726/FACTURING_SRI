package com.anthony.Models;

public class FACTURA_DESCRIPCION {

    private Integer ID_DESCRIPCION;
    private Integer FK_FACTURA;
    private Integer FK_PRODUCTO;
    private Integer DET_CANTIDAD;
    private Double DET_PRECIO;
    private Double DET_TOTAL;

    public FACTURA_DESCRIPCION() {
    }

    public Integer getID_DESCRIPCION() {
        return ID_DESCRIPCION;
    }

    public void setID_DESCRIPCION(Integer ID_DESCRIPCION) {
        this.ID_DESCRIPCION = ID_DESCRIPCION;
    }

    public Integer getFK_FACTURA() {
        return FK_FACTURA;
    }

    public void setFK_FACTURA(Integer FK_FACTURA) {
        this.FK_FACTURA = FK_FACTURA;
    }

    public Integer getFK_PRODUCTO() {
        return FK_PRODUCTO;
    }

    public void setFK_PRODUCTO(Integer FK_PRODUCTO) {
        this.FK_PRODUCTO = FK_PRODUCTO;
    }

    public Integer getDET_CANTIDAD() {
        return DET_CANTIDAD;
    }

    public void setDET_CANTIDAD(Integer DET_CANTIDAD) {
        this.DET_CANTIDAD = DET_CANTIDAD;
    }

    public Double getDET_PRECIO() {
        return DET_PRECIO;
    }

    public void setDET_PRECIO(Double DET_PRECIO) {
        this.DET_PRECIO = DET_PRECIO;
    }

    public Double getDET_TOTAL() {
        return DET_TOTAL;
    }

    public void setDET_TOTAL(Double DET_TOTAL) {
        this.DET_TOTAL = DET_TOTAL;
    }

}
