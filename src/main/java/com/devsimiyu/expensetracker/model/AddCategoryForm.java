package com.devsimiyu.expensetracker.model;

import javax.validation.constraints.NotBlank;

import com.devsimiyu.expensetracker.validation.Unique;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddCategoryForm {
    
    @NotBlank
    @Unique(message = "Name already exists")
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
