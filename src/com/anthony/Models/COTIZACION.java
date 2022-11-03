package com.anthony.Models;

/**
 *
 * @author perez
 */
public class COTIZACION {

    private Integer ID_COTIZACION;
    private Integer FK_USUARIO;
    private Integer FK_CLIENTE;
    private Integer COT_FECHA;
    private Integer COT_CODIGO;
    private Integer COT_SUBTOTAL;
    private Integer COT_TOTAL;
    private Integer COT_ESTADO;

    public COTIZACION() {
    }

    public Integer getID_COTIZACION() {
        return ID_COTIZACION;
    }

    public void setID_COTIZACION(Integer ID_COTIZACION) {
        this.ID_COTIZACION = ID_COTIZACION;
    }

    public Integer getFK_USUARIO() {
        return FK_USUARIO;
    }

    public void setFK_USUARIO(Integer FK_USUARIO) {
        this.FK_USUARIO = FK_USUARIO;
    }

    public Integer getFK_CLIENTE() {
        return FK_CLIENTE;
    }

    public void setFK_CLIENTE(Integer FK_CLIENTE) {
        this.FK_CLIENTE = FK_CLIENTE;
    }

    public Integer getCOT_FECHA() {
        return COT_FECHA;
    }

    public void setCOT_FECHA(Integer COT_FECHA) {
        this.COT_FECHA = COT_FECHA;
    }

    public Integer getCOT_CODIGO() {
        return COT_CODIGO;
    }

    public void setCOT_CODIGO(Integer COT_CODIGO) {
        this.COT_CODIGO = COT_CODIGO;
    }

    public Integer getCOT_SUBTOTAL() {
        return COT_SUBTOTAL;
    }

    public void setCOT_SUBTOTAL(Integer COT_SUBTOTAL) {
        this.COT_SUBTOTAL = COT_SUBTOTAL;
    }

    public Integer getCOT_TOTAL() {
        return COT_TOTAL;
    }

    public void setCOT_TOTAL(Integer COT_TOTAL) {
        this.COT_TOTAL = COT_TOTAL;
    }

    public Integer getCOT_ESTADO() {
        return COT_ESTADO;
    }

    public void setCOT_ESTADO(Integer COT_ESTADO) {
        this.COT_ESTADO = COT_ESTADO;
    }

    
    
}
