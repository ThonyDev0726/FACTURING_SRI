package com.anthony.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author perez
 */
public class Conexion {

    public static Connection getConexion() {
        Connection cn = null;
        String DATA_BASE = "facturing_escritorio";
        String USER = "root";
        String HOST_NUBE = "node117970-env-8974925.jelastic.saveincloud.net";
//        String HOST = "localhost";
        String HOST = "192.168.1.3"; //La ip que tenga el cliente
        String PASSWORD = "";
        String PASSWORD_NUBE = "bvOOtZA0qE";
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATA_BASE + "?serverTimezone=UTC", USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexion");
            System.out.println("" + e);
        }
        return cn;
    }
}
