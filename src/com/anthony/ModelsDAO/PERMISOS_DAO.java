package com.anthony.ModelsDAO;

import com.anthony.Database.Conexion;
import com.anthony.Models.*;
import com.anthony.ModelsInterfaces.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThonyDev0726
 *
 */
public class PERMISOS_DAO implements crud_permisos {

    PERMISOS permisos = new PERMISOS();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    /* ============== VARIABLES PARA PROCEDIMIENTOS ALMACENADOS ==============*/
    String LISTAR = "CALL SELECT_PERMISOS()";
    String LISTAR_ID = "CALL SELECT_PERMISOS_ID(?)";
    String CREAR = "CALL INSERT_PERMISO(?)";
    String ACTUALIZAR = "CALL UPDATE_PERMISOS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String ESTADO = null;
    String CONSULTAR_ESTADO = null;

    @Override
    public List listar() {
        ArrayList<PERMISOS> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR);
            rs = cs.executeQuery();
            while (rs.next()) {
                PERMISOS per = new PERMISOS();
                per.setID_PERMISO(rs.getInt(1));
                per.setFK_USUARIO(rs.getInt(2));
                per.setUSU_USUARIO(rs.getString(3));
                per.setPER_CLIENTE(rs.getString(4));
                per.setPER_EMPLEADO(rs.getString(5));
                per.setPER_PROVEEDOR(rs.getString(6));
                per.setPER_PRODUCTO(rs.getString(7));
                per.setPER_FACTURACION(rs.getString(8));
                per.setPER_NOTA_DEBITO(rs.getString(9));
                per.setPER_NOTA_CREDITO(rs.getString(10));
                per.setPER_REP_GENERALES(rs.getString(11));
                per.setPER_REP_ESTADISTICOS(rs.getString(12));
                per.setPER_REV_FACTURAS(rs.getString(13));
                per.setPER_REV_NOT_CREDITO(rs.getString(14));
                per.setPER_REV_NOT_DEBITO(rs.getString(15));
                per.setPER_USUARIOS(rs.getString(16));
                per.setPER_PERMISOS(rs.getString(17));
                per.setPER_AJUSTES(rs.getString(18));
                per.setPER_EMPRESA(rs.getString(19));
                lista.add(per);
            }
            System.out.println("SE LISTARON LOS USUARIOS Y SUS PERMISOS");
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LOS USUARIOS Y PERMISOS" + ex);
        }
        return lista;
    }

    @Override
    public String add(Integer idUsuario) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CREAR);
            cs.setInt(1, idUsuario);
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL CREAR EL PERMISO");
            System.out.println(ex);
            return "El permiso no fue creado!";
        }
        return "El permiso fue creado con exito!";
    }

    @Override
    public String update(PERMISOS mp) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(ACTUALIZAR);
            cs.setInt(1, mp.getFK_USUARIO());
            cs.setString(2, mp.getPER_CLIENTE());
            cs.setString(3, mp.getPER_EMPLEADO());
            cs.setString(4, mp.getPER_PROVEEDOR());
            cs.setString(5, mp.getPER_PRODUCTO());
            cs.setString(6, mp.getPER_FACTURACION());
            cs.setString(7, mp.getPER_NOTA_DEBITO());
            cs.setString(8, mp.getPER_NOTA_CREDITO());
            cs.setString(9, mp.getPER_REP_GENERALES());
            cs.setString(10, mp.getPER_REP_ESTADISTICOS());
            cs.setString(11, mp.getPER_REV_FACTURAS());
            cs.setString(12, mp.getPER_REV_NOT_CREDITO());
            cs.setString(13, mp.getPER_REV_NOT_DEBITO());
            cs.setString(14, mp.getPER_USUARIOS());
            cs.setString(15, mp.getPER_PERMISOS());
            cs.setString(16, mp.getPER_AJUSTES());
            cs.setString(17, mp.getPER_EMPRESA());
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL ACTUALIZAR EL PERMISO");
            System.out.println(ex);
            return "El permiso no fue actualizado!";
        }
        return "El permiso fue actualizado con exito!";
    }

    @Override
    public PERMISOS list(int fk_usuario) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR_ID);
            cs.setInt(1, fk_usuario);
            rs = cs.executeQuery();
            while (rs.next()) {
                permisos.setPER_CLIENTE(rs.getString(1));
                permisos.setPER_EMPLEADO(rs.getString(2));
                permisos.setPER_PROVEEDOR(rs.getString(3));
                permisos.setPER_PRODUCTO(rs.getString(4));
                permisos.setPER_FACTURACION(rs.getString(5));
                permisos.setPER_NOTA_DEBITO(rs.getString(6));
                permisos.setPER_NOTA_CREDITO(rs.getString(7));
                permisos.setPER_REP_GENERALES(rs.getString(8));
                permisos.setPER_REP_ESTADISTICOS(rs.getString(9));
                permisos.setPER_REV_FACTURAS(rs.getString(10));
                permisos.setPER_REV_NOT_CREDITO(rs.getString(11));
                permisos.setPER_REV_NOT_DEBITO(rs.getString(12));
                permisos.setPER_USUARIOS(rs.getString(13));
                permisos.setPER_PERMISOS(rs.getString(14));
                permisos.setPER_AJUSTES(rs.getString(15));
                permisos.setPER_EMPRESA(rs.getString(16));
            }
            System.out.println("ESTAMOS LISTANDO LOS PERMISOS DEL USUARIO: " + fk_usuario);
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LOS PERMISOS DEL USUARIO: " + fk_usuario + "\n" + ex);
        }
        permisos.toString();
        return permisos;
    }

    /*==============================
    PERMISOS POR PARAMETRO 
    ==============================*/
    public String ESTADO_CLIENTE(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_CLIENTE(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }


    public String ESTADO_EMPLEADO(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_EMPLEADO(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_PROVEEDOR(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_PROVEEDOR(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_PRODUCTO(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_PRODUCTO(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_FACTURACION(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_FACTURACION(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_NOT_DEBITO(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_NOT_DEBITO(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_NOT_CREDITO(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_NOT_CREDITO(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_REP_GENERAL(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_REP_GENERAL(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_REP_ESTADISTICO(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_REP_ESTADISTICO(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_REV_FACTURAS(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_REV_FACTURAS(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_REV_NOT_CREDITO(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_REV_NOT_CREDITO(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_REV_NOT_DEBITO(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_REV_NOT_DEBITO(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_USUARIOS(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_USUARIOS(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_PERMISOS(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_PERMISOS(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_AJUSTES(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_AJUSTES(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }

    public String ESTADO_EMPRESA(int ID_USUARIO) {
        try {
            CONSULTAR_ESTADO = "CALL ESTADO_EMPRESA(?)";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return ESTADO;
    }
}
