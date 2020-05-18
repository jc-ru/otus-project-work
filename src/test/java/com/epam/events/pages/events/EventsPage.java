package com.epam.events.pages.events;

import com.epam.events.config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EventsPage {

    public static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    final private static Logger logger = LogManager.getLogger(EventsPage.class);

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[contains(text(), 'Upcoming Events')]/following-sibling::span[contains(@class, 'evnt-tab-counter')]")
    public static WebElement counterEvents; // Счетчик событий

    @FindBy(xpath = "//span[contains(text(), 'Past Events')]/following-sibling::span[contains(@class, 'evnt-tab-counter')]")
    public static WebElement counterPastEvents; // Счетчик прошедших событий

    @FindBy(xpath = "//div[contains(@class, 'evnt-event-card')]")
    public static List<WebElement> cardsEvents; // Карточки событий

    @FindBy(xpath = "//span[contains(text(), 'Upcoming Events')]/parent::a[contains(@class, 'evnt-tab-link')]")
    public static WebElement upcomingEventsBtn; // Кнопка Upcoming Events

    @FindBy(xpath = "//h3[contains(text(), 'Next week')]")
    public static WebElement titleNextWeek; // Заголовок NEXT WEEK

    @FindBy(xpath = "//p[contains(@class, 'online')]/span")
    public static WebElement locationEvent; // Место проведения события (в карточке)

    @FindBy(xpath = "//p[contains(@class, 'language')]/span")
    public static WebElement langEvent; // Язык события (в карточке)

    @FindBy(xpath = "//div[contains(@class, 'evnt-event-name')]/h1/span")
    public static WebElement nameEvent; // Наименование события (в карточке)

    @FindBy(xpath = "//div[contains(@class, 'evnt-dates-cell')]/p/span[contains(@class, 'date')]")
    public static WebElement dateEvent; // Дата события (в карточке)

    @FindBy(xpath = "//h3[contains(text(), 'Next week')]/following-sibling::div[contains(@class, 'evnt-events-row')]/descendant::p/span[contains(@class, 'date')]")
    public static List<WebElement> datesEvents; // Даты событий (в Next week)

    @FindBy(xpath = "//h3[contains(text(), 'All Events')]/following-sibling::div[contains(@class, 'evnt-events-row')]/descendant::p/span[contains(@class, 'date')]")
    public static List<WebElement> allDatesEvents; // Даты событий (в All events)

    @FindBy(xpath = "//div[contains(@class, 'evnt-dates-cell')]/p/span[contains(@class, 'status')]")
    public static WebElement regEvent; // Тип регистрации события (в карточке)

    @FindBy(xpath = "//div[contains(@class, 'evnt-photo-wrapper')]/img")
    public static WebElement speakerEvent;  // Аватарка спикера события (в карточке)

    @FindBy(xpath = "//span[contains(text(), 'Past Events')]/parent::a[contains(@class, 'evnt-tab-link')]")
    public static WebElement pastEventsBtn; // Кнопка Past Events

    @FindBy(id = "filter_location")
    public static WebElement locationBtn; // Кнопка Location в фильтрах

    @FindBy(xpath = "//p[contains(text(), 'results found for')]")
    public static WebElement titleSearchFilter; // Заголовок результата поиска

    @FindBy(xpath = "//div[contains(@class, 'evnt-countdown-wrapper')]")
    public static WebElement eventTimeLeft;  // Блок отсчета времени при открытии события


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

    // кнопка Upcoming Events
    public EventsPage upcomingEventsBtn() {
        upcomingEventsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(titleNextWeek));
        PageFactory.initElements(driver, this);
        return this;
    }

    // кнопка Past Events
    public EventsPage pastEventsBtn() {
        pastEventsBtn.click();
        PageFactory.initElements(driver, this);
        return this;
    }

    // кнопка Location в фильтрах
    public EventsPage locationBtn() {
        locationBtn.click();
        PageFactory.initElements(driver, this);
        return this;
    }

    // чек-бокс в фильтрах Location
    public EventsPage checkboxLocation(String location) {
        driver.findElement(By.xpath("//label[contains(text(), '" + location + "')]")).click();
        wait.until(ExpectedConditions.visibilityOf(titleSearchFilter));
        PageFactory.initElements(driver, this);
        return this;
    }

    // Первая карточка события
    public EventsPage eventCard() {
        cardsEvents.get(0).click();
        wait.until(ExpectedConditions.visibilityOf(eventTimeLeft));
        PageFactory.initElements(driver, this);
        return this;
    }

}
