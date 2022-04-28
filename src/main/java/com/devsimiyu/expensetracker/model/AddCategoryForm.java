package com.devsimiyu.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddCategoryForm {
    
    private String name;

    @JsonCreator
    public AddCategoryForm(@JsonProperty String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
