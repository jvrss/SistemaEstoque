package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {
    private ConnectionFactory(){}
    
    public static Connection getConnection() throws SQLException{
        try
        {
            Class.forName("org.postgresql.Driver");
            
            Connection conn = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost/storage","postgres","master");
            
            return conn;  
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new SQLException(ex.getMessage());
        }  
    }
}