package com.example.colorsmash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StartGameActivityTest {

    private StartGameActivity act;

    @Before
    public void pre(){
        act = new StartGameActivity();
    }



    @Test
    public void test_hitBox() {

       assertFalse(act.hitCheck((float)0.5,(float)0.5,(float)0.5,(float)0.5));
    }

}