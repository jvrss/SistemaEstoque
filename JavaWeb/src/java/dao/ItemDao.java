/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Item;
import util.ConnectionFactory;

/**
 *
 * @author SoldierJVX
 */
public class ItemDao {

    private Connection connection;

    public ItemDao() {

        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item get(int id) {

        Item item = null;

        try {
            String sql = "SELECT * FROM item WHERE id = " + id;

            Statement stmt = this.connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                item = new Item(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("amount"), rs.getDate("created_date"), rs.getDate("last_update"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;

    }

    public List<Item> list() {

        List<Item> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM item ORDER BY id";

            Statement stmt = this.connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new Item(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("amount"), rs.getDate("created_date"), rs.getDate("last_update")));
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public boolean insertItem(String nome, String descricao, int quantidade) {

        try {
            String sql = "INSERT INTO item (name, description, amount) VALUES ('" + nome + "', '" + descricao + "', " + quantidade + ")";

            Statement stmt = this.connection.createStatement();

            stmt.executeUpdate(sql);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean updateItem(int id, String nome, String descricao, String quantidade) {
        try {
            String sql = "UPDATE item SET name = '" + nome + "', description = '" + descricao + "', amount = " + quantidade + ", last_update = now() WHERE id = " + id;

            Statement stmt = this.connection.createStatement();

            stmt.executeUpdate(sql);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
