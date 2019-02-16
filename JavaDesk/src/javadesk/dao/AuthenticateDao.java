/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadesk.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javadesk.model.User;
import javadesk.util.ConnectionFactory;
import javadesk.util.Md5;

/**
 *
 * @author SoldierJVX
 */
public class AuthenticateDao {

    private Connection connection;

    public AuthenticateDao() {

        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
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
