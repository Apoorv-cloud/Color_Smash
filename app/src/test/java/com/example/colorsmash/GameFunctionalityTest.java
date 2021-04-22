package com.example.colorsmash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameFunctionalityTest {

    private GameFunctionalityActivity act;

    @Before
    public void pre()
    {
        act = new GameFunctionalityActivity();
    }

    @Test
    public void test_startGame() {
        act.startGame();
        assertFalse(act.checkGameOver());
    }

    @Test
    public void test_playerScore() {
        int SCORE=50;
        act.setScore(SCORE);
        assertEquals(act.returnPlayerScore(),SCORE);
    }


    @Test
    public void test_initialScore() {
        int SCORE=0;
        act.initializeScore();
        assertEquals(act.returnPlayerScore(),SCORE);
    }

    @Test
    public void test_checkInitialLocation() {
        int INITIALLOCATION=0;
        act.initializeLocation();
        assertEquals(act.getLocation(),INITIALLOCATION);
    }

    @Test
    public void test_checkChangelLocation() {
        int LOCATION=50;
        act.setLocation(LOCATION);
        assertEquals(act.getLocation(),LOCATION);
    }




}
