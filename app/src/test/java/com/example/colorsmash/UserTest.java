package com.example.colorsmash;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserTest {

    private User act;

    @Before
    public void pre(){
        act = new User();
    }

    @Test
    public void test_username()
    {
        act.setName("Apoorv4");
        assertEquals("Apoorv4",act.getName());
    }

    @Test
    public void test_score()
    {
        act.setHighscore("300");
        assertEquals("300",act.getHighscore());
    }

    @Test
    public void test_age()
    {
        act.setAge(30);
        assertEquals(30,act.getAge());
    }

    @Test
    public void test_checkScores()
    {
        ArrayList<String> score = new ArrayList<String>();
        score.add("30");
        score.add("40");
        score.add("50");

        act.setScores(score);

        assertEquals(score.get(0),act.getScores().get(0));
    }

    @Test
    public void test_checkScores2()
    {
        ArrayList<String> score = new ArrayList<String>();
        score.add("30");
        score.add("40");
        score.add("50");

        act.setScores(score);

        assertEquals(score.get(1),act.getScores().get(1));
    }

    @Test
    public void test_checkScores3()
    {
        ArrayList<String> score = new ArrayList<String>();
        score.add("30");
        score.add("40");
        score.add("50");

        act.setScores(score);

        assertEquals(score.get(2),act.getScores().get(2));
    }

    @Test
    public void test_checkScores4()
    {
        ArrayList<String> score = new ArrayList<String>();
        score.add("30");
        score.add("40");
        score.add("50");

        act.setScores(score);

        assertNotEquals(score.get(2),act.getScores().get(1));
    }

    @Test
    public void test_badColors()
    {
        ArrayList<String> badColors = new ArrayList<String>();
        badColors.add("RED");
        badColors.add("GREEN");
        badColors.add("BLUE");
        act.setBadColors(badColors);
        assertEquals("BLUE",act.getBadColors().get(2));
    }


    @Test
    public void test_badColors2()
    {
        ArrayList<String> badColors = new ArrayList<String>();
        badColors.add("RED");
        badColors.add("GREEN");
        badColors.add("BLUE");
        act.setBadColors(badColors);
        assertEquals("GREEN",act.getBadColors().get(1));
    }

    @Test
    public void test_gender()
    {
        act.setGender("Male");
        assertEquals("Male",act.getGender());
    }

    @Test
    public void test_badColors3()
    {
        ArrayList<String> badColors = new ArrayList<String>();
        badColors.add("RED");
        badColors.add("GREEN");
        badColors.add("BLUE");
        act.setBadColors(badColors);
        assertNotEquals("RED",act.getBadColors().get(1));
    }


}