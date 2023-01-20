/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anthony.XML;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author gato Detid_iva
 */
public class DetalleFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idDetalle;
    private BigDecimal detCantidad;
    private String codigoProducto;
    private String detDescripcion;
    private BigDecimal valorUnit;
    private BigDecimal detSubtotal;
    private BigDecimal detTotal;
    private BigDecimal detIva;
    private BigDecimal detid_iva;
    private BigDecimal detdescuento;
    private BigDecimal dettarifa;

    private BigDecimal desnew;

    public DetalleFactura() {
    }

    public DetalleFactura(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public DetalleFactura(BigDecimal detCantidad,
            String detDescripcion,
            BigDecimal detSubtotal,
            BigDecimal detTotal) {
        this.detCantidad = detCantidad;
        this.detDescripcion = detDescripcion;
        this.detSubtotal = detSubtotal;
        this.detTotal = detTotal;
    }

    public DetalleFactura(BigDecimal detCantidad, String codigoProducto, String detDescripcion,
            BigDecimal valorUnit, BigDecimal detSubtotal, BigDecimal detTotal, BigDecimal detIva, BigDecimal detid_iva,
            BigDecimal dettarifa, BigDecimal descuento,
            BigDecimal desnew) {
        this.detCantidad = detCantidad;
        this.codigoProducto = codigoProducto;
        this.detDescripcion = detDescripcion;
        this.detSubtotal = detSubtotal;
        this.detTotal = detTotal;
        this.detIva = detIva;
        this.valorUnit = valorUnit;
        this.detid_iva = detid_iva;
        this.dettarifa = dettarifa;
        this.detdescuento = descuento;
        //ADDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
        this.desnew = desnew;
    }

    public DetalleFactura(BigDecimal detCantidad, String codigoProducto, String detDescripcion, BigDecimal detSubtotal, BigDecimal detTotal, BigDecimal detIva, BigDecimal detid_iva, BigDecimal dettarifa, BigDecimal descuento) {
        this.detCantidad = detCantidad;
        this.codigoProducto = codigoProducto;
        this.detDescripcion = detDescripcion;
        this.detSubtotal = detSubtotal;
        this.detTotal = detTotal;
        this.detIva = detIva;
        this.detid_iva = detid_iva;
        this.dettarifa = dettarifa;
        this.detdescuento = descuento;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public BigDecimal getDetCantidad() {
        return detCantidad;
    }

    public void setDetCantidad(BigDecimal detCantidad) {
        this.detCantidad = detCantidad;
    }

    public String getDetDescripcion() {
        return detDescripcion;
    }

    public void setDetDescripcion(String detDescripcion) {
        this.detDescripcion = detDescripcion;
    }

    public BigDecimal getDetSubtotal() {
        return detSubtotal;
    }

    public void setDetSubtotal(BigDecimal detSubtotal) {
        this.detSubtotal = detSubtotal;
    }

    public BigDecimal getDetTotal() {
        return detTotal;
    }

    public void setDetTotal(BigDecimal detTotal) {
        this.detTotal = detTotal;
    }

    public BigDecimal getDetIva() {
        return detIva;
    }

    public void setDetIva(BigDecimal detIva) {
        this.detIva = detIva;
    }

    public BigDecimal getDetid_iva() {
        return detid_iva;
    }

    public void setDetid_iva(BigDecimal detid_iva) {
        this.detid_iva = detid_iva;
    }

    public BigDecimal getDettarifa() {
        return dettarifa;
    }

    public void setDettarifa(BigDecimal dettarifa) {
        this.dettarifa = dettarifa;
    }

    public BigDecimal getDedescuento() {
        return detdescuento;
    }

    public void setDetdescuento(BigDecimal descuento) {
        this.detdescuento = descuento;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public BigDecimal getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(BigDecimal valorUnit) {
        this.valorUnit = valorUnit;
    }

    public BigDecimal getDesnew() {
        return desnew;
    }

    public void setDesnew(BigDecimal desnew) {
        this.desnew = desnew;
    }
}
