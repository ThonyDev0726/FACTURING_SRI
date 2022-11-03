package com.anthony.ModelsDAO;

import com.anthony.Database.*;
import com.anthony.Models.*;
import com.anthony.ModelsInterfaces.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThonyDev0726
 */
public class DATOS_EMISOR_DAO implements crud_datos_emisor {

    DATOS_EMISOR dat = new DATOS_EMISOR();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    /* ============== VARIABLES PARA PROCEDIMIENTOS ALMACENADOS ==============*/    
    String LISTAR_ID = "CALL SELECT_EMISOR()";
    String ACTUALIZAR = "CALL UPDATE_EMISOR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    @Override
    public String update(DATOS_EMISOR datos_emisor) {
        try {
            con = (Connection) cn.getConexion();
            CallableStatement cs = con.prepareCall(ACTUALIZAR);
            cs.setInt(1, datos_emisor.getID_EMPRESA());
            cs.setInt(2, datos_emisor.getDAT_RUC());
            cs.setString(3, datos_emisor.getDAT_DIR_ESTABECIMIENTO());
            cs.setString(4, datos_emisor.getDAT_NOM_COMERCIAL());
            cs.setString(5, datos_emisor.getDAT_RAZON_SOCIAL());
            cs.setString(6, datos_emisor.getDAT_CONTRIBUYENTE_ESPECIAL());
            cs.setString(7, datos_emisor.getDAT_MATRIZ());
            cs.setInt(8, datos_emisor.getDAT_COD_ESTABLECIMIENTO());
            cs.setString(9, datos_emisor.getDAT_PUNTO_EMISION());
            cs.setString(10, datos_emisor.getDAT_LLEVAR_CONTABILIDAD());
            cs.setString(11, datos_emisor.getDAT_CONTRIBUYENTE_RIMPE());
            cs.setString(12, datos_emisor.getDAT_AGENTE_RETENCION());
            cs.setString(13, datos_emisor.getDAT_IMAGEN());
            cs.setString(14, datos_emisor.getDAT_TIPO_TOQUEN());
            cs.setString(15, datos_emisor.getDAT_TOQUEN_FIRMA());
            System.out.println(ACTUALIZAR);
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL ACTUALIZAR EL EMISOR");
            System.out.println(ex);
            return "El emisor no fue actualizado!";
        }
        return "El emisor fue actualizado con exito!";
    }

    @Override
    public DATOS_EMISOR list() {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR_ID);
            rs = cs.executeQuery();
            while (rs.next()) {
                dat.setID_EMPRESA(rs.getInt(1));
                dat.setDAT_RUC(rs.getInt(2));
                dat.setDAT_DIR_ESTABECIMIENTO(rs.getString(3));
                dat.setDAT_NOM_COMERCIAL(rs.getString(4));
                dat.setDAT_RAZON_SOCIAL(rs.getString(5));
                dat.setDAT_CONTRIBUYENTE_ESPECIAL(rs.getString(6));
                dat.setDAT_MATRIZ(rs.getString(7));
                dat.setDAT_COD_ESTABLECIMIENTO(rs.getInt(8));
                dat.setDAT_PUNTO_EMISION(rs.getString(9));
                dat.setDAT_LLEVAR_CONTABILIDAD(rs.getString(10));
                dat.setDAT_CONTRIBUYENTE_RIMPE(rs.getString(11));
                dat.setDAT_AGENTE_RETENCION(rs.getString(12));
                dat.setDAT_IMAGEN(rs.getString(13));
                dat.setDAT_TIPO_TOQUEN(rs.getString(14));
                dat.setDAT_TOQUEN_FIRMA(rs.getString(15));
                dat.toString();
            }
            System.out.println(dat.toString());            
            System.out.println(LISTAR_ID);
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR AL EMISOR" + ex);
        }
        return dat;
    }
}
