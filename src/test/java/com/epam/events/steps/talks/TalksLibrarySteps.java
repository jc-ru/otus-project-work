package com.epam.events.steps.talks;

import com.epam.events.helpers.WorkWithDate;
import com.epam.events.pages.main.MainPage;
import com.epam.events.pages.main.sections.NavigateSection;
import com.epam.events.pages.talks.TalksLibraryPage;
import com.epam.events.steps.events.EventsSteps;
import com.epam.events.utils.DriversManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.events.pages.talks.CardTalkPage.locationTalk;
import static com.epam.events.pages.talks.TalksLibraryPage.*;

public class TalksLibrarySteps {
    final private static Logger logger = LogManager.getLogger(EventsSteps.class);
    private static WebDriver driver = DriversManager.getDriver();
    private static WebDriverWait wait = DriversManager.getDriverWait();
    private static TalksLibraryPage talksLibraryPage;
    private static MainPage mainPage;
    private static NavigateSection navigateSection;
    public static ArrayList<String> cardsTalksLinks = new ArrayList<>();

    // переменные для фильтров
    public static String categoryFilter;
    public static String locationFilter;
    public static String langFilter;
    public static String keyword;


    //Переход в раздел TalksLibrary из под главной страницы
    public static void openTalksLibraryPage() {
        mainPage = new MainPage(driver, wait);
        mainPage.open();
        navigateSection = new NavigateSection(driver, wait);
        navigateSection.clickTalksLibraryBtn();
    }

    // Добавить фильтры
    public static void addFilters(String category, String location, String lang) {
        categoryFilter = category;
        locationFilter = location;
        langFilter = lang;

        talksLibraryPage = new TalksLibraryPage(driver, wait);
        talksLibraryPage
                .moreFiltersBtn()
                .categoryFilterBtn()
                .checkboxCategory(category)
                .locationBtn()
                .checkboxLocation(location)
                .langBtn()
                .checkboxLang(lang);
    }

    // Получить все ссылки на страницы событий
    public static void getAllUrlsCardsTalks() {
        List<WebElement> cardsTalks = talksCards;
        for (int counter = 0; counter < cardsTalks.size(); counter++) {
            String cardTalkLink = cardsTalks.get(counter).getAttribute("href");
            logger.debug("Получена ссылка на событие {} из {}", counter + 1, cardsTalks.size());
            logger.debug("url: {}", cardTalkLink);
            cardsTalksLinks.add(cardTalkLink);
        }
    }

    // Открыть каждое событие и проверить данные
    public static void assertOpenAllCardsTalksAndChecksData() {
        for (int counter = 0; counter < cardsTalksLinks.size(); counter++) {
            driver.get(cardsTalksLinks.get(counter));

            try {
                WebElement loc = driver.findElement(By.xpath("//div[contains(@class, 'location')]/span[contains(text(), '" + locationFilter + "')]"));
                logger.debug("Местопроведение: {}", loc.getText());
                logger.debug("Элемент местопроведения события содержит в себе текст: {}", locationFilter);
            } catch (NoSuchElementException ex) {
                logger.error("Элемент местопроведения события содержит в себе текст: {}", locationFilter);
                Assert.fail(ex.getMessage());
            }

            try {
                WebElement lang = driver.findElement(By.xpath("//div[contains(@class, 'language')]/span[contains(text(), '" + langFilter + "')]"));
                logger.debug("Язык: {}", lang.getText());
                logger.debug("Элемент язык события содержит в себе текст: {}", langFilter);
            } catch (NoSuchElementException ex) {
                logger.error("Элемент язык события содержит в себе текст: {}", langFilter);
                Assert.fail(ex.getMessage());
            }

            try {
                WebElement cat = driver.findElement(By.xpath("//label[contains(text(), '" + categoryFilter + "')]"));
                logger.debug("Категория: {}", cat.getText());
                logger.debug("Присутствует хэш-тег с текстом: {}", categoryFilter);
            } catch (NoSuchElementException ex) {
                logger.error("Категория (хэш-тег) с текстом: {} - отсутствует", categoryFilter);
                Assert.fail(ex.getMessage());
            }
            logger.debug("Проверено событий {} из {}", counter + 1, cardsTalksLinks.size());
        }
    }

    // Ввод ключевого слова в поисковую строку
    public static void sendSearch(String text) {
        keyword = text;
        talksLibraryPage = new TalksLibraryPage(driver, wait);
        talksLibraryPage.searchInput(text);
    }

    // Открыть каждое событие и проверить ключевое слово в хэш-тегах
    public static void assertKeywordInTalks() {

        for (int counter = 0; counter < cardsTalksLinks.size(); counter++) {
            driver.get(cardsTalksLinks.get(counter));

            try {
                WebElement cat = driver.findElement(By.xpath("//label[contains(text(), '" + keyword + "')]"));
                logger.debug("Категория: {}", cat.getText());
                logger.debug("Присутствует хэш-тег с текстом: {}", keyword);
            } catch (NoSuchElementException ex) {
                logger.debug("Категория (хэш-тег) с текстом: {} - отсутствует", keyword);

                // Проверка ключевого слова в загаловке
                try {
                    WebElement title = driver.findElement(By.xpath("//h1[contains(text(), '" + keyword + "')]"));
                    logger.debug("Присутствует ключевое слово в загаловке события с текстом: {}", keyword);
                } catch (NoSuchElementException e) {
                    logger.debug("Категория (ключевое слово) с текстом: {} - отсутствует в загаловке события", keyword);
                    Assert.fail(e.getMessage());
                }

            }
            logger.debug("Проверено событий {} из {}", counter + 1, cardsTalksLinks.size());
        }
    }




}
