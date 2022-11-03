package com.anthony.ModelsDAO;

import com.anthony.Database.Conexion;
import com.anthony.Models.*;
import com.anthony.ModelsInterfaces.crud_factura_descripcion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ThonyDev0726
 */
public class FACTURA_DESCRIPCION_DAO implements crud_factura_descripcion {

    FACTURA_DESCRIPCION c = new FACTURA_DESCRIPCION();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;
    
    String CREAR = "CALL INSERT_DETALLE(?,?,?,?,?)";
    String ELIMINAR = "CALL DELETE_DETALLE(?)";

    @Override
    public String add(FACTURA_DESCRIPCION mp) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CREAR);
            cs.setInt(1, mp.getFK_FACTURA());
            cs.setInt(2, mp.getFK_PRODUCTO());
            cs.setDouble(3, mp.getDET_CANTIDAD());
            cs.setDouble(4, mp.getDET_PRECIO());
            cs.setDouble(5, mp.getDET_TOTAL());
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
            return "Error al eliminar los detalles!";
        }
        return "Los detalles fueron eliminados con exito!";
    }

    @Override
    public List listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FACTURA_DESCRIPCION list(int idFactura) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
