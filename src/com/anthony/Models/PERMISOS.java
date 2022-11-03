package com.anthony.Models;

/**
 *
 * @author ThonyDev0726
 *
 */
public class PERMISOS extends USUARIO{

    private Integer ID_PERMISO;
    private Integer FK_USUARIO;
    private String PER_CLIENTE;
    private String PER_EMPLEADO;
    private String PER_PROVEEDOR;
    private String PER_PRODUCTO;
    private String PER_FACTURACION;
    private String PER_NOTA_DEBITO;
    private String PER_NOTA_CREDITO;
    private String PER_REP_GENERALES;
    private String PER_REP_ESTADISTICOS;
    private String PER_REV_FACTURAS;
    private String PER_REV_NOT_CREDITO;
    private String PER_REV_NOT_DEBITO;
    private String PER_USUARIOS;
    private String PER_PERMISOS;
    private String PER_AJUSTES;
    private String PER_EMPRESA;

    public PERMISOS() {
    }

    public Integer getID_PERMISO() {
        return ID_PERMISO;
    }

    public void setID_PERMISO(Integer ID_PERMISO) {
        this.ID_PERMISO = ID_PERMISO;
    }

    public Integer getFK_USUARIO() {
        return FK_USUARIO;
    }

    public void setFK_USUARIO(Integer FK_USUARIO) {
        this.FK_USUARIO = FK_USUARIO;
    }

    public String getPER_CLIENTE() {
        return PER_CLIENTE;
    }

    public void setPER_CLIENTE(String PER_CLIENTE) {
        this.PER_CLIENTE = PER_CLIENTE;
    }

    public String getPER_EMPLEADO() {
        return PER_EMPLEADO;
    }

    public void setPER_EMPLEADO(String PER_EMPLEADO) {
        this.PER_EMPLEADO = PER_EMPLEADO;
    }

    public String getPER_PROVEEDOR() {
        return PER_PROVEEDOR;
    }

    public void setPER_PROVEEDOR(String PER_PROVEEDOR) {
        this.PER_PROVEEDOR = PER_PROVEEDOR;
    }

    public String getPER_PRODUCTO() {
        return PER_PRODUCTO;
    }

    public void setPER_PRODUCTO(String PER_PRODUCTO) {
        this.PER_PRODUCTO = PER_PRODUCTO;
    }

    public String getPER_FACTURACION() {
        return PER_FACTURACION;
    }

    public void setPER_FACTURACION(String PER_FACTURACION) {
        this.PER_FACTURACION = PER_FACTURACION;
    }

    public String getPER_NOTA_DEBITO() {
        return PER_NOTA_DEBITO;
    }

    public void setPER_NOTA_DEBITO(String PER_NOTA_DEBITO) {
        this.PER_NOTA_DEBITO = PER_NOTA_DEBITO;
    }

    public String getPER_NOTA_CREDITO() {
        return PER_NOTA_CREDITO;
    }

    public void setPER_NOTA_CREDITO(String PER_NOTA_CREDITO) {
        this.PER_NOTA_CREDITO = PER_NOTA_CREDITO;
    }

    public String getPER_REP_GENERALES() {
        return PER_REP_GENERALES;
    }

    public void setPER_REP_GENERALES(String PER_REP_GENERALES) {
        this.PER_REP_GENERALES = PER_REP_GENERALES;
    }

    public String getPER_REP_ESTADISTICOS() {
        return PER_REP_ESTADISTICOS;
    }

    public void setPER_REP_ESTADISTICOS(String PER_REP_ESTADISTICOS) {
        this.PER_REP_ESTADISTICOS = PER_REP_ESTADISTICOS;
    }

    public String getPER_REV_FACTURAS() {
        return PER_REV_FACTURAS;
    }

    public void setPER_REV_FACTURAS(String PER_REV_FACTURAS) {
        this.PER_REV_FACTURAS = PER_REV_FACTURAS;
    }

    public String getPER_REV_NOT_CREDITO() {
        return PER_REV_NOT_CREDITO;
    }

    public void setPER_REV_NOT_CREDITO(String PER_REV_NOT_CREDITO) {
        this.PER_REV_NOT_CREDITO = PER_REV_NOT_CREDITO;
    }

    public String getPER_REV_NOT_DEBITO() {
        return PER_REV_NOT_DEBITO;
    }

    public void setPER_REV_NOT_DEBITO(String PER_REV_NOT_DEBITO) {
        this.PER_REV_NOT_DEBITO = PER_REV_NOT_DEBITO;
    }

    public String getPER_USUARIOS() {
        return PER_USUARIOS;
    }

    public void setPER_USUARIOS(String PER_USUARIOS) {
        this.PER_USUARIOS = PER_USUARIOS;
    }

    public String getPER_PERMISOS() {
        return PER_PERMISOS;
    }

    public void setPER_PERMISOS(String PER_PERMISOS) {
        this.PER_PERMISOS = PER_PERMISOS;
    }

    public String getPER_AJUSTES() {
        return PER_AJUSTES;
    }

    public void setPER_AJUSTES(String PER_AJUSTES) {
        this.PER_AJUSTES = PER_AJUSTES;
    }

    public String getPER_EMPRESA() {
        return PER_EMPRESA;
    }

    public void setPER_EMPRESA(String PER_EMPRESA) {
        this.PER_EMPRESA = PER_EMPRESA;
    }

    @Override
    public String toString() {
        return "PERMISOS{" + "ID_PERMISO=" + ID_PERMISO + ", FK_USUARIO=" + FK_USUARIO + ", PER_CLIENTE=" + PER_CLIENTE + ", PER_EMPLEADO=" + PER_EMPLEADO + ", PER_PROVEEDOR=" + PER_PROVEEDOR + ", PER_PRODUCTO=" + PER_PRODUCTO + ", PER_FACTURACION=" + PER_FACTURACION + ", PER_NOTA_DEBITO=" + PER_NOTA_DEBITO + ", PER_NOTA_CREDITO=" + PER_NOTA_CREDITO + ", PER_REP_GENERALES=" + PER_REP_GENERALES + ", PER_REP_ESTADISTICOS=" + PER_REP_ESTADISTICOS + ", PER_REV_FACTURAS=" + PER_REV_FACTURAS + ", PER_REV_NOT_CREDITO=" + PER_REV_NOT_CREDITO + ", PER_REV_NOT_DEBITO=" + PER_REV_NOT_DEBITO + ", PER_USUARIOS=" + PER_USUARIOS + ", PER_PERMISOS=" + PER_PERMISOS + ", PER_AJUSTES=" + PER_AJUSTES + ", PER_EMPRESA=" + PER_EMPRESA + '}';
    }

}
