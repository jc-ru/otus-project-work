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

    // Счетчик прошедших событий
    @FindBy(xpath = "//span[contains(text(), 'Past Events')]/following-sibling::span[contains(@class, 'evnt-tab-counter')]")
    public static WebElement counterPastEvents;

    // Карточки событий
    @FindBy(xpath = "//div[contains(@class, 'evnt-event-card')]")
    public static List<WebElement> cardsEvents;

    // Кнопка Upcoming Events
    @FindBy(xpath = "//span[contains(text(), 'Upcoming Events')]/parent::a[contains(@class, 'evnt-tab-link')]")
    public static WebElement upcomingEventsBtn;

    // Заголовок NEXT WEEK
    @FindBy(xpath = "//h3[contains(text(), 'Next week')]")
    public static WebElement titleNextWeek;

    // Место проведения события (в карточке)
    @FindBy(xpath = "//p[contains(@class, 'online')]/span")
    public static WebElement locationEvent;

    // Язык события (в карточке)
    @FindBy(xpath = "//p[contains(@class, 'language')]/span")
    public static WebElement langEvent;

    // Наименование события (в карточке)
    @FindBy(xpath = "//div[contains(@class, 'evnt-event-name')]/h1/span")
    public static WebElement nameEvent;

    // Дата события (в карточке)
    @FindBy(xpath = "//div[contains(@class, 'evnt-dates-cell')]/p/span[contains(@class, 'date')]")
    public static WebElement dateEvent;

    // Даты событий (в Next week)
    @FindBy(xpath = "//h3[contains(text(), 'Next week')]/following-sibling::div[contains(@class, 'evnt-events-row')]/descendant::p/span[contains(@class, 'date')]")
    public static List<WebElement> datesEvents;

    // Даты событий (в All events)
    @FindBy(xpath = "//h3[contains(text(), 'All Events')]/following-sibling::div[contains(@class, 'evnt-events-row')]/descendant::p/span[contains(@class, 'date')]")
    public static List<WebElement> allDatesEvents;

    // Тип регистрации события (в карточке)
    @FindBy(xpath = "//div[contains(@class, 'evnt-dates-cell')]/p/span[contains(@class, 'status')]")
    public static WebElement regEvent;

    // Аватарка спикера события (в карточке)
    @FindBy(xpath = "//div[contains(@class, 'evnt-photo-wrapper')]/img")
    public static WebElement speakerEvent;

    // Кнопка Past Events
    @FindBy(xpath = "//span[contains(text(), 'Past Events')]/parent::a[contains(@class, 'evnt-tab-link')]")
    public static WebElement pastEventsBtn;

    // Кнопка Location в фильтрах
    @FindBy(id = "filter_location")
    public static WebElement locationBtn;

    // Заголовок результата поиска
    @FindBy(xpath = "//p[contains(text(), 'results found for')]")
    public static WebElement titleSearchFilter;

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
