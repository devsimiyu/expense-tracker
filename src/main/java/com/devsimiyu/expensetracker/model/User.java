package com.devsimiyu.expensetracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    
    @Id
    @Column
    private Integer id;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "active")
    private Boolean isActive;

    @Column
    private String role;

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public String getRole() {
        return role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
