package org.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {//описание локаторов и методов на странице Регистрации
    private final WebDriver driver;
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By registerButton = By.className("Auth_link__1fOlj");
    private final By formOfRegister = By.xpath(".//div[@class='Auth_login__3hAey']");
    private final By nameInput = By.xpath(".//div/label[text()='Имя']/../input");
    private final By emailInput = By.xpath(".//div/label[text()='Email']/../input");
    private final By passwordInput = By.xpath(".//div/label[text()='Пароль']/../input");
    private final By bigRegisterButton = By.xpath(".//button[text()='Зарегистрироваться']");
    String name;
    String email;
    String password;
    private final By loginPageLocator = By.xpath(".//div/form/button[text()='Войти']");
    private final By logInLink = By.xpath(".//a[@class='Auth_link__1fOlj']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openFormOfRegister() {//метод открытия страницы заполнения полей для регистрации
        driver.findElement(personalAccountButton).click();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(registerButton).click();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(formOfRegister));
    }

    public void fillingSuccessRegistration(String name, String email, String password) {//метод успешной регистрации, заполнение всех полей на странице регистрации
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(bigRegisterButton));
        driver.findElement(bigRegisterButton).click();
    }

    public void clickLogInLink() {//метод Вход со страницы регистрации
        driver.findElement(logInLink).click();
    }

    public void waitForLoginPage() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(loginPageLocator));
    }

}
