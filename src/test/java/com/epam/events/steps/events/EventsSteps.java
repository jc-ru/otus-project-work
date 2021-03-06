package com.epam.events.steps.events;

import com.epam.events.pages.events.EventsPage;
import com.epam.events.pages.main.MainPage;
import com.epam.events.pages.main.sections.NavigateSection;
import com.epam.events.utils.DriversManager;
import com.epam.events.helpers.WorkWithDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static com.epam.events.pages.events.EventsPage.*;


public class EventsSteps {
    final private static Logger logger = LogManager.getLogger(EventsSteps.class);
    private static WebDriver driver = DriversManager.getDriver();
    private static WebDriverWait wait = DriversManager.getDriverWait();
    private static EventsPage eventsPage;
    private static MainPage mainPage;
    private static NavigateSection navigateSection;

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

    // Открыть Events из под главной страницы
    public void openEventsPage(){
        mainPage = new MainPage(driver, wait);
        mainPage.open();
        navigateSection = new NavigateSection(driver, wait);
        navigateSection.clickEventsBtn();
    }

    // Клик по кнопке Upcoming Events
    public void clickUpcomingEventsBtn() {
        eventsPage = new EventsPage(driver, wait);
        eventsPage.upcomingEventsBtn();
    }

    // Клик по кнопке Past Events
    public void clickPastEventsBtn() {
        eventsPage = new EventsPage(driver, wait);
        eventsPage.pastEventsBtn();
    }

    // Добавить фильтр по локации
    public void addFilterLocation(String location) {
        eventsPage = new EventsPage(driver, wait);
        eventsPage
                .locationBtn()
                .checkboxLocation(location);
    }

    // Клик по первой карточке события
    public void clickEventCard() {
        eventsPage = new EventsPage(driver, wait);
        try {
            eventsPage.eventCard();
        } catch (TimeoutException ex) {
            logger.error("Не удалось открыть карточку события");
            Assert.fail(ex.getMessage());
        }

    }

    // Проверить счетчик событий с кол-вом карточек событий
    public void assertCounterEventsAndCountCards() {
        int countEvents = EventsSteps.getCounterEvents();
        int countCardsEvents = EventsSteps.getSizeCardsEvents();
        try {
            Assert.assertEquals(countEvents, countCardsEvents);
            logger.debug("Счетчик событий соответствует кол-ву карточек событий. Счетчик = {}, Карточки = {}", countEvents, countCardsEvents);
        } catch (AssertionError ex) {
            logger.error("Счетчик событий не соответствует кол-ву карточек событий. Счетчик = {}, Карточки = {}", countEvents, countCardsEvents);
            logger.error(ex.getMessage());
            Assert.fail();
        }
    }

    // Проверить счетчик прошедших событий с кол-вом карточек событий
    public void assertCounterPastEventsAndCountCards() {
        int countEvents = EventsSteps.getCounterPastEvents();
        int countCardsEvents = EventsSteps.getSizeCardsEvents();
        try {
            Assert.assertEquals(countEvents, countCardsEvents);
            logger.debug("Счетчик событий не соответствует кол-ву карточек событий. Счетчик = {}, Карточки = {}", countEvents, countCardsEvents);
        } catch (AssertionError ex) {
            logger.error("Счетчик событий не соответствует кол-ву карточек событий. Счетчик = {}, Карточки = {}", countEvents, countCardsEvents);
            logger.error(ex.getMessage());
            Assert.fail();
        }
    }

    // проверка наличия местопроведения в карточке события
    public void assertLocationEvent() {
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
    public void assertLanguageEvent() {
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
    public void assertNameEvent() {
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
    public void assertDateEvent() {
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
    public void assertRegistrationEvent() {
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
    public void assertSpeakerEvent() {
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
    public void assertNotPastDateEvent() {
        List<WebElement> cards = datesEvents;
        for (int counter = 0; counter < cards.size(); counter++) {
            String dateStr = cards.get(counter).getText();
            logger.debug("Получена дата события: {}", dateStr);
            Date datePars = WorkWithDate.stringToDate(dateStr);
            if(datePars.before(new Date())) {
                logger.error("Дата события {} находится в прошлом", dateStr);
                Assert.fail("Дата события находится в прошлом " + dateStr);
            } else if(datePars.after(WorkWithDate.stringToDate(WorkWithDate.getDateBeforeOneWeek()))) {
                logger.error("Дата события {} находится более чем на 7 дней позже текущей даты", dateStr);
                Assert.fail("Дата события находится более чем на 7 дней позже текущей даты " + dateStr);
            }
        }
    }

    // Проверка всех дат событий, что они в прошедшем времени
    public void assertPastDateEvent() {
        List<WebElement> cards = allDatesEvents;
        for (int counter = 0; counter < cards.size(); counter++) {
            String dateStr = cards.get(counter).getText();
            logger.debug("Получена дата события: {}", dateStr);
            Date datePars = WorkWithDate.stringToDate(dateStr);
            if(datePars.after(new Date())) {
                logger.error("Дата события {} находится в будущем", dateStr);
                Assert.fail("Дата события находится в будущем " + dateStr);
            } else if(datePars.equals(new Date())) {
                logger.error("Дата события {} находится в настоящем", dateStr);
                Assert.fail("Дата события находится в настоящем " + dateStr);
            }
        }
    }

}
