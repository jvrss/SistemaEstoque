/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadesk.model;

import java.sql.Date;

/**
 *
 * @author SoldierJVX
 */
public class Item {
    
    private int id;
    private String name;
    private String description;
    private int amount;
    private Date createdDate;
    private Date lastUpdate;

    public Item(int id, String name, String description, int amount, Date createdDate, Date lastUpdate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.createdDate = createdDate;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
    
    
    
}
