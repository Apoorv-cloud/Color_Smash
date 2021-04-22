package com.example.colorsmash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdminLoginTest {

    private AdminLoginActivity act;

    @Before
    public void pre(){
        act = new AdminLoginActivity();
    }

    @Test
    public void test_correctEmailAddress() {
        assertTrue(act.isEmailValid("name@email.com"));
    }

    @Test
    public void test_incorrectEmailAddress() {
        assertFalse(act.isEmailValid("nameabcemail.com"));
    }

    @Test
    public void test_correctPasswordLength() {
        assertTrue(act.isPasswordValid("a1b2c3d4e5"));
    }

    @Test
    public void test_incorrectPasswordLength() {
        assertFalse(act.isPasswordValid("2"));
    }

    @Test
    public void test_shaFunctionHashLength() {
        int realLen=act.sha("1").length();
        int expectedLen=64;
        assertEquals(realLen,expectedLen);
    }
}

