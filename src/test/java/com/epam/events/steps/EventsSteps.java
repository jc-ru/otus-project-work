package com.epam.events.steps;

import com.epam.events.pages.EventsPage;
import com.epam.events.tests.Events;
import com.epam.events.utils.DriversManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static com.epam.events.pages.EventsPage.*;

public class EventsSteps {
    final private static Logger logger = LogManager.getLogger(EventsSteps.class);
//    private WebDriver driver = DriversManager.getDriver();
//    private WebDriverWait wait = DriversManager.getDriverWait();

    // Получить значение счетчика событий
    public static Integer getCounterEvents() {
        return Integer.parseInt(counterEvents.getText());
    }

    // Получить кол-во карточек событий
    public static Integer getSizeCardsEvents() {
        List<WebElement> cards = cardsEvents;
        return cards.size();
    }

    // Проверить счетчик событий с кол-вом карточек событий
    public static void assertCounterEventsAndCountCards() {
        int countEvents = EventsSteps.getCounterEvents();
        int countCardsEvents = EventsSteps.getSizeCardsEvents();
        try {
            Assert.assertEquals(countEvents, countCardsEvents);
        } catch (AssertionError ex) {
            logger.error("Счетчик событий не соответствует кол-ву карточек событий. Счетчик = {}, Карточки = {}", countEvents, countCardsEvents);
            logger.error(ex.getMessage());
            Assert.fail();
        }
    }

}
