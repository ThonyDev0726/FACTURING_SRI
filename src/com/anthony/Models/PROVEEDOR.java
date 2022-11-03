package com.anthony.Models;

/**
 *
 * @author perez
 */
public class PROVEEDOR extends SUCURSAL {

    private Integer ID_PROVEEDOR;
    private Integer FK_SUCURSAL;
    private String PRO_EMPRESA;
    private String PRO_CONTACTO;
    private String PRO_RUC;
    private String PRO_TELEFONO;
    private String PRO_EMAIL;
    private String PRO_DIRECCION;
    private String PRO_WEB;
    private String PRO_CREACION;
    private String PRO_ESTADO;

    public PROVEEDOR() {
    }

    public Integer getID_PROVEEDOR() {
        return ID_PROVEEDOR;
    }

    public void setID_PROVEEDOR(Integer ID_PROVEEDOR) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
    }

    public Integer getFK_SUCURSAL() {
        return FK_SUCURSAL;
    }

    public void setFK_SUCURSAL(Integer FK_SUCURSAL) {
        this.FK_SUCURSAL = FK_SUCURSAL;
    }

    public String getPRO_EMPRESA() {
        return PRO_EMPRESA;
    }

    public void setPRO_EMPRESA(String PRO_EMPRESA) {
        this.PRO_EMPRESA = PRO_EMPRESA;
    }

    public String getPRO_CONTACTO() {
        return PRO_CONTACTO;
    }

    public void setPRO_CONTACTO(String PRO_CONTACTO) {
        this.PRO_CONTACTO = PRO_CONTACTO;
    }

    public String getPRO_RUC() {
        return PRO_RUC;
    }

    public void setPRO_RUC(String PRO_RUC) {
        this.PRO_RUC = PRO_RUC;
    }

    public String getPRO_TELEFONO() {
        return PRO_TELEFONO;
    }

    public void setPRO_TELEFONO(String PRO_TELEFONO) {
        this.PRO_TELEFONO = PRO_TELEFONO;
    }

    public String getPRO_EMAIL() {
        return PRO_EMAIL;
    }

    public void setPRO_EMAIL(String PRO_EMAIL) {
        this.PRO_EMAIL = PRO_EMAIL;
    }

    public String getPRO_DIRECCION() {
        return PRO_DIRECCION;
    }

    public void setPRO_DIRECCION(String PRO_DIRECCION) {
        this.PRO_DIRECCION = PRO_DIRECCION;
    }

    public String getPRO_WEB() {
        return PRO_WEB;
    }

    public void setPRO_WEB(String PRO_WEB) {
        this.PRO_WEB = PRO_WEB;
    }

    public String getPRO_CREACION() {
        return PRO_CREACION;
    }

    public void setPRO_CREACION(String PRO_CREACION) {
        this.PRO_CREACION = PRO_CREACION;
    }

    public String getPRO_ESTADO() {
        return PRO_ESTADO;
    }

    public void setPRO_ESTADO(String PRO_ESTADO) {
        this.PRO_ESTADO = PRO_ESTADO;
    }

}
