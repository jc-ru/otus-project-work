package com.epam.events.steps;

import com.epam.events.pages.EventsPage;
import com.epam.events.tests.Events;
import com.epam.events.utils.DriversManager;
import com.epam.helpers.WorkWithDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static com.epam.events.pages.EventsPage.*;

public class EventsSteps {
    final private static Logger logger = LogManager.getLogger(EventsSteps.class);
    private static WebDriver driver = DriversManager.getDriver();
    private static WebDriverWait wait = DriversManager.getDriverWait();

    // Получить значение счетчика событий
    public static Integer getCounterEvents() {
        return Integer.parseInt(counterEvents.getText());
    }

    // Получить значение счетчика прошедших событий
    public static Integer getCounterPastEvents() {
        return Integer.parseInt(counterPastEvents.getText());
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

    // клик по кнопке Upcoming Events
    public static void clickUpcomingEventsBtn() {
        upcomingEventsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(titleNextWeek));
    }

    // клик по кнопке Past Events
    public static void clickPastEventsBtn() {
        pastEventsBtn.click();
    }


    // проверка наличия местопроведения в карточке события
    public static void assertLocationEvent() {
        String locationText;
        try {
            locationText = locationEvent.getText();
            logger.debug("Место проведения события: {}", locationText);
        } catch (NoSuchElementException ex) {
            logger.error("Элемент местопроведения события не найден");
            locationText = "";
        }
        Assert.assertNotEquals(locationText.trim(), "");
    }

    //  проверка наличия языка в карточке события
    public static void assertLanguageEvent() {
        String langText;
        try {
            langText = langEvent.getText();
            logger.debug("Язык события: {}", langText);
        } catch (NoSuchElementException ex) {
            logger.error("Элемент указания языка события не найден");
            langText = "";
        }
        Assert.assertNotEquals(langText.trim(), "");
    }

    // проверка на наличие наимнования события
    public static void assertNameEvent() {
        String nameEventText;
        try {
            nameEventText = nameEvent.getText();
            logger.debug("Наименование события: {}", nameEventText);
        } catch (NoSuchElementException ex) {
            logger.error("Элемент наименования события не найден");
            nameEventText = "";
        }
        Assert.assertNotEquals(nameEventText.trim(), "");
    }

    // проверка на наличие даты события
    public static void assertDateEvent() {
        String dateEventText;
        try {
            dateEventText = dateEvent.getText();
            logger.debug("Дата события: {}", dateEventText);
        } catch (NoSuchElementException ex) {
            logger.error("Элемент даты события не найден");
            dateEventText = "";
        }
        Assert.assertNotEquals(dateEventText.trim(), "");
    }

    // проверка на наличие типа регистрации события
    public static void assertRegistrationEvent() {
        String regEventText;
        try {
            regEventText = regEvent.getText();
            logger.debug("Тип регистрации события: {}", regEventText);
        } catch (NoSuchElementException ex) {
            logger.error("Элемент типа регистрации события не найден");
            regEventText = "";
        }
        Assert.assertNotEquals(regEventText.trim(), "");
    }

    // проверка на наличие спикера события
    public static void assertSpeakerEvent() {
        WebElement avatarSpeaker;
        try {
            avatarSpeaker = speakerEvent;
            logger.debug("url аватара спикера: {}", avatarSpeaker.getAttribute("src"));
        } catch (NoSuchElementException ex) {
            logger.error("Спикер события не обнаружен");
            avatarSpeaker = null;
        }
        Assert.assertNotNull(avatarSpeaker);
    }

    // Проверка всех дат событий, на актуальность даты (не в прошлом и не на 7 дней больше от текущей даты)
    public static void assertNotPastDateEvent() {
        List<WebElement> cards = datesEvents;
        for (int counter = 0; counter < cards.size(); counter++) {
            String dateStr = cards.get(counter).getText();
            logger.debug("Получена дата события: {}", dateStr);
            Date datePars = WorkWithDate.stringToDate(dateStr);
            if(datePars.before(new Date())) {
                logger.error("Дата события {} находится в прошлом", dateStr);
                Assert.fail();
            } else if(datePars.after(WorkWithDate.stringToDate(WorkWithDate.getDateBeforeOneWeek()))) {
                logger.error("Дата события {} находится на 7 дней позже текущей даты", dateStr);
                Assert.fail();
            }
        }
    }

    // клик по кнопке LocationBtn
    public static void clickLocationBtn() {
        locationBtn.click();
    }

    // клик по чекбоксу в фильтрах Location
    public static void clickCheckboxLocation(String location) {
        driver.findElement(By.xpath("//label[contains(text(), '" + location + "')]")).click();
        wait.until(ExpectedConditions.visibilityOf(titleSearchFilter));
    }

    // Проверить счетчик прошедших событий с кол-вом карточек событий
    public static void assertCounterPastEventsAndCountCards() {
        int countEvents = EventsSteps.getCounterPastEvents();
        int countCardsEvents = EventsSteps.getSizeCardsEvents();
        try {
            Assert.assertEquals(countEvents, countCardsEvents);
        } catch (AssertionError ex) {
            logger.error("Счетчик событий не соответствует кол-ву карточек событий. Счетчик = {}, Карточки = {}", countEvents, countCardsEvents);
            logger.error(ex.getMessage());
            Assert.fail();
        }
    }

    // Проверка всех дат событий, что они в прошедшем времени
    public static void assertPastDateEvent() {
        List<WebElement> cards = allDatesEvents;
        for (int counter = 0; counter < cards.size(); counter++) {
            String dateStr = cards.get(counter).getText();
            logger.debug("Получена дата события: {}", dateStr);
            Date datePars = WorkWithDate.stringToDate(dateStr);
            if(datePars.after(new Date())) {
                logger.error("Дата события {} находится в будущем", dateStr);
                Assert.fail();
            } else if(datePars.equals(WorkWithDate.stringToDate(WorkWithDate.getDateBeforeOneWeek()))) {
                logger.error("Дата события {} находится в настоящем", dateStr);
                Assert.fail();
            }
        }
    }

}
