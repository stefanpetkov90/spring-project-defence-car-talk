package com.springprojectdefence.models.bindingModels.user;

import com.springprojectdefence.entities.Role;

import java.util.Set;

public class UserModel {
    private long id;

    private String username;

    private Set<Role> authorities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
