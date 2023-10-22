package com.novi.cabforyou.models;


import jakarta.persistence.Entity;

import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name="planners")
public class Planner extends User {
    private String plannerName;

    public Planner() {

    }

    public Planner(String username, String password, Set<Authority> authorities, String email, String firstName, String lastName, String phoneNumber, String plannerName) {
        super(username, password, authorities, email, firstName, lastName, phoneNumber);
        this.plannerName = plannerName;
    }

    public Planner(String plannerName) {
        this.plannerName = plannerName;
    }

}
