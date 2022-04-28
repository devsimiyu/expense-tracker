package com.devsimiyu.expensetracker.model;

import javax.validation.constraints.NotBlank;

import com.devsimiyu.expensetracker.validation.Unique;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Submit a new category")
public class AddCategoryForm {
    
    @ApiModelProperty(
        allowEmptyValue = false,
        notes = "Category name"
    )
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
