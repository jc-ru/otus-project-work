package com.epam.events.pages.talks;

import com.epam.events.config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CardTalkPage {
    public static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    final private static Logger logger = LogManager.getLogger(CardTalkPage.class);

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[contains(@class, 'location')]/span")
    public static WebElement locationTalk; // Местопроведение события

    @FindBy(xpath = "//div[contains(@class, 'language')]/span")
    public static WebElement languageTalk; // Язык события

    @FindBy(xpath = "//h1[contains(@class, 'evnt-talk-title')]")
    public static WebElement headerTitleTalk; // Заголовок в хэдере события


    public CardTalkPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
}
