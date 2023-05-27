package ar.edu.d2s;

import org.junit.Test;

import java.sql.*;

public class ConnTest {
    @Test
    public void testConn() throws Exception {
        final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/dds";
        final String DB_USER = "postgres";
        final String DB_PASSWORD = "123";
        Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,	DB_PASSWORD);

        Statement statement = dbConnection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT id, propietario from Casa");
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String prop = rs.getString("propietario");
            System.out.println(id);
            System.out.println(prop);
        }
    }
}
