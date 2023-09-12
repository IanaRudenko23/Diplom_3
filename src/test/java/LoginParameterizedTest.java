import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.praktikum.configuration.SetUpBrowser;
import org.praktikum.pages.ForgotPasswordPage;
import org.praktikum.pages.LoginPage;
import org.praktikum.pages.MainPage;
import org.praktikum.pages.RegistrationPage;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.anything;

@RunWith(Parameterized.class)
public class LoginParameterizedTest {
    WebDriver driver;
    private final String browser;
    private final String name;
    private final String password;
    private final String email;
    private final By createBurgerText = By.xpath(".//h1[text()='Соберите бургер']");

    public LoginParameterizedTest(String name,String email, String password, String browser) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Авторизация пользователя:{1},браузер: {3}")
    public static Object[][] clientLogin() {
        return new Object[][]{
                {"Bulochka","bulochka@open.ru", "qwerty12!", "chrome"},
                {"Rachel_Green", "rachel_green1990@open.ru","qwerty12!", "yandex"}
        };
    }

    @Before
    public void setup() {
        driver = SetUpBrowser.getWebDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(SetUpBrowser.getBaseUrl());
    }

    @Test
    @DisplayName("Логин пользователя с главной страницы")
    public void checkLogInFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.loginWithAccountButton();
        LoginBaseUser.logInBaseUser(password,email,driver);
        String expectedResult = "Оформить заказ";
        MatcherAssert.assertThat("Ошибка авторизации", driver.findElement(createBurgerText), anything(expectedResult));
    }

    @Test
    @DisplayName("Логин пользователя через личный кабинет")
    public void checkLogInFromPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButtonOnMainPage();
        LoginBaseUser.logInBaseUser(password,email,driver);
        String expectedResult = "Оформить заказ";
        MatcherAssert.assertThat("Ошибка авторизации", driver.findElement(createBurgerText), anything(expectedResult));
    }

    @Test
    @DisplayName("Логин пользователя через кнопку в форме регистрации")
    public void checkLogInFromRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButtonOnMainPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSingUpButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLogInLink();
        loginPage.fillingPasswordAndEmail(password, email);
        String expectedResult = "Оформить заказ";
        MatcherAssert.assertThat("Ошибка авторизации", driver.findElement(createBurgerText), anything(expectedResult));
    }

    @Test
    @DisplayName("Логин пользователя через кнопку восстановления пароля")
    public void checkLogInFromForgotPasswordLink() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButtonOnMainPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordButton();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLogInLink();
        loginPage.fillingPasswordAndEmail(password, email);
        String expectedResult = "Оформить заказ";
        MatcherAssert.assertThat("Ошибка авторизации", driver.findElement(createBurgerText), anything(expectedResult));
    }

    @After
    public void tearDown() {
        LoginBaseUser.logOutBaseUser(driver);
        driver.quit();
        }
}

