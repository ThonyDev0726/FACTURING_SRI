package com.anthony.Models;

public class FACTURA extends EMPLEADO {

    private Integer ID_FACTURA;
    private Integer FK_EMPLEADO;
    private Integer FK_CLIENTE;
    private Integer FK_SUCURSAL;
    private String FAC_FECHA;
    private String FAC_HORA;
    private String FAC_CODIGO;
    private String FAC_COD_AUT;
    private String FAC_RUTA;
    private String FAC_ESTADO;
    private String CLI_NOMBRES;
    private String CLI_APELLIDOS;

    public FACTURA() {
    }

    public Integer getID_FACTURA() {
        return ID_FACTURA;
    }

    public void setID_FACTURA(Integer ID_FACTURA) {
        this.ID_FACTURA = ID_FACTURA;
    }

    public Integer getFK_EMPLEADO() {
        return FK_EMPLEADO;
    }

    public void setFK_EMPLEADO(Integer FK_EMPLEADO) {
        this.FK_EMPLEADO = FK_EMPLEADO;
    }

    public Integer getFK_CLIENTE() {
        return FK_CLIENTE;
    }

    public void setFK_CLIENTE(Integer FK_CLIENTE) {
        this.FK_CLIENTE = FK_CLIENTE;
    }

    public Integer getFK_SUCURSAL() {
        return FK_SUCURSAL;
    }

    public void setFK_SUCURSAL(Integer FK_SUCURSAL) {
        this.FK_SUCURSAL = FK_SUCURSAL;
    }

    public String getFAC_FECHA() {
        return FAC_FECHA;
    }

    public void setFAC_FECHA(String FAC_FECHA) {
        this.FAC_FECHA = FAC_FECHA;
    }

    public String getFAC_HORA() {
        return FAC_HORA;
    }

    public void setFAC_HORA(String FAC_HORA) {
        this.FAC_HORA = FAC_HORA;
    }

    public String getFAC_CODIGO() {
        return FAC_CODIGO;
    }

    public void setFAC_CODIGO(String FAC_CODIGO) {
        this.FAC_CODIGO = FAC_CODIGO;
    }

    public String getFAC_COD_AUT() {
        return FAC_COD_AUT;
    }

    public void setFAC_COD_AUT(String FAC_COD_AUT) {
        this.FAC_COD_AUT = FAC_COD_AUT;
    }

    public String getFAC_RUTA() {
        return FAC_RUTA;
    }

    public void setFAC_RUTA(String FAC_RUTA) {
        this.FAC_RUTA = FAC_RUTA;
    }

    public String getFAC_ESTADO() {
        return FAC_ESTADO;
    }

    public void setFAC_ESTADO(String FAC_ESTADO) {
        this.FAC_ESTADO = FAC_ESTADO;
    }

    public String getCLI_NOMBRES() {
        return CLI_NOMBRES;
    }

    public void setCLI_NOMBRES(String CLI_NOMBRES) {
        this.CLI_NOMBRES = CLI_NOMBRES;
    }

    public String getCLI_APELLIDOS() {
        return CLI_APELLIDOS;
    }

    public void setCLI_APELLIDOS(String CLI_APELLIDOS) {
        this.CLI_APELLIDOS = CLI_APELLIDOS;
    }

}
