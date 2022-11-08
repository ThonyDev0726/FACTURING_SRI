package com.anthony.Models;

/**
 *
 * @author ThonyDev0726
 */
public class PRODUCTO extends PROVEEDOR {

    private Integer ID_PRODUCTO;
    private Integer FK_SUCURSAL;
    private Integer FK_PROVEEDOR;
    private String PRO_NOMBRE;
    private String PRO_DETALLE;
    private String PRO_CATEGORIA;
    private String PRO_COD_PRINC;
    private String PRO_COD_AUX;
    private String PRO_DETALLE_EXTRA;
    private Integer PRO_STOCK;
    private Double PRO_PRECIO_FABRICA;
    private Integer PRO_GANANCIA;
    private Double PRO_PVP;
    private String PRO_CREACION;
    private String PRO_ESTADO;
    private String PRO_TIPO_IVA;

    public PRODUCTO() {
    }

    public Integer getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(Integer ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public Integer getFK_SUCURSAL() {
        return FK_SUCURSAL;
    }

    public void setFK_SUCURSAL(Integer FK_SUCURSAL) {
        this.FK_SUCURSAL = FK_SUCURSAL;
    }

    public Integer getFK_PROVEEDOR() {
        return FK_PROVEEDOR;
    }

    public void setFK_PROVEEDOR(Integer FK_PROVEEDOR) {
        this.FK_PROVEEDOR = FK_PROVEEDOR;
    }

    public String getPRO_NOMBRE() {
        return PRO_NOMBRE;
    }

    public void setPRO_NOMBRE(String PRO_NOMBRE) {
        this.PRO_NOMBRE = PRO_NOMBRE;
    }

    public String getPRO_DETALLE() {
        return PRO_DETALLE;
    }

    public void setPRO_DETALLE(String PRO_DETALLE) {
        this.PRO_DETALLE = PRO_DETALLE;
    }

    public String getPRO_CATEGORIA() {
        return PRO_CATEGORIA;
    }

    public void setPRO_CATEGORIA(String PRO_CATEGORIA) {
        this.PRO_CATEGORIA = PRO_CATEGORIA;
    }

    public String getPRO_COD_PRINC() {
        return PRO_COD_PRINC;
    }

    public void setPRO_COD_PRINC(String PRO_COD_PRINC) {
        this.PRO_COD_PRINC = PRO_COD_PRINC;
    }

    public String getPRO_COD_AUX() {
        return PRO_COD_AUX;
    }

    public void setPRO_COD_AUX(String PRO_COD_AUX) {
        this.PRO_COD_AUX = PRO_COD_AUX;
    }

    public String getPRO_DETALLE_EXTRA() {
        return PRO_DETALLE_EXTRA;
    }

    public void setPRO_DETALLE_EXTRA(String PRO_DETALLE_EXTRA) {
        this.PRO_DETALLE_EXTRA = PRO_DETALLE_EXTRA;
    }

    public Integer getPRO_STOCK() {
        return PRO_STOCK;
    }

    public void setPRO_STOCK(Integer PRO_STOCK) {
        this.PRO_STOCK = PRO_STOCK;
    }

    public Double getPRO_PRECIO_FABRICA() {
        return PRO_PRECIO_FABRICA;
    }

    public void setPRO_PRECIO_FABRICA(Double PRO_PRECIO_FABRICA) {
        this.PRO_PRECIO_FABRICA = PRO_PRECIO_FABRICA;
    }

    public Integer getPRO_GANANCIA() {
        return PRO_GANANCIA;
    }

    public void setPRO_GANANCIA(Integer PRO_GANANCIA) {
        this.PRO_GANANCIA = PRO_GANANCIA;
    }

    public Double getPRO_PVP() {
        return PRO_PVP;
    }

    public void setPRO_PVP(Double PRO_PVP) {
        this.PRO_PVP = PRO_PVP;
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

    public String getPRO_TIPO_IVA() {
        return PRO_TIPO_IVA;
    }

    public void setPRO_TIPO_IVA(String PRO_TIPO_IVA) {
        this.PRO_TIPO_IVA = PRO_TIPO_IVA;
    }

    

}
