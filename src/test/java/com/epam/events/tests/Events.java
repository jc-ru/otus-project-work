package com.epam.events.tests;

import com.epam.events.steps.EventsSteps;
import com.epam.events.utils.BaseConfigurationTest;
import org.testng.annotations.Test;


public class Events extends BaseConfigurationTest {


    @Test
    public static void viewUpcomingEvents() {
        mainPage.open();
        navigateSection.clickEventsBtn();
        EventsSteps.assertCounterEventsAndCountCards();
    }

}
