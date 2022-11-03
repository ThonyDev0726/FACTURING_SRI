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
public class CLIENTE_DAO implements crud_cliente {

    CLIENTE c = new CLIENTE();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    /* ============== VARIABLES PARA PROCEDIMIENTOS ALMACENADOS ==============*/
    String LISTAR_EN_LINEA = "CALL SELECT_CLIENTES_LINEA()";
    String LISTAR = "CALL SELECT_CLIENTES_ALL()";
    String LISTAR_ID = "CALL A_S_ID_CLIENTE(?)";
    String CREAR = "CALL INSERT_CLIENTES(?,?,?,?,?,?,?,?,?)";
    String ACTUALIZAR = "CALL UPDATE_CLIENTE(?,?,?,?,?,?,?,?,?)";
    String ELIMINAR = "CALL DELETE_CLIENTE(?)";
    String ACTUALIZAR_ESTADO = "CALL UPDATE_CLIENTE_ESTADO(?,?)";
    String BUSCAR_CLIENTE = "CALL SELECT_CLIENTE_BUSCAR(?,?)";

    @Override
    public List listar() {
        ArrayList<CLIENTE> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR);
            rs = cs.executeQuery();
            while (rs.next()) {
                CLIENTE cli = new CLIENTE();
                cli.setID_CLIENTE(rs.getInt(1));
                cli.setCLI_NOMBRES(rs.getString(2));
                cli.setCLI_APELLIDOS(rs.getString(3));
                cli.setCLI_CEDULA(rs.getString(4));
                cli.setCLI_RUC(rs.getString(5));
                cli.setCLI_TELEFONO(rs.getString(6));
                cli.setCLI_DIRECCION(rs.getString(7));
                cli.setCLI_EMAIL(rs.getString(8));
                cli.setCLI_ESTADO(rs.getString(9));
                lista.add(cli);
            }
            System.out.println("SE ESTA LISTANDO LOS CLIENTES");
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LOS CLIENTES" + ex);
        }
        return lista;
    }

    @Override
    public List listar_en_linea() {
        ArrayList<CLIENTE> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR_EN_LINEA);
            rs = cs.executeQuery();
            while (rs.next()) {
                CLIENTE cli = new CLIENTE();
                cli.setID_CLIENTE(rs.getInt(1));
                cli.setCLI_NOMBRES(rs.getString(2));
                cli.setCLI_APELLIDOS(rs.getString(3));
                cli.setCLI_CEDULA(rs.getString(4));
                cli.setCLI_RUC(rs.getString(5));
                cli.setCLI_TELEFONO(rs.getString(6));
                cli.setCLI_DIRECCION(rs.getString(7));
                cli.setCLI_EMAIL(rs.getString(8));
                lista.add(cli);
            }
            System.out.println("SE ESTA LISTANDO LOS CLIENTES EN LINEA");
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LOS CLIENTES" + ex);
        }
        return lista;
    }

    @Override
    public CLIENTE list(int id) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR_ID);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()) {
                c.setID_CLIENTE(rs.getInt(1));
                c.setCLI_ESTADO(rs.getString(2));
                c.setCLI_NOMBRES(rs.getString(3));
                c.setCLI_APELLIDOS(rs.getString(4));
                c.setCLI_CEDULA(rs.getString(5));
                c.setCLI_RUC(rs.getString(6));
                c.setCLI_EMAIL(rs.getString(7));
                c.setCLI_TELEFONO(rs.getString(8));
                c.setCLI_DIRECCION(rs.getString(9));
            }
            System.out.println("SE ESTA LISTANDO EL CLIENTE");
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LOS CLIENTES" + ex);
        }
        return c;
    }

    @Override
    public String add(CLIENTE cliente) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CREAR);
            cs.setString(1, cliente.getCLI_NOMBRES());
            cs.setString(2, cliente.getCLI_APELLIDOS());
            cs.setString(3, cliente.getCLI_CEDULA());
            cs.setString(4, cliente.getCLI_RUC());
            cs.setString(5, cliente.getCLI_TELEFONO());
            cs.setString(6, cliente.getCLI_DIRECCION());
            cs.setString(7, cliente.getCLI_EMAIL());
            cs.setString(8, cliente.getCLI_ESTADO());
            cs.setString(9, cliente.getCLI_CREACION());
            cs.execute();
            System.out.println("SE CREO EL CLIENTE CON EXITO");
        } catch (SQLException ex) {
            System.out.println("ERROR AL CREAR EL CLIENTE");
            System.out.println(ex);
            return "El cliente no fue creado!";
        }
        return "El cliente fue creado con exito!";
    }

    @Override
    public String update(CLIENTE cli) {
        try {
            con = (Connection) cn.getConexion();
            CallableStatement cs = con.prepareCall(ACTUALIZAR);
            cs.setInt(1, cli.getID_CLIENTE());
            cs.setString(2, cli.getCLI_NOMBRES());
            cs.setString(3, cli.getCLI_APELLIDOS());
            cs.setString(4, cli.getCLI_CEDULA());
            cs.setString(5, cli.getCLI_RUC());
            cs.setString(6, cli.getCLI_TELEFONO());
            cs.setString(7, cli.getCLI_DIRECCION());
            cs.setString(8, cli.getCLI_EMAIL());
            cs.setString(9, cli.getCLI_ESTADO());
            System.out.println("SE ACTUALIZO EL CLIENTE CON EXITO");
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL ACTUALIZAR EL CLIENTE");
            System.out.println(ex);
            return "El cliente no fue actualizado!";
        }
        return "El cliente fue actualizado con exito!";
    }

    @Override
    public String actualizar_estado(int id, String estado) {
        try {
            con = (Connection) cn.getConexion();
            CallableStatement cs = con.prepareCall(ACTUALIZAR_ESTADO);
            cs.setInt(1, id);
            cs.setString(2, estado);
            System.out.println(ACTUALIZAR_ESTADO);
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL DAR DE BAJA AL CLIENTE");
            System.out.println(ex);
            return "Error al dar baja al cliente!";
        }
        return "El cliente fue dado de baja con exito!";
    }

    @Override
    public String delete(int id) {
        try {
            con = (Connection) cn.getConexion();
            CallableStatement cs = con.prepareCall(ELIMINAR);
            cs.setInt(1, id);
            System.out.println("SE ELIMINO EL CLIENTE: " + id);
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el cliente!");
            System.out.println(ex);
            return "Error al eliminar el cliente!";
        }
        return "El cliente fue eliminado con exito!";
    }

    public ResultSet BUSCAR_CLIENTE(String criterio, String busqueda) throws Exception {
        con = (Connection) cn.getConexion();
        ResultSet rs = null;
        try {
            CallableStatement st = con.prepareCall(BUSCAR_CLIENTE);
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
