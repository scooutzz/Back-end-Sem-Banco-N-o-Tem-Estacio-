package cadastrodb.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectorBD {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:derby://localhost:1527/Loja";
        String user = "loja";
        String pass = "123qwe";
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }
    
    public static PreparedStatement getPrepared(String sql) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps;
    }
    
    public static ResultSet getSelect(String sql) throws ClassNotFoundException, SQLException {
        PreparedStatement ps = getPrepared(sql);
        ResultSet rs = ps.executeQuery(sql);
        return rs;
    }
    
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
