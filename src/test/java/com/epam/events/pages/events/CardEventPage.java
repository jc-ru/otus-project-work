package com.epam.events.pages.events;

import com.epam.events.config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CardEventPage {

    public static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    final public static Logger logger = LogManager.getLogger(CardEventPage.class);

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "home")
    public static WebElement headerEvent;  // Шапка события

    @FindBy(xpath = "//button[contains(@class, 'reg-button')]")
    public static WebElement regEventBtn;  //Кнопка регистрации на событие в шапке

    @FindBy(xpath = "//div[contains(@class, 'evnt-details')]/span")
    public static WebElement locationEvent2; // Место проведения события

    @FindBy(xpath = "//div[contains(@class, 'evnt-content-text')]/h1")
    public static WebElement nameEvent; // Заголовок события

    @FindBy(xpath = "//div[contains(@class, 'date-wrapper')]/span[contains(@class, 'date')]")
    public static WebElement dateEvent2; // Дата события

    public CardEventPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }


}
