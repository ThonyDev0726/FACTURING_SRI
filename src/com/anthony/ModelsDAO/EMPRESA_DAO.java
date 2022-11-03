/*
 * EMPck nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * EMPck nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anthony.ModelsDAO;

import com.anthony.Database.*;
import com.anthony.Models.*;
import com.anthony.ModelsInterfaces.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author perez
 */
public class EMPRESA_DAO implements crud_empresa {

    
    EMPRESA empresa = new EMPRESA();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    /* ============== VARIABLES PARA PROCEDIMIENTOS ALMACENADOS ==============*/
    String LISTAR = "CALL SELECT_EMPRESA()";
    String ACTUALIZAR = "CALL UPDATE_EMPRESA(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    @Override
    public EMPRESA list() {
    try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR);
            rs = cs.executeQuery();
            while (rs.next()) {
                empresa.setID_EMPRESA(rs.getInt(1));
                empresa.setEMP_NOMBRE_COMERCIAL(rs.getString(2));
                empresa.setEMP_RAZON_SOCIAL(rs.getString(3));
                empresa.setEMP_RUC(rs.getString(4));
                empresa.setEMP_MATRIZ(rs.getString(5));
                empresa.setEMP_ESTABLECIMIENTO(rs.getString(6));
                empresa.setEMP_COD_ESTABLECIMIENTO(rs.getString(7));
                empresa.setEMP_PUNTO_EMISION(rs.getString(9));
                empresa.setEMP_RESOLUCION_CONTRIB_ESPECIAL(rs.getString(9));
                empresa.setEMP_NUM_AGENTE_RETENCION(rs.getString(10));
                empresa.setEMP_LLEVAR_CONTABILIDAD(rs.getString(11));
                empresa.setEMP_CONTRIBUYENTE_RIMPE(rs.getString(12));
                empresa.setEMP_TOKEN(rs.getString(13));
            }
            System.out.println("SE LISTO LA EMPRESA");
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LA EMPRESA" + ex);
        }
        return empresa;    
    }

    @Override
    public String update(EMPRESA emp) {
        try {
            con = (Connection) cn.getConexion();
            CallableStatement cs = con.prepareCall(ACTUALIZAR);
            cs.setInt(1, emp.getID_EMPRESA());
            cs.setString(2, emp.getEMP_NOMBRE_COMERCIAL());
            cs.setString(3, emp.getEMP_RAZON_SOCIAL());
            cs.setString(4, emp.getEMP_RUC());
            cs.setString(5, emp.getEMP_MATRIZ());
            cs.setString(6, emp.getEMP_ESTABLECIMIENTO());
            cs.setString(7, emp.getEMP_COD_ESTABLECIMIENTO());
            cs.setString(8, emp.getEMP_PUNTO_EMISION());
            cs.setString(9, emp.getEMP_RESOLUCION_CONTRIB_ESPECIAL());
            cs.setString(10, emp.getEMP_NUM_AGENTE_RETENCION());
            cs.setString(11, emp.getEMP_LLEVAR_CONTABILIDAD());
            cs.setString(12, emp.getEMP_CONTRIBUYENTE_RIMPE());
            cs.setString(13, emp.getEMP_TOKEN());
            System.out.println("SE ACTUALIZO EMPRESA");
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL ACTUALIZAR LA EMPRESA");
            System.out.println(ex);
            return "La empresa no fue actualizada!";
        }
        return "La empresa fue actualizada con exito!";
    }

}
