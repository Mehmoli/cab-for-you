package com.novi.cabforyou.dtos;

import com.novi.cabforyou.models.Authority;

import java.util.Set;

public class PlannerDto {

    public String username;

    public String password;

    public String email;

    public String employeeNumber;

    public Set<Authority> authorities;
    public PlannerDto() {
    }

    public PlannerDto(String username, String password, String email, String employeeNumber, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.employeeNumber = employeeNumber;
        this.authorities = authorities;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
