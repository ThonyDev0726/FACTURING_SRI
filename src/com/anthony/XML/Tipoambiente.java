/*
puntoventa
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anthony.XML;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Darwin
 */
public class Tipoambiente {

    private static final long serialVersionUID = 1L;
    
    private Integer codTipoambiente;
//  PRUEBA
    private String amCodigo = "1";
//  PRODUCCION
    //private String amCodigo = "2";
    
//  PRUEBA
    private String amDescripcion = "PRUEBA";
//  PRODUCCION
    //private String amDescripcion = "PRODUCCION";

    private Boolean amEstado = Boolean.TRUE;
    private Integer amIdEmpresa = 1;
//  PRODUCCION    
    //private String amUsuariosri = "PRODUCCION";
    //private String amUrlsri = "https://cel.sri.gob.ec";
//  PRUEBA
    private String amUsuariosri = "PRUEBA";
    private String amUrlsri = "https://celcer.sri.gob.ec";
    
    private String amDirReportes = "REPORTES";
    //private String amDirFirma = "wilson_fernando_garces_silva.p12";
    private String amDirFirma = "";
    //private String amDirBaseArchivos = System.getProperty("user.home") + "\\Documents\\DOCUMENTOSRI";
    private String amDirBaseArchivos = "";

//  private String amDirBaseArchivos="D:\\DOCUMENTOSRI";
    
    private String amDirXml = "XML";
    private String amFirmados = "FIRMADOS";
    private String amTrasmitidos = "TRASMITIDOS";
    private String amDevueltos = "DEVUELTOS";
    private String amAutorizados = "AUTORIZADOS";
    private String amEnviocliente = "ENVIARCLIENTE";
    private String amNoAutorizados = "NOAUTORIZADOS";
    /*CLAVE DE LA FIRMA ELECTRONICA*/
    private String amClaveAccesoSri = "Fer48473";
    private String amGenerados = "GENERADOS";
    private String amRazonSocial = "WILSON FERNANDO GARCÉS SILVA";
    private String amNombreComercial = "WILGAS";
    private String amRuc = "0602543951001";
    private String amTipoEmision = "1";
    private String amDireccionMatriz = "Av. Lizarzaburo / Saint Amand Montrond";
    private String llevarContabilidad = "NO";
    private String amEstab = "001";
    private String amPtoemi = "001";
    private String amNroContribuyente = "";
    private String amUnidadDisco = "D://";
    private String amFolderFirma = "FIRMAS";
    private String amDirAts = "";
    private String amCiudad = "RIOBAMBA";
    private String regimen = "CONTRIBUYENTE RÉGIMEN MICROEMPRESAS";

    public Tipoambiente() {
        try{
            Connection conn= conexion.ObtenerConexion(); // esta es la verificación de la conexión con mysql
            Statement consulta=conn.createStatement(); // crea una variable que se encargue del código de sql
            
            ResultSet r= consulta.executeQuery("select * from datos_sri");
            while(r.next()){
                String ambiente=r.getString(2);
                if(ambiente.equals("PRUEBA")){
                    amDescripcion="PRUEBA";
                    amCodigo = "1";
                    amUsuariosri = "PRUEBA";
                    amUrlsri = "https://celcer.sri.gob.ec";
                }else{
                    amDescripcion="PRODUCCION";
                    amCodigo = "2";
                    amUsuariosri = "PRODUCCION";
                    amUrlsri = "https://cel.sri.gob.ec";
                }
                amRazonSocial=(r.getString(3));
                amRuc=(r.getString(4));
                amClaveAccesoSri =(r.getString(5));
                amNombreComercial=(r.getString(6));
                amDireccionMatriz=(r.getString(7));
                amCiudad=(r.getString(8));
                amDirFirma=(r.getString(9));
                amDirBaseArchivos=(r.getString(10));
                amEstab=(r.getString(11));
                amPtoemi=(r.getString(12));
                amNroContribuyente=(r.getString(13));
                regimen=(r.getString(14));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERROR AL CARGAR EL AMBIENTE") ; // esto aparece cuando ya existe un código por lo cual no se guarda la info.
            System.out.println(e);
        }
    }
    
    public void Regimen(String regimen) {
        this.regimen = regimen;
    }
    
    public String getRegimen() {
        return regimen;
    }

    public Tipoambiente(Integer codTipoambiente) {
        this.codTipoambiente = codTipoambiente;
    }

    public Integer getCodTipoambiente() {
        return codTipoambiente;
    }

    public void setCodTipoambiente(Integer codTipoambiente) {
        this.codTipoambiente = codTipoambiente;
    }

    public String getAmCodigo() {
        return amCodigo;
    }

    public void setAmCodigo(String amCodigo) {
        this.amCodigo = amCodigo;
    }

    public String getAmDescripcion() {
        return amDescripcion;
    }

    public void setAmDescripcion(String amDescripcion) {
        this.amDescripcion = amDescripcion;
    }

    public Boolean getAmEstado() {
        return amEstado;
    }

    public void setAmEstado(Boolean amEstado) {
        this.amEstado = amEstado;
    }

    public Integer getAmIdEmpresa() {
        return amIdEmpresa;
    }

    public void setAmIdEmpresa(Integer amIdEmpresa) {
        this.amIdEmpresa = amIdEmpresa;
    }

    public String getAmUsuariosri() {
        return amUsuariosri;
    }

    public void setAmUsuariosri(String amUsuariosri) {
        this.amUsuariosri = amUsuariosri;
    }

    public String getAmUrlsri() {
        return amUrlsri;
    }

    public void setAmUrlsri(String amUrlsri) {
        this.amUrlsri = amUrlsri;
    }

    public String getAmDirReportes() {
        return amDirReportes;
    }

    public void setAmDirReportes(String amDirReportes) {
        this.amDirReportes = amDirReportes;
    }

    public String getAmDirFirma() {
        return amDirFirma;
    }

    public void setAmDirFirma(String amDirFirma) {
        this.amDirFirma = amDirFirma;
    }

    public String getAmDirBaseArchivos() {
        return amDirBaseArchivos;
    }

    public void setAmDirBaseArchivos(String amDirBaseArchivos) {
        this.amDirBaseArchivos = amDirBaseArchivos;
    }

    public String getAmDirXml() {
        return amDirXml;
    }

    public void setAmDirXml(String amDirXml) {
        this.amDirXml = amDirXml;
    }

    public String getAmFirmados() {
        return amFirmados;
    }

    public void setAmFirmados(String amFirmados) {
        this.amFirmados = amFirmados;
    }

    public String getAmTrasmitidos() {
        return amTrasmitidos;
    }

    public void setAmTrasmitidos(String amTrasmitidos) {
        this.amTrasmitidos = amTrasmitidos;
    }

    public String getAmDevueltos() {
        return amDevueltos;
    }

    public void setAmDevueltos(String amDevueltos) {
        this.amDevueltos = amDevueltos;
    }

    public String getAmAutorizados() {
        return amAutorizados;
    }

    public void setAmAutorizados(String amAutorizados) {
        this.amAutorizados = amAutorizados;
    }

    public String getAmNoAutorizados() {
        return amNoAutorizados;
    }

    public void setAmNoAutorizados(String amNoAutorizados) {
        this.amNoAutorizados = amNoAutorizados;
    }

    public String getAmClaveAccesoSri() {
        return amClaveAccesoSri;
    }

    public void setAmClaveAccesoSri(String amClaveAccesoSri) {
        this.amClaveAccesoSri = amClaveAccesoSri;
    }

    public String getAmGenerados() {
        return amGenerados;
    }

    public void setAmGenerados(String amGenerados) {
        this.amGenerados = amGenerados;
    }

    public String getAmRazonSocial() {
        return amRazonSocial;
    }

    public void setAmRazonSocial(String amRazonSocial) {
        this.amRazonSocial = amRazonSocial;
    }

    public String getAmNombreComercial() {
        return amNombreComercial;
    }

    public void setAmNombreComercial(String amNombreComercial) {
        this.amNombreComercial = amNombreComercial;
    }

    public String getAmRuc() {
        return amRuc;
    }

    public void setAmRuc(String amRuc) {
        this.amRuc = amRuc;
    }

    public String getAmTipoEmision() {
        return amTipoEmision;
    }

    public void setAmTipoEmision(String amTipoEmision) {
        this.amTipoEmision = amTipoEmision;
    }

    public String getAmDireccionMatriz() {
        return amDireccionMatriz;
    }

    public void setAmDireccionMatriz(String amDireccionMatriz) {
        this.amDireccionMatriz = amDireccionMatriz;
    }

    public String getLlevarContabilidad() {
        return llevarContabilidad;
    }

    public void setLlevarContabilidad(String llevarContabilidad) {
        this.llevarContabilidad = llevarContabilidad;
    }

    public String getAmEstab() {
        return amEstab;
    }

    public void setAmEstab(String amEstab) {
        this.amEstab = amEstab;
    }

    public String getAmPtoemi() {
        return amPtoemi;
    }

    public void setAmPtoemi(String amPtoemi) {
        this.amPtoemi = amPtoemi;
    }

    public String getAmNroContribuyente() {
        return amNroContribuyente;
    }

    public void setAmNroContribuyente(String amNroContribuyente) {
        this.amNroContribuyente = amNroContribuyente;
    }

    public String getAmEnviocliente() {
        return amEnviocliente;
    }

    public void setAmEnviocliente(String amEnviocliente) {
        this.amEnviocliente = amEnviocliente;
    }

    public String getAmUnidadDisco() {
        return amUnidadDisco;
    }

    public void setAmUnidadDisco(String amUnidadDisco) {
        this.amUnidadDisco = amUnidadDisco;
    }

    public String getAmFolderFirma() {
        return amFolderFirma;
    }

    public void setAmFolderFirma(String amFolderFirma) {
        this.amFolderFirma = amFolderFirma;
    }

    public String getAmDirAts() {
        return amDirAts;
    }

    public void setAmDirAts(String amDirAts) {
        this.amDirAts = amDirAts;
    }

    public String getAmCiudad() {
        return amCiudad;
    }

    public void setAmCiudad(String amCiudad) {
        this.amCiudad = amCiudad;
    }

}
// off