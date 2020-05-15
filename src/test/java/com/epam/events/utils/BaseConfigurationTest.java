package com.epam.events.utils;

import com.epam.events.config.ServerConfig;
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

    @BeforeSuite
    public void setupSuite() {
        driver = DriversManager.getDriver();
        wait = DriversManager.getDriverWait();

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
