package com.anthony.Models;

/**
 *
 * @author perez
 */
public class SUCURSAL extends EMPRESA{

    private Integer ID_SUCURSAL;
    private Integer FK_EMPRESA;
    private String SUC_NOMBRE;
    private String SUC_TELEFONO;
    private String SUC_EMAIL;
    private String SUC_DIRECCION;
    private String SUC_ESTADO;

    public SUCURSAL() {
    }

    public Integer getID_SUCURSAL() {
        return ID_SUCURSAL;
    }

    public void setID_SUCURSAL(Integer ID_SUCURSAL) {
        this.ID_SUCURSAL = ID_SUCURSAL;
    }

    public Integer getFK_EMPRESA() {
        return FK_EMPRESA;
    }

    public void setFK_EMPRESA(Integer FK_EMPRESA) {
        this.FK_EMPRESA = FK_EMPRESA;
    }

    public String getSUC_NOMBRE() {
        return SUC_NOMBRE;
    }

    public void setSUC_NOMBRE(String SUC_NOMBRE) {
        this.SUC_NOMBRE = SUC_NOMBRE;
    }

    public String getSUC_TELEFONO() {
        return SUC_TELEFONO;
    }

    public void setSUC_TELEFONO(String SUC_TELEFONO) {
        this.SUC_TELEFONO = SUC_TELEFONO;
    }

    public String getSUC_EMAIL() {
        return SUC_EMAIL;
    }

    public void setSUC_EMAIL(String SUC_EMAIL) {
        this.SUC_EMAIL = SUC_EMAIL;
    }

    public String getSUC_DIRECCION() {
        return SUC_DIRECCION;
    }

    public void setSUC_DIRECCION(String SUC_DIRECCION) {
        this.SUC_DIRECCION = SUC_DIRECCION;
    }

    public String getSUC_ESTADO() {
        return SUC_ESTADO;
    }

    public void setSUC_ESTADO(String SUC_ESTADO) {
        this.SUC_ESTADO = SUC_ESTADO;
    }

    

}
