package com.anthony.Models;

/**
 *
 * @author perez
 */
public class EMPLEADO extends SUCURSAL{

    private Integer ID_EMPLEADO;
    private Integer FK_SUCURSAL;
    private String EMP_NOMBRES;
    private String EMP_APELLIDOS;
    private String EMP_CEDULA;
    private String EMP_EMAIL;
    private String EMP_TELEFONO;
    private String EMP_DIRECCION;
    private String EMP_ESTADO;
    private String EMP_CREACION;

    public EMPLEADO() {
    }

    public Integer getID_EMPLEADO() {
        return ID_EMPLEADO;
    }

    public void setID_EMPLEADO(Integer ID_EMPLEADO) {
        this.ID_EMPLEADO = ID_EMPLEADO;
    }

    public Integer getFK_SUCURSAL() {
        return FK_SUCURSAL;
    }

    public void setFK_SUCURSAL(Integer FK_SUCURSAL) {
        this.FK_SUCURSAL = FK_SUCURSAL;
    }

    public String getEMP_NOMBRES() {
        return EMP_NOMBRES;
    }

    public void setEMP_NOMBRES(String EMP_NOMBRES) {
        this.EMP_NOMBRES = EMP_NOMBRES;
    }

    public String getEMP_APELLIDOS() {
        return EMP_APELLIDOS;
    }

    public void setEMP_APELLIDOS(String EMP_APELLIDOS) {
        this.EMP_APELLIDOS = EMP_APELLIDOS;
    }

    public String getEMP_CEDULA() {
        return EMP_CEDULA;
    }

    public void setEMP_CEDULA(String EMP_CEDULA) {
        this.EMP_CEDULA = EMP_CEDULA;
    }

    public String getEMP_EMAIL() {
        return EMP_EMAIL;
    }

    public void setEMP_EMAIL(String EMP_EMAIL) {
        this.EMP_EMAIL = EMP_EMAIL;
    }

    public String getEMP_TELEFONO() {
        return EMP_TELEFONO;
    }

    public void setEMP_TELEFONO(String EMP_TELEFONO) {
        this.EMP_TELEFONO = EMP_TELEFONO;
    }

    public String getEMP_DIRECCION() {
        return EMP_DIRECCION;
    }

    public void setEMP_DIRECCION(String EMP_DIRECCION) {
        this.EMP_DIRECCION = EMP_DIRECCION;
    }

    public String getEMP_ESTADO() {
        return EMP_ESTADO;
    }

    public void setEMP_ESTADO(String EMP_ESTADO) {
        this.EMP_ESTADO = EMP_ESTADO;
    }

    public String getEMP_CREACION() {
        return EMP_CREACION;
    }

    public void setEMP_CREACION(String EMP_CREACION) {
        this.EMP_CREACION = EMP_CREACION;
    }

    
}
