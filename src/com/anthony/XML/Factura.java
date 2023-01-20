/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//getDetIva
package com.anthony.XML;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @author gato
 */

public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idFactura;
    private Integer facNumero;
    private Date facFecha;
    private Date facFechaSustento;
    private Date facFechaAutorizacion;
    private BigDecimal facSubtotal;
    private BigDecimal facIva;
    private BigDecimal facTotal;
    private String facEstado;
    private String facTipo;
    private BigDecimal facAbono;
    private BigDecimal facSaldo;
    private String facDescripcion;
    private Integer facNumProforma;

    private String tipodocumento;
    private String tipoDocumentoMod;
    private String puntoemision;
    private String codestablecimiento;
    private String facNumeroText;
    private String facClaveAcceso;
    private String facClaveAutorizacion;
    private String facTipoIdentificadorComprobador;
    private BigDecimal facDescuento;
    private String facCodIce;
    private String facCodIva;
    private BigDecimal facTotalBaseCero;
    private BigDecimal facTotalBaseGravaba;
    private String codigoPorcentaje;
    private String facPorcentajeIva;
    private String facMoneda;
    private BigDecimal facPlazo;
    private BigDecimal facSaldoAmortizado;
    private String facUnidadTiempo;
    private String estadosri;
    private String mensajesri;
    private String facpath;
    private String facMsmInfoSri;
    private Integer facNumNotaEntrega;
    private String facNotaEntregaProcess;

   

    public Factura() {
    }

    public Factura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getFacNumero() {
        return facNumero;
    }

    public void setFacNumero(Integer facNumero) {
        this.facNumero = facNumero;
    }

    public Date getFacFecha() {
        return facFecha;
    }

    public void setFacFecha(Date facFecha) {
        this.facFecha = facFecha;
    }

    public BigDecimal getFacSubtotal() {
        return facSubtotal;
    }

    public void setFacSubtotal(BigDecimal facSubtotal) {
        this.facSubtotal = facSubtotal;
    }

    public BigDecimal getFacIva() {
        return facIva;
    }

    public void setFacIva(BigDecimal facIva) {
        this.facIva = facIva;
    }

    public BigDecimal getFacTotal() {
        return facTotal;
    }

    public void setFacTotal(BigDecimal facTotal) {
        this.facTotal = facTotal;
    }

    public String getFacEstado() {
        return facEstado;
    }

    public void setFacEstado(String facEstado) {
        this.facEstado = facEstado;
    }

    public String getFacTipo() {
        return facTipo;
    }

    public void setFacTipo(String facTipo) {
        this.facTipo = facTipo;
    }

    public BigDecimal getFacAbono() {
        return facAbono;
    }

    public void setFacAbono(BigDecimal facAbono) {
        this.facAbono = facAbono;
    }

    public BigDecimal getFacSaldo() {
        return facSaldo;
    }

    public void setFacSaldo(BigDecimal facSaldo) {
        this.facSaldo = facSaldo;
    }

    public String getFacDescripcion() {
        return facDescripcion;
    }

    public void setFacDescripcion(String facDescripcion) {
        this.facDescripcion = facDescripcion;
    }

    public Integer getFacNumProforma() {
        return facNumProforma;
    }

    public void setFacNumProforma(Integer facNumProforma) {
        this.facNumProforma = facNumProforma;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getPuntoemision() {
        return puntoemision;
    }

    public void setPuntoemision(String puntoemision) {
        this.puntoemision = puntoemision;
    }

    public String getCodestablecimiento() {
        return codestablecimiento;
    }

    public void setCodestablecimiento(String codestablecimiento) {
        this.codestablecimiento = codestablecimiento;
    }

    public String getFacNumeroText() {
        return facNumeroText;
    }

    public void setFacNumeroText(String facNumeroText) {
        this.facNumeroText = facNumeroText;
    }

    public String getFacTipoIdentificadorComprobador() {
        return facTipoIdentificadorComprobador;
    }

    public void setFacTipoIdentificadorComprobador(String facTipoIdentificadorComprobador) {
        this.facTipoIdentificadorComprobador = facTipoIdentificadorComprobador;
    }

    public BigDecimal getFacDescuento() {
        return facDescuento;
    }

    public void setFacDescuento(BigDecimal facDescuento) {
        this.facDescuento = facDescuento;
    }

    public String getFacCodIce() {
        return facCodIce;
    }

    public void setFacCodIce(String facCodIce) {
        this.facCodIce = facCodIce;
    }

    public String getFacCodIva() {
        return facCodIva;
    }

    public void setFacCodIva(String facCodIva) {
        this.facCodIva = facCodIva;
    }

    public BigDecimal getFacTotalBaseCero() {
        return facTotalBaseCero;
    }

    public void setFacTotalBaseCero(BigDecimal facTotalBaseCero) {
        this.facTotalBaseCero = facTotalBaseCero;
    }

    public BigDecimal getFacTotalBaseGravaba() {
        return facTotalBaseGravaba;
    }

    public void setFacTotalBaseGravaba(BigDecimal facTotalBaseGravaba) {
        this.facTotalBaseGravaba = facTotalBaseGravaba;
    }

    public String getCodigoPorcentaje() {
        return codigoPorcentaje;
    }

    public void setCodigoPorcentaje(String codigoPorcentaje) {
        this.codigoPorcentaje = codigoPorcentaje;
    }

    public String getFacPorcentajeIva() {
        return facPorcentajeIva;
    }

    public void setFacPorcentajeIva(String facPorcentajeIva) {
        this.facPorcentajeIva = facPorcentajeIva;
    }

    public String getFacMoneda() {
        return facMoneda;
    }

    public void setFacMoneda(String facMoneda) {
        this.facMoneda = facMoneda;
    }

   
    public BigDecimal getFacPlazo() {
        return facPlazo;
    }

    public void setFacPlazo(BigDecimal facPlazo) {
        this.facPlazo = facPlazo;
    }

    public String getFacUnidadTiempo() {
        return facUnidadTiempo;
    }

    public void setFacUnidadTiempo(String facUnidadTiempo) {
        this.facUnidadTiempo = facUnidadTiempo;
    }

    public String getEstadosri() {
        return estadosri;
    }

    public void setEstadosri(String estadosri) {
        this.estadosri = estadosri;
    }

    public String getMensajesri() {
        return mensajesri;
    }

    public void setMensajesri(String mensajesri) {
        this.mensajesri = mensajesri;
    }

    public Date getFacFechaAutorizacion() {
        return facFechaAutorizacion;
    }

    public void setFacFechaAutorizacion(Date facFechaAutorizacion) {
        this.facFechaAutorizacion = facFechaAutorizacion;
    }

 

    public String getFacClaveAutorizacion() {
        return facClaveAutorizacion;
    }

    public void setFacClaveAutorizacion(String facClaveAutorizacion) {
        this.facClaveAutorizacion = facClaveAutorizacion;
    }

    public String getFacpath() {
        return facpath;
    }

    public void setFacpath(String facpath) {
        this.facpath = facpath;
    }

    public String getTipoDocumentoMod() {
        return tipoDocumentoMod;
    }

    public void setTipoDocumentoMod(String tipoDocumentoMod) {
        this.tipoDocumentoMod = tipoDocumentoMod;
    }

    public Date getFacFechaSustento() {
        return facFechaSustento;
    }

    public void setFacFechaSustento(Date facFechaSustento) {
        this.facFechaSustento = facFechaSustento;
    }

    

    public BigDecimal getFacSaldoAmortizado() {
        return facSaldoAmortizado;
    }

    public void setFacSaldoAmortizado(BigDecimal facSaldoAmortizado) {
        this.facSaldoAmortizado = facSaldoAmortizado;
    }

    public String getFacClaveAcceso() {
        return facClaveAcceso;
    }

    public void setFacClaveAcceso(String facClaveAcceso) {
        this.facClaveAcceso = facClaveAcceso;
    }

    public Integer getFacNumNotaEntrega() {
        return facNumNotaEntrega;
    }

    public void setFacNumNotaEntrega(Integer facNumNotaEntrega) {
        this.facNumNotaEntrega = facNumNotaEntrega;
    }

   

    public String getFacMsmInfoSri() {
        return facMsmInfoSri;
    }

    public void setFacMsmInfoSri(String facMsmInfoSri) {
        this.facMsmInfoSri = facMsmInfoSri;
    }

  

    public String getFacNotaEntregaProcess() {
        return facNotaEntregaProcess;
    }

    public void setFacNotaEntregaProcess(String facNotaEntregaProcess) {
        this.facNotaEntregaProcess = facNotaEntregaProcess;
    }



   
}
