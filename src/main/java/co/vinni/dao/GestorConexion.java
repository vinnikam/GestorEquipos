package co.vinni.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorConexion {

    //jdbc:postgresql://[host]:[puerto]/[nombre_base_de_datos]

    public static Connection obtenerConexion() throws ClassNotFoundException, SQLException {
        String usuario = "123";
        String clave = "1231";
        String url = "jdbc:postgresql://127.0.0.1:5432/neondb";

        return DriverManager.getConnection(url, usuario, clave);
    }


}
