//	package com.org.helper;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class ConnectionHelper {
//	
//	public static Connection getConObj(){
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","1111");
//			return con;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//		
//	}
//
//}




package com.org.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    // Instanța conexiunii
    private static Connection connection = null;

    // Metodă pentru obținerea instanței conexiunii (Singleton)
    public static Connection getConObj() {
        // Verificăm dacă instanța există deja
        if (connection == null) {
            try {
                // Încărcăm driverul JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Obținem conexiunea la baza de date
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "1111");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Metodă pentru închiderea conexiunii
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
