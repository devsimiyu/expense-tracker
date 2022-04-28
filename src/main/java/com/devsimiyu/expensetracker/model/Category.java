package com.devsimiyu.expensetracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
    
    @Id
    @Column
    private int id;

    @Column
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        this.id  = id;
    }

    public void setName(String name) {
        this.name  = name;
    }
}
