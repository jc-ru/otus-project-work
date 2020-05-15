package com.epam.events.pages;

import com.epam.events.config.ServerConfig;
import com.epam.events.utils.DriversManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    public static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    final public static Logger logger = LogManager.getLogger(DriversManager.class);

    WebDriver driver;
    WebDriverWait wait;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
    
}
