import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.praktikum.baseUserConfig.BaseUserSteps;
import org.praktikum.configuration.SetUpBrowser;
import org.praktikum.pages.MainPage;
import org.praktikum.pojo.UserRequest;

import java.util.concurrent.TimeUnit;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.praktikum.baseUserConfig.UserRequestGenerator.getUserRequest;

@RunWith(Parameterized.class)
public class PersonalAccountParameterizedTest {
    BaseUserSteps userRequest;
    String accessToken;
    WebDriver driver;
    private final String browser;
    private final String password;
    private final String email;
    private final String name;
    private final By profileButton = By.xpath(".//a[text()='Профиль']");

    public PersonalAccountParameterizedTest(String name, String email, String password, String browser) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Проверка логина и логаута из личного кабинета пользователя: {1}, браузер: {3}")
    public static Object[][] checkPersonalAccount() {
        return new Object[][]{
                {"Sugar-2", "yes_please-02@mail.ru", "123456S", "chrome"},
                {"Solt-2", "cry_cry_baby-02@mail.ru", "123456S", "yandex"}
        };
    }

    @Before
    public void setup() {
        driver = SetUpBrowser.getWebDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(SetUpBrowser.getBaseUrl());
        accessToken = null;
        userRequest = new BaseUserSteps();
    }

    @Test
    @DisplayName("Переход в Личный кабинет по кнопке Личный кабинет с главной страницы")
    public void checkEnterPersonalAccountFromMainPage() {
        UserRequest userRequestForDelete;

        CreateBaseUser.createBaseUser(name, email, password, driver);//создали пользователя
        LoginBaseUser.logInBaseUser(password, email, driver);//залогинили пользователя

        userRequestForDelete = getUserRequest(name, email, password);
        ValidatableResponse loginResponse = userRequest.loginUser(userRequestForDelete);
        loginResponse.assertThat().statusCode(SC_OK).and().body("success", equalTo(true));
        accessToken = loginResponse.extract().path("accessToken");//получили токен пользователя

        MainPage mainPage = new MainPage(driver);
        mainPage.enterPersonalAccount();
        String expectedResult = "В этом разделе вы можете изменить свои персональные данные";
        MatcherAssert.assertThat("Ошибка регистрации", driver.findElement(profileButton), anything(expectedResult));
    }

    @Test
    @DisplayName("Выход из Личного кабинета")
    public void checkLogOutFromPersonalAccount() {
        UserRequest userRequestForDelete;

        CreateBaseUser.createBaseUser(name, email, password, driver);//создали пользователя
        LoginBaseUser.logInBaseUser(password, email, driver); //залогинили пользователя

        userRequestForDelete = getUserRequest(name, email, password);
        ValidatableResponse loginResponse = userRequest.loginUser(userRequestForDelete);
        loginResponse.assertThat().statusCode(SC_OK).and().body("success", equalTo(true));
        accessToken = loginResponse.extract().path("accessToken");//получили токен пользователя

        MainPage mainPage = new MainPage(driver);
        mainPage.enterPersonalAccount();
        LoginBaseUser.logOutBaseUser(driver);
        String expectedResult = "Вы — новый пользователь?";
        MatcherAssert.assertThat("Ошибка выхода из Личного кабинета", expectedResult, anything(expectedResult));

    }

    @After
    public void tearDown() {
        driver.quit();
        userRequest.deleteUser(accessToken).assertThat().statusCode(SC_ACCEPTED).and().
                body("success", equalTo(true));
    }

}
