package com.example.colorsmash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegisterActivityTest {
    private RegisterActivity act;

    @Before
    public void pre(){
        act = new RegisterActivity();
    }

    @Test
    public void test_correctEmailAddress() {
        assertTrue(act.isEmailValid("name@email.com"));
    }

    @Test
    public void test_incorrectEmailAddress1() {
        assertFalse(act.isEmailValid("namebcemail.com"));
    }


    @Test
    public void test_correctPasswordLength() {
        assertTrue(act.isPasswordValid("a1b2c3d4e5"));
    }

    @Test
    public void test_incorrectPasswordLength() {
        assertFalse(act.isPasswordValid("1"));
    }



}