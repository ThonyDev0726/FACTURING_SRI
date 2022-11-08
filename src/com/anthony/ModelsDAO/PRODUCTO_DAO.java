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
public class PRODUCTO_DAO implements crud_producto {

    PRODUCTO p = new PRODUCTO();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    /* ============== VARIABLES PARA PROCEDIMIENTOS ALMACENADOS ==============*/
    String LISTAR_EN_LINEA = "CALL SELECT_PRODUCTOS_LINEA()";
    String LISTAR = "CALL SELECT_PRODUCTOS_ALL()";
    String LISTAR_FACTURACION = "CALL SELECT_PRODUCTOS_FACTURACION()";
    String LISTAR_ID = "CALL SELECT_PRODUCTO_ID(?)";
    String LISTAR_ID_COD = "CALL SELECT_PRODUCTO_ID_CODIGO(?)";
    String CREAR = "CALL INSERT_PRODUCTO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String ACTUALIZAR = "CALL UPDATE_PRODUCTO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String ELIMINAR = "CALL DELETE_PRODUCTO(?)";
    String ACTUALIZAR_ESTADO = "CALL UPDATE_PRODUCTO_ESTADO(?,?)";
    String ACTUALIZAR_STOCK = "CALL UPDATE_STOCK_PRODUCTO(?,?)";
    String BUSCAR_PRODUCTO = "CALL SELECT_PRODUCTO_BUSCAR(?,?)";
    String BUSCAR_PRODUCTO_FACTURACION = "CALL SELECT_PRODUCTO_BUSCAR_FACTURACION(?,?)";

    @Override
    public List listar() {
        ArrayList<PRODUCTO> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR);
            rs = cs.executeQuery();
            while (rs.next()) {
                PRODUCTO pro = new PRODUCTO();
                pro.setID_PRODUCTO(rs.getInt(1));
                pro.setSUC_NOMBRE(rs.getString(2));
                pro.setPRO_EMPRESA(rs.getString(3));
                pro.setPRO_NOMBRE(rs.getString(4));
                pro.setPRO_DETALLE(rs.getString(5));
                pro.setPRO_CATEGORIA(rs.getString(6));
                pro.setPRO_COD_PRINC(rs.getString(7));
                pro.setPRO_COD_AUX(rs.getString(8));
                pro.setPRO_DETALLE_EXTRA(rs.getString(9));
                pro.setPRO_STOCK(rs.getInt(10));
                pro.setPRO_PRECIO_FABRICA(rs.getDouble(11));
                pro.setPRO_GANANCIA(rs.getInt(12));
                pro.setPRO_PVP(rs.getDouble(13));
                pro.setPRO_ESTADO(rs.getString(14));
                pro.setPRO_TIPO_IVA(rs.getString(15));
                lista.add(pro);
            }
            System.out.println("SE ESTAN LISTANDO TODOS LOS PRODUCTOS");
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LOS PRODUCTOS" + ex);
        }
        return lista;
    }

    public List listarFacturacion() {
        ArrayList<PRODUCTO> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR_FACTURACION);
            rs = cs.executeQuery();
            while (rs.next()) {
                PRODUCTO pro = new PRODUCTO();
                pro.setID_PRODUCTO(rs.getInt(1));
                pro.setPRO_NOMBRE(rs.getString(2));
                pro.setPRO_COD_PRINC(rs.getString(3));
                pro.setPRO_COD_AUX(rs.getString(4));
                pro.setPRO_DETALLE_EXTRA(rs.getString(5));
                pro.setPRO_STOCK(rs.getInt(6));
                pro.setPRO_PVP(rs.getDouble(7));
                pro.setPRO_TIPO_IVA(rs.getString(8));
                lista.add(pro);
            }
            System.out.println("SE ESTAN LISTANDO TODOS LOS PRODUCTOS FACTURACION");
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LOS PRODUCTOS DE FACTURACION " + ex);
        }
        return lista;
    }

    @Override
    public List listar_linea() {
        ArrayList<PRODUCTO> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR_EN_LINEA);
            rs = cs.executeQuery();
            while (rs.next()) {
                PRODUCTO pro = new PRODUCTO();
                pro.setID_PRODUCTO(rs.getInt(1));
                pro.setSUC_NOMBRE(rs.getString(2));
                pro.setPRO_EMPRESA(rs.getString(3));
                pro.setPRO_NOMBRE(rs.getString(4));
                pro.setPRO_DETALLE(rs.getString(5));
                pro.setPRO_CATEGORIA(rs.getString(6));
                pro.setPRO_COD_PRINC(rs.getString(7));
                pro.setPRO_COD_AUX(rs.getString(8));
                pro.setPRO_DETALLE_EXTRA(rs.getString(9));
                pro.setPRO_STOCK(rs.getInt(10));
                pro.setPRO_PRECIO_FABRICA(rs.getDouble(11));
                pro.setPRO_GANANCIA(rs.getInt(12));
                pro.setPRO_PVP(rs.getDouble(13));
                lista.add(pro);
                System.out.println("SE ESTAN LISTANDO TODOS LOS PRODUCTOS EN LINEA");
            }
            System.out.println(LISTAR);
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LOS PRODUCTOS" + ex);
        }
        return lista;
    }

    @Override
    public PRODUCTO list(int idProducto) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR_ID);
            cs.setInt(1, idProducto);
            rs = cs.executeQuery();
            while (rs.next()) {
                p.setID_PRODUCTO(rs.getInt(1));
                p.setPRO_COD_PRINC(rs.getString(2));
                p.setPRO_COD_AUX(rs.getString(3));
                p.setPRO_STOCK(rs.getInt(4));
            }
            System.out.println(LISTAR_ID);
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR EL PRODUCTO" + ex);
        }
        return p;
    }

    public Integer codigo_id(String cod) {
        int id = 0;
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR_ID_COD);
            cs.setString(1, cod);
            rs = cs.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);

            }
            System.out.println(LISTAR_ID);
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR EL PRODUCTO" + ex);
        }
        return id;
    }

    @Override
    public String add(PRODUCTO producto) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CREAR);
            cs.setInt(1, producto.getFK_SUCURSAL());
            cs.setInt(2, producto.getFK_PROVEEDOR());
            cs.setString(3, producto.getPRO_NOMBRE());
            cs.setString(4, producto.getPRO_DETALLE());
            cs.setString(5, producto.getPRO_CATEGORIA());
            cs.setString(6, producto.getPRO_COD_PRINC());
            cs.setString(7, producto.getPRO_COD_AUX());
            cs.setString(8, producto.getPRO_DETALLE_EXTRA());
            cs.setInt(9, producto.getPRO_STOCK());
            cs.setDouble(10, producto.getPRO_PRECIO_FABRICA());
            cs.setInt(11, producto.getPRO_GANANCIA());
            cs.setDouble(12, producto.getPRO_PVP());
            cs.setString(13, producto.getPRO_CREACION());
            cs.setString(14, producto.getPRO_ESTADO());
            cs.setString(15, producto.getPRO_TIPO_IVA());
            cs.execute();
            System.out.println(CREAR);
        } catch (SQLException ex) {
            System.out.println("ERROR AL CREAR EL PRODUCTO");
            System.out.println(ex);
            return "El producto no fue creado con exito!";
        }
        return "El producto fue creado con exito!";
    }

    @Override
    public String update(PRODUCTO pro) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(ACTUALIZAR);
            cs.setInt(1, pro.getID_PRODUCTO());
            cs.setInt(2, pro.getFK_SUCURSAL());
            cs.setInt(3, pro.getFK_PROVEEDOR());
            cs.setString(4, pro.getPRO_NOMBRE());
            cs.setString(5, pro.getPRO_DETALLE());
            cs.setString(6, pro.getPRO_CATEGORIA());
            cs.setString(7, pro.getPRO_COD_PRINC());
            cs.setString(8, pro.getPRO_COD_AUX());
            cs.setString(9, pro.getPRO_DETALLE_EXTRA());
            cs.setInt(10, pro.getPRO_STOCK());
            cs.setDouble(11, pro.getPRO_PRECIO_FABRICA());
            cs.setInt(12, pro.getPRO_GANANCIA());
            cs.setDouble(13, pro.getPRO_PVP());
            cs.setString(14, pro.getPRO_ESTADO());
            cs.setString(15, pro.getPRO_TIPO_IVA());
            cs.execute();
            System.out.println(ACTUALIZAR);
        } catch (SQLException ex) {
            System.out.println("ERROR AL ACTUALIZAR EL PRODUCTO");
            System.out.println(ex);
            return "El producto no fue actualizado con exito!";
        }
        return "El producto fue actualizado con exito!";
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
            System.out.println("Error al eliminar el producto!");
            System.out.println(ex);
            return "Error al eliminar el producto!";
        }
        return "El producto fue eliminada con exito!";
    }

    @Override
    public List listar_en_linea() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            System.out.println("ERROR AL DAR DE BAJA AL PRODUCTO");
            System.out.println(ex);
            return "Error al dar baja al producto!";
        }
        return "El producto fue dada de baja con exito!";
    }

    public String actualizar_stock(int id, int cantidad) {
        try {
            con = (Connection) cn.getConexion();
            CallableStatement cs = con.prepareCall(ACTUALIZAR_STOCK);
            cs.setInt(1, id);
            cs.setInt(2, cantidad);
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR AL DAR DE BAJA AL PRODUCTO");
            System.out.println(ex);
            return "Error al actualizar el stock!";
        }
        return "El stock fue actualizado con exito!";
    }

    public ResultSet BUSCAR_PRODUCTO(String criterio, String busqueda) throws Exception {
        con = (Connection) cn.getConexion();
        ResultSet rs = null;
        try {
            CallableStatement st = con.prepareCall(BUSCAR_PRODUCTO);
            st.setString("pcriterio", criterio);
            st.setString("pbusqueda", busqueda);
            rs = st.executeQuery();
            return rs;
        } catch (SQLException SQLex) {
            System.out.println("ERROR AL BUSCAR EL EMPLEADO: " + SQLex);
        }
        return rs;
    }
    public ResultSet BUSCAR_PRODUCTO_FACTURACION(String criterio, String busqueda) throws Exception {
        con = (Connection) cn.getConexion();
        ResultSet rs = null;
        try {
            CallableStatement st = con.prepareCall(BUSCAR_PRODUCTO_FACTURACION);
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
