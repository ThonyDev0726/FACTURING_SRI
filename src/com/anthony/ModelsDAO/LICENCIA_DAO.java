package com.anthony.ModelsDAO;

import com.anthony.Database.Conexion;
import com.anthony.ModelsInterfaces.crud_licencias;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ThonyDev0726
 */
public class LICENCIA_DAO implements crud_licencias {

    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;
    Integer ID_LICENCIA = 0;
    String CONSULTAR_LICENCIA = "CALL SELECT_LICENCIA_ID(?)";
    String CONSULTAR_LICENCIA_ESTADO = "CALL SELECT_LICENCIA_ESTADO()";
    String ESTADO_LICENCIA = "CALL UPDATE_LICENCIA()";
    String LICENCIA;

    @Override
    public Integer consultarLicencia(String licencia) {
        ID_LICENCIA = 0;
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_LICENCIA);
            cs.setString(1, licencia);
            rs = cs.executeQuery();
            while (rs.next()) {
                ID_LICENCIA = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ID_LICENCIA no encontrada: " + ex);
            return 0;
        }
        return ID_LICENCIA;
    }

    public String consultarEstado() {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_LICENCIA_ESTADO);
            rs = cs.executeQuery();
            while (rs.next()) {
                LICENCIA = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println("estado de LICENCIA no encontrada: " + ex);
        }
        return LICENCIA;
    }

    @Override
    public String updateEstado() {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(ESTADO_LICENCIA);
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL ACTUALIZAR EL ESTADO DE LA LICENCIA");
            System.out.println(ex);
            return "No se actualizo la licencia!";
        }
        return "Licencia actualizada con exito!";
    }

}
