package com.anthony.ModelsDAO;

import com.anthony.Controller.*;
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
public class USUARIO_DAO implements crud_usuario {

    /* ========= VARIABLES GLOBALES ========= */
    Encriptador enc = new Encriptador();
    USUARIO c = new USUARIO();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    /* ========= VARIABLES PROCEDIMIENTOS ========= */
    String CONSULTAR_USUARIO = "CALL U_S_ID_USUARIO(?)";
    String CONSULTAR_CLAVE = "CALL U_S_USU_CLAVE(?)";
    String CONSULTAR_CARGO = "CALL U_S_USU_PARAMETRO(?)";
    String CONSULTAR_ESTADO = "CALL U_S_USU_ESTADO(?)";
    String LISTAR = "CALL SELECT_USUARIO()";
    String LISTAR_ID = "CALL A_S_ID_USUARIO(?)";
    String CREAR = "CALL INSERT_USUARIO(?,?,?,?,?,?,?)";
    String ACTUALIZAR = "CALL UPDATE_USUARIO(?,?,?,?,?)";
    String ACTUALIZAR_ESTADO = "CALL UPDATE_USUARIO_ESTADO(?,?)";
    String ACTUALIZAR_CLAVE = "CALL UPDATE_CLAVE(?,?)";
    String BUSCAR_USUARIO = "CALL SELECT_USUARIO_BUSCAR(?,?)";
    String ELIMINAR = "CALL DELETE_USUARIO(?)";

    /* ========= VARIABLES PROCEDIMIENTOS PARAMETROS ========= */
    Integer ID_USUARIO;
    Integer ID_EMPLEADO;
    Integer ID_SUCURSAL;
    String CLAVE = "";
    String CARGO = "";
    String ESTADO = "";

    /* =================== LOGIN =================== */
    @Override
    public Integer consultarUsuario(String USU_USUARIO) {
        try {
            ID_USUARIO = 0;
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_USUARIO);
            cs.setString(1, USU_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ID_USUARIO = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println("ID_USUARIO no encontrado: " + ex);
            return 0;
        }
        return ID_USUARIO;
    }

    @Override
    public String obtenerClave(int ID_USUARIO) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_CLAVE);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                CLAVE = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_CLAVE no encontrada: " + e);
        }
        return CLAVE;
    }

    @Override
    public String consultarCargo(int ID_USUARIO) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_CARGO);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                CARGO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_CARGO no encontrada: " + e);
        }
        return CARGO;
    }

    @Override
    public String consultarEstado(int ID_USUARIO) {
        try {
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

    /* ========= CRUD ========= */
    @Override
    public List listar() {
        ArrayList<USUARIO> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR);
            rs = cs.executeQuery();
            while (rs.next()) {
                USUARIO cli = new USUARIO();
                cli.setID_USUARIO(rs.getInt(1));
                cli.setSUC_NOMBRE(rs.getString(2));
                cli.setEMP_NOMBRES(rs.getString(3));
                cli.setEMP_APELLIDOS(rs.getString(4));
                cli.setUSU_USUARIO(rs.getString(5));
                cli.setUSU_CLAVE(rs.getString(6));
                cli.setUSU_PARAMETRO(rs.getString(7));
                cli.setUSU_ESTADO(rs.getString(8));
                lista.add(cli);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LOS USUARIOS" + ex);
        }
        return lista;
    }

    @Override
    public USUARIO list(int idUsuario) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR_ID);
            cs.setInt(1, idUsuario);
            rs = cs.executeQuery();
            while (rs.next()) {
                c.setID_USUARIO(rs.getInt(1));
                c.setSUC_NOMBRE(rs.getString(2));
                c.setEMP_NOMBRES(rs.getString(3));
                c.setEMP_APELLIDOS(rs.getString(4));
                c.setUSU_USUARIO(rs.getString(5));
                c.setUSU_CLAVE(enc.desencriptar(rs.getString(6)));
                c.setUSU_PARAMETRO(rs.getString(7));
                c.setUSU_ESTADO(rs.getString(8));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LOS EMPLEADOS" + ex);
        }
        return c;
    }

    public String ULTIMO_ID_USUARIO() {
        try {
            CONSULTAR_ESTADO = "CALL ULTIMO_USUARIO()";
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_ESTADO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ESTADO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ULTIMO USUARIO NO ENCONTRADO: " + e);
        }
        return ESTADO;
    }

    public Integer ID_EMPLEADO(int ID_USUARIO) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall("CALL SELCT_USU_ID_EMPLEADO(?)");
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                ID_EMPLEADO = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("ULTIMO USUARIO NO ENCONTRADO: " + e);
        }
        return ID_EMPLEADO;
    }

    public Integer ID_SUCURSAL(int fk) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall("CALL SELECT_USU_ID_SUCURSAL(?)");
            cs.setInt(1, fk);
            rs = cs.executeQuery();
            while (rs.next()) {
                ID_SUCURSAL = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("ULTIMO USUARIO NO ENCONTRADO: " + e);
        }
        return ID_SUCURSAL;
    }

    @Override
    public String add(USUARIO mp) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CREAR);
            cs.setInt(1, mp.getFK_SUCURSAL());
            cs.setInt(2, mp.getFK_EMPLEADO());
            cs.setString(3, mp.getUSU_USUARIO());
            cs.setString(4, enc.encriptar(mp.getUSU_CLAVE()));
            cs.setString(5, mp.getUSU_PARAMETRO());
            cs.setString(6, mp.getUSU_ESTADO());
            cs.setString(7, mp.getUSU_CREACION());
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL CREAR EL USUARIO");
            System.out.println(ex);
            return "El usuario no fue creado con exito!";
        }
        return "El usuario fue creado con exito!";
    }

    @Override
    public String update(USUARIO usu) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(ACTUALIZAR);
            cs.setInt(1, usu.getID_USUARIO());
            cs.setInt(2, usu.getFK_SUCURSAL());
            cs.setString(3, enc.encriptar(usu.getUSU_CLAVE()));
            cs.setString(4, usu.getUSU_PARAMETRO());
            cs.setString(5, usu.getUSU_ESTADO());
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL CREAR EL USUARIO");
            System.out.println(ex);
            return "El usuario no fue actualizado!";
        }
        return "El usuario fue actualizado con exito!";
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
            System.out.println("Error al eliminar el cliente!");
            System.out.println(ex);
            return "Error al eliminar el usuario!";
        }
        return "El usuario fue eliminado con exito!";
    }

    @Override
    public String actualizar_estado(int id, String estado) {
        try {
            con = (Connection) cn.getConexion();
            CallableStatement cs = con.prepareCall(ACTUALIZAR_ESTADO);
            cs.setInt(1, id);
            cs.setString(2, estado);
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL DAR DE BAJA AL USUARIO");
            System.out.println(ex);
            return "Error al dar baja al usuario!";
        }
        return "El usuario fue dado de baja con exito!";
    }

    @Override
    public String update_password(int id, String string) {
        try {
            con = (Connection) cn.getConexion();
            CallableStatement cs = con.prepareCall(ACTUALIZAR_CLAVE);
            cs.setInt(1, id);
            cs.setString(2, enc.encriptar(string));
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL CAMBIAR LA CLAVE");
            System.out.println(ex);
            return "Error al actualizar la clave!";
        }
        return "La clave se actualizo con exito!";
    }

    public ResultSet BUSCAR_USUARIO(String criterio, String busqueda) throws Exception {
        con = (Connection) cn.getConexion();
        ResultSet rs = null;
        try {
            CallableStatement st = con.prepareCall(BUSCAR_USUARIO);
            st.setString("pcriterio", criterio);
            st.setString("pbusqueda", busqueda);
            rs = st.executeQuery();
            return rs;
        } catch (SQLException SQLex) {
            System.out.println("ERROR AL BUSCAR EL EMPLEADO: " + SQLex);
        }
        return rs;
    }
}
