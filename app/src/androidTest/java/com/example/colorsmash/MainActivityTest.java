package com.example.colorsmash;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import android.view.View;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor monitorLogin = getInstrumentation().addMonitor(LoginActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitorAdminLogin = getInstrumentation().addMonitor(AdminLoginActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitorRegister = getInstrumentation().addMonitor(RegisterActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitorStartGame = getInstrumentation().addMonitor(StartGameActivity.class.getName(),null,false);
    //Instrumentation.ActivityMonitor monitorMainActivity = getInstrumentation().addMonitor(MainActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {

        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch()
    {
        View view = mainActivity.findViewById(R.id.ButtonStartGame);

        assertNotNull(view);

    }

    @Test
    public void testLaunchOfLoginActivityOnButtonClick()
    {
        assertNotNull(mainActivity.findViewById(R.id.ButtonLogin));

        onView(withId(R.id.ButtonLogin)).perform(click()); // mock a click on a button

        Activity LoginActivity = getInstrumentation().waitForMonitorWithTimeout(monitorLogin,5000);

        assertNotNull(LoginActivity);

        LoginActivity.finish();

    }

    @Test
    public void testLaunchOfRegisterActivityOnButtonClick()
    {
        assertNotNull(mainActivity.findViewById(R.id.ButtonRegister));

        onView(withId(R.id.ButtonRegister)).perform(click()); // mock a click on a button

        Activity RegisterActivity = getInstrumentation().waitForMonitorWithTimeout(monitorRegister,5000);

        assertNotNull(RegisterActivity);

        RegisterActivity.finish();

    }

    @Test
    public void testLaunchOfAdminLoginActivityOnButtonClick()
    {
        assertNotNull(mainActivity.findViewById(R.id.ButtonAdminLogin));

        onView(withId(R.id.ButtonAdminLogin)).perform(click()); // mock a click on a button

        Activity AdminLoginActivity = getInstrumentation().waitForMonitorWithTimeout(monitorAdminLogin,5000);

        assertNotNull(AdminLoginActivity);

        AdminLoginActivity.finish();

    }


    @Test
    public void testLaunchOfStartGameActivityOnButtonClick()
    {
        assertNotNull(mainActivity.findViewById(R.id.ButtonStartGame));

        onView(withId(R.id.ButtonStartGame)).perform(click()); // mock a click on a button

        Activity StartGameActivity  = getInstrumentation().waitForMonitorWithTimeout(monitorStartGame,5000);

        assertNull(StartGameActivity);

    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.colorsmash", appContext.getPackageName());
    }


    @After
    public void tearDown() throws Exception {

        mainActivity = null;
    }
}