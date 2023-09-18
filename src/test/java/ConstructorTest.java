import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.praktikum.configuration.SetUpBrowser;
import org.praktikum.pages.LoginPage;
import org.praktikum.pages.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {
    WebDriver driver;
    private final String browser = "chrome";

    @Before
    public void setup() {
        driver = SetUpBrowser.getWebDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(SetUpBrowser.getBaseUrl());
    }

    @Test
    @DisplayName("Переход от раздела Булки к разделу Соусы")
    public void changeBunToSouse() {
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.isBunTabSelected());
        mainPage.changeIngridientToSouse();
        assertTrue(mainPage.isSouseTabSelected());
    }

    @Test
    @DisplayName("Переход от раздела Соусы к разделу Начинки")
    public void changeSouseToFillings() {
        MainPage mainPage = new MainPage(driver);
        mainPage.changeIngridientToSouse();
        assertTrue(mainPage.isSouseTabSelected());
        mainPage.changeIngredientToFillings();
        assertTrue(mainPage.isFillingTabSelected());
    }

    @Test
    @DisplayName("Переход от раздела Начинки к разделу Булки")
    public void changeFillingsToBun() {
        MainPage mainPage = new MainPage(driver);
        mainPage.changeIngredientToFillings();
        assertTrue(mainPage.isFillingTabSelected());
        mainPage.changeIngridientToBuns();
        assertTrue(mainPage.isBunTabSelected());
    }

    @Test
    @DisplayName("Переход от раздела Соусы к разделу Булки")
    public void changeSouseToBun() {
        MainPage mainPage = new MainPage(driver);
        mainPage.changeIngridientToSouse();
        assertTrue(mainPage.isSouseTabSelected());
        mainPage.changeIngridientToBuns();
        assertTrue(mainPage.isBunTabSelected());
    }

    @Test
    @DisplayName("Переход от раздела Булки к разделу Начинки")
    public void changeBunToFillings() {
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.isBunTabSelected());
        mainPage.changeIngredientToFillings();
        assertTrue(mainPage.isFillingTabSelected());
    }

    @Test
    @DisplayName("Переход из Личного кабинета в Конструктор нажатием Лого")
    public void moveToContstructorWithLogo() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButtonOnMainPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickStellarBurgerLogo();
        assertTrue(mainPage.isBunTabSelected());
    }

    @Test
    @DisplayName("Переход из Личного кабинета в Конструктор нажатием кнопки Конструктор")
    public void moveToConstructorWithConstructorButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButtonOnMainPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickConstructorButton();
        assertTrue(mainPage.isBunTabSelected());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
