package com.epam.events.pages.auth;

import com.epam.events.config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    public static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    final private static Logger logger = LogManager.getLogger(LoginPage.class);

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "username")
    private WebElement inputLogin;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login")
    private WebElement submitLoginPage;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // path страницы логина
    public LoginPage urlLogin() {
        driver.get("https://sl.epam.com/core/login");

        PageFactory.initElements(driver, this);
        return this;
    }


    // Авторизация на страницы логина
    public LoginPage toAuth() {
        inputLogin.clear();
        inputLogin.sendKeys(cfg.getLoginProperties());
        inputPassword.clear();
        inputPassword.sendKeys(cfg.getPasswordProperties());
        submitLoginPage.click();
        PageFactory.initElements(driver, this);
        return this;
    }

}
