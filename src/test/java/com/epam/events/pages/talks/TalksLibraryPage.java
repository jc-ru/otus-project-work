package com.epam.events.pages.talks;

import com.epam.events.config.ServerConfig;
import com.epam.events.pages.events.EventsPage;
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

public class TalksLibraryPage {
    public static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    final private static Logger logger = LogManager.getLogger(TalksLibraryPage.class);

    WebDriver driver;
    WebDriverWait wait;

    public static final String PRELOAD = "//div[contains(@class, 'evnt-global-loader')]";

    @FindBy(xpath = PRELOAD)
    public static WebElement preLoader;  // прелоадер

    @FindBy(xpath = "//div[contains(@class, 'evnt-toogle-filters')]")
    public static WebElement moreFiltersBtn;  // Кнопка "More Filters"

    @FindBy(id = "filter_category")
    public static WebElement filterCategoryBtn;  // Кнопка "Category" в фильтрах

    @FindBy(id = "filter_location")
    public static WebElement locationBtn; // Кнопка Location в фильтрах

    @FindBy(id = "filter_language")
    public static WebElement langBtn; // Кнопка Language в фильтрах

    @FindBy(xpath = "//input[contains(@placeholder, 'Search by Talk Name')]")
    public static WebElement searchInput; // Поисковое поле

    @FindBy(xpath = "//div[contains(@class, 'evnt-card-wrapper')]/parent::a")
    public static List<WebElement> talksCards; // Карточки событий

    @FindBy(xpath = "//h1/span")
    public static List<WebElement> titlesTalksCards; // Заголовки карточек событий


    public TalksLibraryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // url Talks library Page
    public TalksLibraryPage open() {
        driver.get(cfg.getBaseUriProperties() + "/talks");
        PageFactory.initElements(driver, this);
        return this;
    }

    // Кнопка "More Filters"
    public TalksLibraryPage moreFiltersBtn() {
        moreFiltersBtn.click();
        PageFactory.initElements(driver, this);
        return this;
    }

    // Кнопка "Category" в фильтрах
    public TalksLibraryPage categoryFilterBtn() {
        filterCategoryBtn.click();
        PageFactory.initElements(driver, this);
        return this;
    }

    // Чек-бокс в фильтрах Category
    public TalksLibraryPage checkboxCategory(String category) {
        driver.findElement(By.xpath("//label[contains(text(), '" + category + "')]")).click();
        wait.until(ExpectedConditions.visibilityOf(preLoader));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PRELOAD)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'evnt-tag')]/label[contains(text(), '" + category + "')]")));
        PageFactory.initElements(driver, this);
        return this;
    }

    // Кнопка Location в фильтрах
    public TalksLibraryPage locationBtn() {
        locationBtn.click();
        PageFactory.initElements(driver, this);
        return this;
    }

    // Чек-бокс в фильтрах Location
    public TalksLibraryPage checkboxLocation(String location) {
        driver.findElement(By.xpath("//label[contains(text(), '" + location + "')]")).click();
        wait.until(ExpectedConditions.visibilityOf(preLoader));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PRELOAD)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'evnt-tag')]/label[contains(text(), '" + location + "')]")));
        PageFactory.initElements(driver, this);
        return this;
    }

    // Кнопка Language в фильтрах
    public TalksLibraryPage langBtn() {
        langBtn.click();
        PageFactory.initElements(driver, this);
        return this;
    }

    // Чек-бокс в фильтрах Location
    public TalksLibraryPage checkboxLang(String lang) {
        driver.findElement(By.xpath("//label[contains(text(), '" + lang + "')]")).click();
        wait.until(ExpectedConditions.visibilityOf(preLoader));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PRELOAD)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'evnt-tag')]/label[contains(text(), '" + lang + "')]")));
        PageFactory.initElements(driver, this);
        return this;
    }

    // Ввод в поисковое поле
    public TalksLibraryPage searchInput(String text) {
        searchInput.sendKeys(text);
        wait.until(ExpectedConditions.visibilityOf(preLoader));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PRELOAD)));
        PageFactory.initElements(driver, this);
        return this;
    }



}
