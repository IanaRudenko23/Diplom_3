package org.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {//описание локаторов и методов на странице Вход
    private final WebDriver driver;
    private final By enterButton = By.xpath(".//div/form/button[text()='Войти']");
    private final By passwordField = By.xpath(".//div/label[text()='Пароль']/../input");
    private final By emailField = By.xpath(".//div/label[text()='Email']/../input");
    private final By signUpButton = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By forgotPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");
    private final By createBurgerText = By.xpath(".//h1[text()='Соберите бургер']");
    private final By stellarBurgerLogo = By.className("AppHeader_header__logo__2D0X2");
    private final By constructorButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2'" +
            "and text()='Конструктор']");
    public LoginPage (WebDriver driver){
        this.driver = driver;
    }
    public void setEmail(String email){//ввели имя в поле Имя
        driver.findElement(emailField).sendKeys(email);
    }
    public void setPassword(String password){//ввели имя в поле Имя
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickSingUpButton(){
        driver.findElement(signUpButton).click();
    }

    public void fillingPasswordAndEmail(String password,String email){//метод заполнить поля для Входа в личный кабинет
        setEmail(email);
        setPassword(password);
        driver.findElement(enterButton).click();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(createBurgerText));
    }
    public void clickConstructorButton(){
        driver.findElement(constructorButton).click();
    }
    public void clickForgotPasswordButton(){//метод нажать на кнопку Восстановить пароль
        driver.findElement(forgotPasswordButton).click();
    }
    public void clickStellarBurgerLogo(){
        driver.findElement(stellarBurgerLogo).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(createBurgerText));
    }
    public String getForgotPassswordText(){
        return driver.findElement(forgotPasswordButton).getText();
    }
    public boolean isEnterButtonVisible() {
        return driver.findElement(enterButton).isDisplayed();
    }
}
