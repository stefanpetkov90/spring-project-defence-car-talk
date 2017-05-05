package com.springprojectdefence.models.bindingModels;

import com.springprojectdefence.annotations.IsPasswordMatch;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@IsPasswordMatch
public class RegistrationModel {

    @Size(min = 5, message = "Username too short")
    private String username;

    @Size(min = 5, message ="Password too short" )
    private String password;

    private String confirmedPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
