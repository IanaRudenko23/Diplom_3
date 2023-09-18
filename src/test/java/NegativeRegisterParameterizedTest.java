import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.praktikum.configuration.SetUpBrowser;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.anything;


@RunWith(Parameterized.class)
public class NegativeRegisterParameterizedTest {
    private final String browser;
    WebDriver driver;
    private final By incorrectPasswordWarning = By.xpath(".//div/p[text()='Некорректный пароль']");
    private static String name;
    private static String email;
    private static String password;

    public NegativeRegisterParameterizedTest(String name, String email, String password, String browser) {
        NegativeRegisterParameterizedTest.name = name;
        NegativeRegisterParameterizedTest.email = email;
        NegativeRegisterParameterizedTest.password = password;
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Неуспешная регистрация пользователя: {1}, браузер: {3}")
    public static Object[][] unsuccessfulClientRegister() {
        return new Object[][]{
                {"Joanna", "joanna_rodary@mail.ru", "0", "chrome"},
                {"Jonny", "jonny_hey_hey@mail.ru", "-1", "yandex"}
        };
    }

    @Before
    public void setup() {
        driver = SetUpBrowser.getWebDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(SetUpBrowser.getBaseUrl());
    }

    @Test
    public void checkUnsuccessfulClientRegister() {
        CreateBaseUser.failedCreateBaseUser(name, email, password, driver);
        String expectedResult = "Некорректный пароль";
        MatcherAssert.assertThat("Ошибка регистрации", driver.findElement(incorrectPasswordWarning), anything(expectedResult));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}



