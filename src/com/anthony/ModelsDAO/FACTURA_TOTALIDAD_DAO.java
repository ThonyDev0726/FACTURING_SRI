package com.anthony.ModelsDAO;

import com.anthony.Database.Conexion;
import com.anthony.Models.*;
import com.anthony.ModelsInterfaces.crud_factura_totalidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThonyDev0726
 */
public class FACTURA_TOTALIDAD_DAO implements crud_factura_totalidad {

    FACTURA_TOTALIDAD c = new FACTURA_TOTALIDAD();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    String CREAR = "CALL INSERT_FACTURA_TOTALIDAD(?,?,?,?,?,?,?,?)";
    String ELIMINAR = "CALL DELETE_FACTURA_TOTALIDAD(?)";

    @Override
    public List listar() {
        return null;
    }

    @Override
    public FACTURA_TOTALIDAD list(int idFactura) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String add(FACTURA_TOTALIDAD mp) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CREAR);
            cs.setInt(1, mp.getFK_FACTURA());
            cs.setDouble(2, mp.getFAC_SUB_0());
            cs.setDouble(3, mp.getFAC_SUB_12());
            cs.setDouble(4, mp.getFAC_OBJ_IVA());
            cs.setDouble(5, mp.getFAC_EXC_IVA());
            cs.setDouble(6, mp.getFAC_DESCUENTO());
            cs.setDouble(7, mp.getFAC_IVA_12());
            cs.setDouble(8, mp.getFAC_TOTAL_PAGAR());
            cs.execute();
            System.out.println("SE CREO LA FACTURA CON EXITO");
        } catch (SQLException ex) {
            System.out.println("ERROR AL CREAR LA FACTURA");
            System.out.println(ex);
            return "La factura no fue creada!";
        }
        return "La factura fue creada con exito!";
    }

    @Override
    public String delete(int id) {
        try {
            con = (Connection) cn.getConexion();
            CallableStatement cs = con.prepareCall(ELIMINAR);
            cs.setInt(1, id);
            System.out.println(ELIMINAR);
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL ELIMINAR EL PRODUCTO DE LA FACTURA!");
            System.out.println(ex);
            return "Error al eliminar la totalidad!";
        }
        return "La totalidad fue eliminada con exito!";
    }

}
