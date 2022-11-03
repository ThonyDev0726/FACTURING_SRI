package com.anthony.Models;

/**
 *
 * @author perez
 */
public class AUDITORIA {

    private Integer ID_AUDITORIA;
    private String AUD_DESC;
    private String AUD_IP;
    private String AUD_FECHA;
    private String AUD_HORA;
    private String AUD_USUARIO;

    public AUDITORIA() {
    }

    public Integer getID_AUDITORIA() {
        return ID_AUDITORIA;
    }

    public void setID_AUDITORIA(Integer ID_AUDITORIA) {
        this.ID_AUDITORIA = ID_AUDITORIA;
    }

    public String getAUD_DESC() {
        return AUD_DESC;
    }

    public void setAUD_DESC(String AUD_DESC) {
        this.AUD_DESC = AUD_DESC;
    }

    public String getAUD_IP() {
        return AUD_IP;
    }

    public void setAUD_IP(String AUD_IP) {
        this.AUD_IP = AUD_IP;
    }

    public String getAUD_FECHA() {
        return AUD_FECHA;
    }

    public void setAUD_FECHA(String AUD_FECHA) {
        this.AUD_FECHA = AUD_FECHA;
    }

    public String getAUD_HORA() {
        return AUD_HORA;
    }

    public void setAUD_HORA(String AUD_HORA) {
        this.AUD_HORA = AUD_HORA;
    }

    public String getAUD_USUARIO() {
        return AUD_USUARIO;
    }

    public void setAUD_USUARIO(String AUD_USUARIO) {
        this.AUD_USUARIO = AUD_USUARIO;
    }
    
    
}
