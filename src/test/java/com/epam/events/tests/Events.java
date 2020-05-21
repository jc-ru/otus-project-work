package com.epam.events.tests;

import com.epam.events.steps.events.EventCardSteps;
import com.epam.events.steps.events.EventsSteps;
import com.epam.events.utils.BaseConfigurationTest;
import org.testng.annotations.Test;


public class Events extends BaseConfigurationTest {
    EventCardSteps eventCardSteps = new EventCardSteps();
    EventsSteps eventsSteps = new EventsSteps();

    @Test
    void viewUpcomingEvents() {
        eventsSteps.openEventsPage();
        eventsSteps.assertCounterEventsAndCountCards();
    }

    @Test
    void viewEventCards() {
        eventsSteps.openEventsPage();
        eventsSteps.clickUpcomingEventsBtn();
        eventsSteps.assertLocationEvent();
        eventsSteps.assertLanguageEvent();
        eventsSteps.assertNameEvent();
        eventsSteps.assertDateEvent();
        eventsSteps.assertRegistrationEvent();
        eventsSteps.assertSpeakerEvent();
    }

    @Test
    void eventsDateValidation() {
        eventsSteps.openEventsPage();
        eventsSteps.clickUpcomingEventsBtn();
        eventsSteps.assertNotPastDateEvent();
    }

    @Test
    void viewPastEventsInCanada() {
        eventsSteps.openEventsPage();
        eventsSteps.clickPastEventsBtn();
        eventsSteps.addFilterLocation("Canada");
        eventsSteps.assertCounterPastEventsAndCountCards();
        eventsSteps.assertPastDateEvent();
    }

    @Test
    void viewEventDetails() {
        eventsSteps.openEventsPage();
        eventsSteps.clickUpcomingEventsBtn();
        eventsSteps.clickEventCard();
        eventCardSteps.assertRegBtnEventCardPage();
        eventCardSteps.assertDateEventCardPage();
        eventCardSteps.assertLocationEventCardPage();
        eventCardSteps.assertNameEventCardPage();
    }



}
