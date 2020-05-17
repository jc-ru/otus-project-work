package com.epam.events.pages;

import com.epam.events.config.ServerConfig;
import com.epam.events.utils.DriversManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EventsPage {

    public static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    final public static Logger logger = LogManager.getLogger(EventsPage.class);

    WebDriver driver;
    WebDriverWait wait;

    // Счетчик событий
    @FindBy(xpath = "//span[contains(text(), 'Upcoming Events')]/following-sibling::span[contains(@class, 'evnt-tab-counter')]")
    public static WebElement counterEvents;

    // Карточки событий
    @FindBy(xpath = "//div[contains(@class, 'evnt-event-card')]")
    public static List<WebElement> cardsEvents;

    public EventsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // url Events Page
    public EventsPage open() {
        driver.get(cfg.getBaseUriProperties() + "/events");

        PageFactory.initElements(driver, this);
        return this;
    }

}
