import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.praktikum.baseUserConfig.BaseUserSteps;
import org.praktikum.configuration.SetUpBrowser;
import org.praktikum.pages.MainPage;
import org.praktikum.pojo.UserRequest;

import java.util.concurrent.TimeUnit;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.praktikum.baseUserConfig.UserRequestGenerator.getUserRequest;


@RunWith(Parameterized.class)
public class RegisterParameterizedTest {
    BaseUserSteps userRequest;
    String accessToken;
    private final String browser;
    WebDriver driver;
    private static String name;
    private static String email;
    private static String password;

    public RegisterParameterizedTest(String name, String email, String password, String browser) {
        RegisterParameterizedTest.name = name;
        RegisterParameterizedTest.email = email;
        RegisterParameterizedTest.password = password;
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Регистрация пользователя: {1}, браузер: {3}")
    public static Object[][] clientRegister() {
        return new Object[][]{
                {"Ralf", "Ralf_Loren@mail.ru", "ralfLoren", "chrome"},//позитив кейс в гугл хром
                {"Bella07", "bella_swann07@mail.ru", "swann123", "yandex"}//позитив кейс в яндекс браузере
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
    public void checkRegisterClient() {
        UserRequest userRequestForDelete;

        CreateBaseUser.createBaseUser(name, email, password, driver);//создали пользователя
        LoginBaseUser.logInBaseUser(password, email, driver);//залогинили пользователя


        userRequestForDelete = getUserRequest(name, email, password);
        ValidatableResponse loginResponse = userRequest.loginUser(userRequestForDelete);
        loginResponse.assertThat().statusCode(SC_OK).and().body("success", equalTo(true));
        accessToken = loginResponse.extract().path("accessToken");

        MainPage mainPage = new MainPage(driver);
        String expectedResult = mainPage.getCreateBurgerText();
        MatcherAssert.assertThat("Регистрация не успешна", expectedResult, notNullValue());
    }

    @After
    public void tearDown() {
        driver.quit();
        userRequest.deleteUser(accessToken).assertThat().statusCode(SC_ACCEPTED).and().
                body("success", equalTo(true));
    }
}


