package com.epam.events.steps.events;

import com.epam.events.utils.DriversManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.NoSuchElementException;

import static com.epam.events.pages.events.CardEventPage.*;


public class EventCardSteps {
    final private static Logger logger = LogManager.getLogger(EventCardSteps.class);
    private static WebDriver driver = DriversManager.getDriver();
    private static WebDriverWait wait = DriversManager.getDriverWait();

    // проверка на наличие кнопки на регистрацию события
    public static void assertRegBtnEvent() {
        try {
            WebElement regBtn = regEventBtn;
            logger.debug("Элемент кнопки регистрации на событие - обнаружен");
        } catch (NoSuchElementException ex) {
            logger.error("Элемент кнопки регистрации на событие - не найден");
            Assert.fail(ex.getMessage());
        }

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

    // проверка наличия местопроведения в карточке события
    public static void assertLocationEvent() {
        String locationText;
        try {
            locationText = locationEvent2.getText();
            logger.debug("Место проведения события: {}", locationText);
        } catch (NoSuchElementException ex) {
            logger.error("Элемент местопроведения события не найден");
            locationText = "";
        }
        Assert.assertNotEquals(locationText.trim(), "");
    }

    // проверка на наличие даты события
    public static void assertDateEvent() {
        String dateEvent;
        try {
            dateEvent = dateEvent2.getText();
            logger.debug("Дата события: {}", dateEvent);
        } catch (NoSuchElementException ex) {
            logger.error("Элемент даты события не найден");
            dateEvent = "";
        }
        Assert.assertNotEquals(dateEvent.trim(), "");
    }
}
