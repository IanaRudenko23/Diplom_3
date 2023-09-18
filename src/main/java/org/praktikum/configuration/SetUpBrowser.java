package org.praktikum.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SetUpBrowser {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static WebDriver getWebDriver(String browserName) {
        ChromeOptions chromeOptions = new ChromeOptions();
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                return new ChromeDriver(chromeOptions);
            case "yandex":
                return createYandexBrowser();
        }
        return new ChromeDriver(chromeOptions);
    }

    public static WebDriver createYandexBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandex-chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Users\\Yana Rudenko\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        return new ChromeDriver(chromeOptions);

    }

}

