import org.openqa.selenium.WebDriver;
import org.praktikum.pages.RegistrationPage;

public class CreateBaseUser {

    public static void createBaseUser(String name, String email, String password, WebDriver driver) {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openFormOfRegister();
        registrationPage.fillingSuccessRegistration(name, email, password);
        registrationPage.waitForLoginPage();
    }

    public static void failedCreateBaseUser(String name, String email, String password, WebDriver driver) {//попытка создать пользователя, нет метода ожидания waitForLoginPage
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openFormOfRegister();
        registrationPage.fillingSuccessRegistration(name, email, password);
    }

}
