/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author perez
 */
public class AUDITORIA_DAO implements crud_auditoria {

    AUDITORIA c = new AUDITORIA();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;
    /* ============== VARIABLES PARA PROCEDIMIENTOS ALMACENADOS ==============*/
    String LISTAR = "CALL A_SELECT_CLIENTE()";
    String LISTAR_ID = "CALL A_S_ID_CLIENTE(?)";
    String CREAR = "CALL INSERT_CLIENTE(?,?,?,?,?,?,?,?,?,?,?,?)";
    String ACTUALIZAR = "CALL ACTUALIZAR_CLIENTE(?,?,?,?,?,?,?,?,?)";

    @Override
    public List listar() {

        ArrayList<AUDITORIA> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR);
            rs = cs.executeQuery();
            while (rs.next()) {
                AUDITORIA cli = new AUDITORIA();
                cli.setID_AUDITORIA(rs.getInt(1));
                cli.setAUD_DESC(rs.getString(2));
                cli.setAUD_IP(rs.getString(3));
                cli.setAUD_FECHA(rs.getString(4));
                cli.setAUD_HORA(rs.getString(5));
                cli.setAUD_USUARIO(rs.getString(6));
                lista.add(cli);
            }
            System.out.println(LISTAR);
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LOS CLIENTES" + ex);
        }
        return lista;
    }

    @Override
    public AUDITORIA list(int idAuditoria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String add(AUDITORIA mp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String update(AUDITORIA mp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List listar_en_linea() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String actualizar_estado(int id, String estado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
