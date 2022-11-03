package com.anthony.Models;

/**
 *
 * @author ThonyDev0726
 */
public class FACTURA_TOTALIDAD {

    private Integer ID_TOTALIDAD;
    private Integer FK_FACTURA;
    private Double FAC_SUB_0;
    private Double FAC_SUB_12;
    private Double FAC_OBJ_IVA;
    private Double FAC_EXC_IVA;
    private Integer FAC_DESCUENTO;
    private Double FAC_IVA_12;
    private Double FAC_TOTAL_PAGAR;

    public FACTURA_TOTALIDAD() {
    }

    public Integer getID_TOTALIDAD() {
        return ID_TOTALIDAD;
    }

    public void setID_TOTALIDAD(Integer ID_TOTALIDAD) {
        this.ID_TOTALIDAD = ID_TOTALIDAD;
    }

    public Integer getFK_FACTURA() {
        return FK_FACTURA;
    }

    public void setFK_FACTURA(Integer FK_FACTURA) {
        this.FK_FACTURA = FK_FACTURA;
    }

    public Double getFAC_SUB_0() {
        return FAC_SUB_0;
    }

    public void setFAC_SUB_0(Double FAC_SUB_0) {
        this.FAC_SUB_0 = FAC_SUB_0;
    }

    public Double getFAC_SUB_12() {
        return FAC_SUB_12;
    }

    public void setFAC_SUB_12(Double FAC_SUB_12) {
        this.FAC_SUB_12 = FAC_SUB_12;
    }

    public Double getFAC_OBJ_IVA() {
        return FAC_OBJ_IVA;
    }

    public void setFAC_OBJ_IVA(Double FAC_OBJ_IVA) {
        this.FAC_OBJ_IVA = FAC_OBJ_IVA;
    }

    public Double getFAC_EXC_IVA() {
        return FAC_EXC_IVA;
    }

    public void setFAC_EXC_IVA(Double FAC_EXC_IVA) {
        this.FAC_EXC_IVA = FAC_EXC_IVA;
    }

    public Integer getFAC_DESCUENTO() {
        return FAC_DESCUENTO;
    }

    public void setFAC_DESCUENTO(Integer FAC_DESCUENTO) {
        this.FAC_DESCUENTO = FAC_DESCUENTO;
    }

    public Double getFAC_IVA_12() {
        return FAC_IVA_12;
    }

    public void setFAC_IVA_12(Double FAC_IVA_12) {
        this.FAC_IVA_12 = FAC_IVA_12;
    }

    public Double getFAC_TOTAL_PAGAR() {
        return FAC_TOTAL_PAGAR;
    }

    public void setFAC_TOTAL_PAGAR(Double FAC_TOTAL_PAGAR) {
        this.FAC_TOTAL_PAGAR = FAC_TOTAL_PAGAR;
    }

}
