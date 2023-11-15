package com.novi.cabforyou.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("testUser", "ROLE_USER"));
        user.setAuthorities(authorities);

        assertEquals(authorities, user.getAuthorities());
    }

    @Test
    void setAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("testUser", "ROLE_ADMIN"));

        user.setAuthorities(authorities);

        assertEquals(authorities, user.getAuthorities());
    }

}