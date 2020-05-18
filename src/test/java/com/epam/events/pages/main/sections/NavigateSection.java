package com.epam.events.pages.main.sections;

import com.epam.events.config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigateSection {
    public static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    final private static Logger logger = LogManager.getLogger(NavigateSection.class);

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@href='/events']")
    private WebElement eventsBtn;

    @FindBy(xpath = "//a[@href='/talks']")
    private WebElement talksLibraryBtn;

    public NavigateSection(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public NavigateSection clickEventsBtn() {
        eventsBtn.click();

        PageFactory.initElements(driver, this);
        return this;
    }

    public NavigateSection clickTalksLibraryBtn() {
        talksLibraryBtn.click();

        PageFactory.initElements(driver, this);
        return this;
    }

}
