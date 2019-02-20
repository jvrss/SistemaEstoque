package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;
import util.ConnectionFactory;

public class AuthenticateDao 
{
    private Connection connection;

    public AuthenticateDao(){    
        
        try
        {
            this.connection =  ConnectionFactory.getConnection() ;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
   }
    
   public User authenticate(String login, String password) {

        try {
            String sql = "SELECT * FROM user_data WHERE login = '" + login + "' AND password = '" + password + "'";

            Statement stmt = this.connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("login"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}