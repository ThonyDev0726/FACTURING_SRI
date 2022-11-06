package com.anthony.Models;

/**
 *
 * @author ThonyDev0726
 *
 */
public class LICENCIAS {

    private Integer ID_LICENCIA;
    private String LIC_CLAVE;
    private String LIC_ESTADOString;

    public LICENCIAS() {
    }

    public Integer getID_LICENCIA() {
        return ID_LICENCIA;
    }

    public void setID_LICENCIA(Integer ID_LICENCIA) {
        this.ID_LICENCIA = ID_LICENCIA;
    }

    public String getLIC_CLAVE() {
        return LIC_CLAVE;
    }

    public void setLIC_CLAVE(String LIC_CLAVE) {
        this.LIC_CLAVE = LIC_CLAVE;
    }

    public String getLIC_ESTADOString() {
        return LIC_ESTADOString;
    }

    public void setLIC_ESTADOString(String LIC_ESTADOString) {
        this.LIC_ESTADOString = LIC_ESTADOString;
    }

}
