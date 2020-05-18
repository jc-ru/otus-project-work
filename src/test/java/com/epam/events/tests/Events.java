package com.epam.events.tests;

import com.epam.events.steps.events.EventCardSteps;
import com.epam.events.steps.events.EventsSteps;
import com.epam.events.utils.BaseConfigurationTest;
import org.testng.annotations.Test;



public class Events extends BaseConfigurationTest {


    @Test
    public static void viewUpcomingEvents() {
        mainPage.open();
        navigateSection.clickEventsBtn();
        EventsSteps.assertCounterEventsAndCountCards();
    }

    @Test
    public static void viewEventCards() {
        mainPage.open();
        navigateSection.clickEventsBtn();
        EventsSteps.clickUpcomingEventsBtn();
        EventsSteps.assertLocationEvent();
        EventsSteps.assertLanguageEvent();
        EventsSteps.assertNameEvent();
        EventsSteps.assertDateEvent();
        EventsSteps.assertRegistrationEvent();
        EventsSteps.assertSpeakerEvent();
    }

    @Test
    public static void eventsDateValidation() {
        mainPage.open();
        navigateSection.clickEventsBtn();
        EventsSteps.clickUpcomingEventsBtn();
        EventsSteps.assertNotPastDateEvent();
    }

    @Test
    public static void viewPastEventsInCanada() {
        mainPage.open();
        navigateSection.clickEventsBtn();
        EventsSteps.clickPastEventsBtn();
        EventsSteps.addFilterLocation("Canada");
        EventsSteps.assertCounterPastEventsAndCountCards();
        EventsSteps.assertPastDateEvent();
    }

    @Test
    public static void viewEventDetails() {
        mainPage.open();
        navigateSection.clickEventsBtn();
        EventsSteps.clickUpcomingEventsBtn();
        EventsSteps.clickEventCard();
        EventCardSteps.assertRegBtnEvent();
        EventCardSteps.assertDateEvent();
        EventCardSteps.assertLocationEvent();
        EventCardSteps.assertNameEvent();
    }



}
