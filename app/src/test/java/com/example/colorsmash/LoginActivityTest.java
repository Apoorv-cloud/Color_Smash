package com.example.colorsmash;

import org.junit.Before;
import org.junit.Test;

//@RunWith(MockitoJUnitRunner.class)

public class LoginActivityTest extends junit.framework.TestCase{

    LoginActivity loginActivity ;

    //@Mock
    //LoginActivity view;
    @Before
    public void  setUp(){
        loginActivity = new LoginActivity();
        //loginActivity.onCreate(null);

    }


    @Test
    public void test_correctEmailAddress() {
        assertTrue(loginActivity.isEmailValid("name@email.com"));
    }

    @Test
    public void test_incorrectEmailAddress() {
        assertFalse(loginActivity.isEmailValid("nameabecemail.com"));
    }

    @Test
    public void test_correctPasswordLength() {
        assertTrue(loginActivity.isPasswordValid("a1b2c3d4e5"));
    }

    @Test
    public void test_incorrectPasswordLength() {
        assertFalse(loginActivity.isPasswordValid("1"));
    }



}