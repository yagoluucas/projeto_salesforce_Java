package org.configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleDatabase {
    private static Properties properties = new Properties();

    static{
        try {
            properties.load(new FileInputStream("credenciais.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getUser() {
        return properties.getProperty("userName");
    }

    private String getPassword() {
        return properties.getProperty("password");
    }

    private String getUrl() {
        return properties.getProperty("url");
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            return DriverManager.getConnection(getUrl(), getUser(),getPassword());
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
