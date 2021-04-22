package com.example.colorsmash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminTest {

    Admin admin;

    @Before
    public void pre(){

        admin = new Admin();
    }


    @Test
    public void testUsername() {
        admin.setUsername("name@gmail.com");
        String userName = "name@gmail.com";
        assertEquals(userName,admin.getUsername());
    }


    @Test
    public void testUserPassword() {
        admin.setPassword("a1b2c3d4e5");
        String password = "a1b2c3d4e5";
        assertEquals(password,admin.getPassword());
    }

}