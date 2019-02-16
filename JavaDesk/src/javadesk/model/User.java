/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadesk.model;

/**
 *
 * @author SoldierJVX
 */
public class User {

    private int id;
    private String login;
    private String name;

    public User(int id, String login, String name) {
        this.id = id;
        this.login = login;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

}
