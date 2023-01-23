package com.anthony.ModelsDAO;

import com.anthony.Database.Conexion;
import com.anthony.Models.AJUSTES;
import com.anthony.ModelsInterfaces.crud_ajuste;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AJUSTES_DAO implements crud_ajuste {

    String LISTAR_ID = "CALL SELECT_AJUSTE()";
    String ACTUALIZAR = "CALL UPDATE_AJUSTES(?,?,?,?,?,?)";
    String ACTUALIZAR_LOGO = "CALL UPDATE_LOGO(?)";

    AJUSTES ajuste = new AJUSTES();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    @Override
    public AJUSTES listAjustes() {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR_ID);
            rs = cs.executeQuery();
            while (rs.next()) {
                ajuste.setAJ_EMAIL(rs.getString(1));
                ajuste.setAJ_CLAVE(rs.getString(2));
                ajuste.setAJ_RUTA_FIRMA(rs.getString(3));
                ajuste.setAJ_CLAVE_ACCESO(rs.getString(4));
                ajuste.setAJ_LOGO_EMPRESA(rs.getString(5));
            }
            System.out.println("SE ESTA LISTANDO EL AJUSTE");
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LOS AJUSTES" + ex);
        }
        return ajuste;
    }

    @Override
    public String updateAjuste(AJUSTES ajustes) {
        try {
            con = (Connection) cn.getConexion();
            CallableStatement cs = con.prepareCall(ACTUALIZAR);
            cs.setInt(1, ajustes.getID_AJUSTE());
            cs.setString(2, ajustes.getAJ_EMAIL());
            cs.setString(3, ajustes.getAJ_CLAVE());
            cs.setString(4, ajustes.getAJ_RUTA_FIRMA());
            cs.setString(5, ajustes.getAJ_CLAVE_ACCESO());
            cs.setString(6, ajustes.getAJ_LOGO_EMPRESA());
            System.out.println("SE ACTUALIZO EL AJUSTE CON EXITO");
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL ACTUALIZAR EL AJUSTE");
            System.out.println(ex);
            return "El ajuste no fue actualizado!";
        }
        return "El ajuste fue actualizado con exito!";
    }

    public String updateLogo(String logo) {
        try {
            con = (Connection) cn.getConexion();
            CallableStatement cs = con.prepareCall(ACTUALIZAR_LOGO);
            cs.setString(1, logo);
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL ACTUALIZAR EL AJUSTE");
            System.out.println(ex);
            return "El logo no se actualizo";
        }
        return "El logo se actualizo con exito";
    }

}
