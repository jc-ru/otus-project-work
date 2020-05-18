package com.epam.events.pages.main;

import com.epam.events.config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    public static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    final private static Logger logger = LogManager.getLogger(MainPage.class);

    WebDriver driver;
    WebDriverWait wait;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // url галвной страницы
    public MainPage open() {
        driver.get(cfg.getBaseUriProperties());
        PageFactory.initElements(driver, this);
        return this;
    }
    
}
