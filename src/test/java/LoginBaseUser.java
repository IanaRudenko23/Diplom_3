import org.openqa.selenium.WebDriver;
import org.praktikum.pages.LoginPage;
import org.praktikum.pages.MainPage;
import org.praktikum.pages.PersonalAccountPage;

public class LoginBaseUser {

    public static void logInBaseUser(String password, String email, WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillingPasswordAndEmail(password, email);
    }

    public static void logOutBaseUser(WebDriver driver) {//выход из аккаунта пользователя в Личном кабинете
        MainPage mainPage = new MainPage(driver);
        mainPage.enterPersonalAccount();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.logOut();
    }

}
