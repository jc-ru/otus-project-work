package com.epam.events.utils;

import com.epam.events.config.ServerConfig;
import com.epam.events.pages.events.CardEventPage;
import com.epam.events.pages.events.EventsPage;
import com.epam.events.pages.main.MainPage;
import com.epam.events.pages.main.sections.NavigateSection;
import com.epam.events.pages.talks.CardTalkPage;
import com.epam.events.pages.talks.TalksLibraryPage;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseConfigurationTest {

    final private static Logger logger = LogManager.getLogger(BaseConfigurationTest.class);
    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static MainPage mainPage;
    public static NavigateSection navigateSection;
    public static EventsPage eventsPage;
    public static CardEventPage cardEventPage;
    public static TalksLibraryPage talksLibraryPage;
    public static CardTalkPage cardTalkPage;

    @BeforeSuite
    public void setupSuite() {
        driver = DriversManager.getDriver();
        wait = DriversManager.getDriverWait();

        mainPage = new MainPage(driver, wait);
        navigateSection = new NavigateSection(driver, wait);
        eventsPage = new EventsPage(driver, wait);
        cardEventPage = new CardEventPage(driver, wait);
        talksLibraryPage = new TalksLibraryPage(driver, wait);
        cardTalkPage = new CardTalkPage(driver, wait);

    }

    @BeforeClass
    public void initSpec() {
    }

    @BeforeMethod
    public void setupTest() {
    }

    @AfterMethod
    public void TestFailure(ITestResult result) {
        if (! result.isSuccess()) {
            logger.error("FAILED TEST");
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        DriversManager.stopAllDrivers();
    }

}
