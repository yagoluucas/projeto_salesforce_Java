package org.configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDatabase {
    private static String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static String user = "rm553013";
    private static String password = "090900";

    public Connection getConnection() {
        Connection connection = null;
        try {
            return DriverManager.getConnection(url, user,password);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
