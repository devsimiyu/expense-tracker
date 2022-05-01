package com.devsimiyu.expensetracker.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LoginForm {
    
    @ApiModelProperty
    @NotBlank
    @Email
    private String email;

    @ApiModelProperty
    @NotBlank
    private String password;

    @JsonCreator
    public LoginForm(
        @JsonProperty String email,
        @JsonProperty String password
    ) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
