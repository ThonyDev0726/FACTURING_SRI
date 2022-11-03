/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anthony.ModelsDAO;

import com.anthony.Database.Conexion;
import com.anthony.Models.FACTURA;
import com.anthony.ModelsInterfaces.*;
import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FACTURA_DAO implements crud_factura {

    FACTURA c = new FACTURA();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    String LISTAR = "CALL SELECT_FACTURA()";
    String LISTAR_ID = "CALL SELECT_FACTURA_ID(?)";
    String CREAR = "CALL INSERT_FACTURA(?,?,?,?,?,?,?,?,?)";
    String ELIMINAR = "CALL DELETE_FACTURA(?,?,?,?,?,?,?,?,?)";
    String ULTIMA_FACTURA = "CALL SELECT_FACTURA_LAST()";
    String NUMERO = "";
    String SERIE = "";
    Integer NUM;
    String YEAR = "";
    String MES = "";
    String DIA = "";
    String HORA = "";
    String MINUTO = "";
    String SEGUNDO = "";
    //Fecha actual desglosada:
    Calendar fecha = Calendar.getInstance();
    int year = fecha.get(Calendar.YEAR);
    int mes = fecha.get(Calendar.MONTH) + 1;
    int dia = fecha.get(Calendar.DAY_OF_MONTH);
    int hora = fecha.get(Calendar.HOUR_OF_DAY);
    int minuto = fecha.get(Calendar.MINUTE);
    int segundo = fecha.get(Calendar.SECOND);

    @Override
    public List listar() {
        ArrayList<FACTURA> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR);
            rs = cs.executeQuery();
            while (rs.next()) {
                FACTURA cli = new FACTURA();
                cli.setID_FACTURA(rs.getInt(1));
                cli.setEMP_NOMBRES(rs.getString(2));
                cli.setEMP_APELLIDOS(rs.getString(3));
                cli.setCLI_NOMBRES(rs.getString(4));
                cli.setCLI_APELLIDOS(rs.getString(5));
                cli.setSUC_NOMBRE(rs.getString(6));
                cli.setFAC_FECHA(rs.getString(7));
                cli.setFAC_HORA(rs.getString(8));
                cli.setFAC_CODIGO(rs.getString(9));
                cli.setFAC_COD_AUT(rs.getString(10));
                cli.setFAC_RUTA(rs.getString(11));
                cli.setFAC_ESTADO(rs.getString(12));
                lista.add(cli);
            }
            System.out.println("SE ESTA LISTANDO LAS FACTURAS");
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LAS FACTURAS: " + ex);
        }
        return lista;
    }

    @Override
    public FACTURA list(int idFactura) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(LISTAR_ID);
            cs.setInt(1, idFactura);
            rs = cs.executeQuery();
            while (rs.next()) {
                c.setID_FACTURA(rs.getInt(1));
                c.setEMP_NOMBRES(rs.getString(2));
                c.setEMP_APELLIDOS(rs.getString(3));
                c.setCLI_NOMBRES(rs.getString(4));
                c.setCLI_APELLIDOS(rs.getString(5));
                c.setSUC_NOMBRE(rs.getString(6));
                c.setFAC_FECHA(rs.getString(7));
                c.setFAC_HORA(rs.getString(8));
                c.setFAC_CODIGO(rs.getString(9));
                c.setFAC_COD_AUT(rs.getString(10));
                c.setFAC_RUTA(rs.getString(11));
                c.setFAC_ESTADO(rs.getString(12));
            }
            System.out.println("SE ESTA LISTANDO LA FACTURA");
        } catch (SQLException ex) {
            System.out.println("ERROR AL LISTAR LA FACTURA " + idFactura + " " + ex);
        }
        return c;
    }

    @Override
    public String add(FACTURA cliente) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CREAR);
            cs.setInt(1, cliente.getFK_EMPLEADO());
            cs.setInt(2, cliente.getFK_CLIENTE());
            cs.setInt(3, cliente.getFK_SUCURSAL());
            cs.setString(4, cliente.getFAC_FECHA());
            cs.setString(5, cliente.getFAC_HORA());
            cs.setString(6, cliente.getFAC_CODIGO());
            cs.setString(7, cliente.getFAC_COD_AUT());
            cs.setString(8, cliente.getFAC_RUTA());
            cs.setString(9, cliente.getFAC_ESTADO());
            cs.execute();
            System.out.println("SE CREO LA FACTURA CON EXITO");
        } catch (SQLException ex) {
            System.out.println("ERROR AL CREAR LA FACTURA");
            System.out.println(ex);
            return "La factura no fue creada!";
        }
        return "La factura fue creada con exito!";
    }

    public String ultimaFactura_id() {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(ULTIMA_FACTURA);
            rs = cs.executeQuery();
            while (rs.next()) {
                NUMERO = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("NUMERO_FACT ERROR: " + e);
        }
        return NUMERO;
    }

    public String generadorNumeroFactura(String RUC, String cedula) {
        if (ultimaFactura_id() == null) {
            return SERIE = DIA + "" + "" + MES + "" + YEAR + "" + "01" + RUC + "" + cedula + "" + HORA + "" + MINUTO + "" + SEGUNDO + "" + generateRandomPassword(6) + "01";
        }
        NUM = Integer.parseInt(ultimaFactura_id()) + 1;
        if (dia < 0) {
            DIA = "0" + dia;
        } else {
            DIA = String.valueOf(dia);
        }
        if (mes < 10) {
            MES = "0" + mes;
        } else {
            MES = String.valueOf(mes);
        }
        if (hora < 10) {
            HORA = "0" + hora;
        } else {
            HORA = String.valueOf(hora);
        }
        if (minuto < 10) {
            MINUTO = "0" + minuto;
        } else {
            MINUTO = String.valueOf(minuto);
        }
        if (segundo < 10) {
            SEGUNDO = "0" + segundo;
        } else {
            SEGUNDO = String.valueOf(segundo);
        }
        YEAR = String.valueOf(year);

        SERIE = DIA + "" + "" + MES + "" + YEAR + "" + "01" + RUC + "" + cedula + "" + HORA + "" + MINUTO + "" + SEGUNDO + "" + generateRandomPassword(6) + "" + NUM;
        return SERIE;
    }

    public String generadorNumeroFacturaCosumidor(String RUC, String cedula) {
        if (ultimaFactura_id() == null) {
            return SERIE = DIA + "" + "" + MES + "" + YEAR + "" + "01" + RUC + "" + cedula + "" + HORA + "" + MINUTO + "" + SEGUNDO + "" + generateRandomPassword(6) + "01";
        }
        NUM = Integer.parseInt(ultimaFactura_id()) + 1;
        if (cedula == null || cedula.length() == 0 || cedula.equals("N / A")) {
            cedula = "0000000000";
            return SERIE = DIA + "" + "" + MES + "" + YEAR + "" + "01" + RUC + "" + cedula + "" + HORA + "" + MINUTO + "" + SEGUNDO + "" + generateRandomPassword(6) + "" + ultimaFactura_id();
        }
        if (dia < 0) {
            DIA = "0" + dia;
        } else {
            DIA = String.valueOf(dia);
        }
        if (mes < 10) {
            MES = "0" + mes;
        } else {
            MES = String.valueOf(mes);
        }
        if (hora < 10) {
            HORA = "0" + hora;
        } else {
            HORA = String.valueOf(hora);
        }
        if (minuto < 10) {
            MINUTO = "0" + minuto;
        } else {
            MINUTO = String.valueOf(minuto);
        }
        if (segundo < 10) {
            SEGUNDO = "0" + segundo;
        } else {
            SEGUNDO = String.valueOf(segundo);
        }
        YEAR = String.valueOf(year);
        SERIE = DIA + "" + "" + MES + "" + YEAR + "" + "01" + RUC + "" + cedula + "" + HORA + "" + MINUTO + "" + SEGUNDO + "" + generateRandomPassword(6) + "" + NUM;
        return SERIE;
    }

    public String fechaNormal() {
        return year + "-" + mes + "-" + dia;
    }

    public String fecha() {
        return year + "" + mes + "" + dia;
    }

    public String horaNormal() {
        return hora + ":" + minuto + ":" + segundo;
    }

    public String hora() {
        return hora + "" + minuto + "" + segundo;
    }

    public static String generateRandomPassword(int len) {
        final String chars = "0123456789";

        SecureRandom random = new SecureRandom();

        // cada iteración del bucle elige aleatoriamente un carácter del dado
        // rango ASCII y lo agrega a la instancia `StringBuilder`
        return IntStream.range(0, len)
                .map(i -> random.nextInt(chars.length()))
                .mapToObj(randomIndex -> String.valueOf(chars.charAt(randomIndex)))
                .collect(Collectors.joining());
    }

    @Override
    public String update(FACTURA mp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String delete(int id) {
        try {
            con = (Connection) cn.getConexion();
            CallableStatement cs = con.prepareCall("CALL DELETE_FACTURA(?)");
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

}
