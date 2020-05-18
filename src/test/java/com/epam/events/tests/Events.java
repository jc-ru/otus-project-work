package com.epam.events.tests;

import com.epam.events.utils.BaseConfigurationTest;
import org.testng.annotations.Test;

import static com.epam.events.steps.events.EventCardSteps.*;
import static com.epam.events.steps.events.EventsSteps.*;


public class Events extends BaseConfigurationTest {


    @Test
    public static void viewUpcomingEvents() {
        openEventsPage();
        assertCounterEventsAndCountCards();
    }

    @Test
    public static void viewEventCards() {
        openEventsPage();
        clickUpcomingEventsBtn();
        assertLocationEvent();
        assertLanguageEvent();
        assertNameEvent();
        assertDateEvent();
        assertRegistrationEvent();
        assertSpeakerEvent();
    }

    @Test
    public static void eventsDateValidation() {
        openEventsPage();
        clickUpcomingEventsBtn();
        assertNotPastDateEvent();
    }

    @Test
    public static void viewPastEventsInCanada() {
        openEventsPage();
        clickPastEventsBtn();
        addFilterLocation("Canada");
        assertCounterPastEventsAndCountCards();
        assertPastDateEvent();
    }

    @Test
    public static void viewEventDetails() {
        openEventsPage();
        clickUpcomingEventsBtn();
        clickEventCard();
        assertRegBtnEventCardPage();
        assertDateEventCardPage();
        assertLocationEventCardPage();
        assertNameEventCardPage();
    }



}
