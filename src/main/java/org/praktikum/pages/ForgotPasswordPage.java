package org.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {//страница для Восстановления пароля
    private final WebDriver driver;
    private final By logInLink = By.xpath(".//a[@class='Auth_link__1fOlj']");
    public ForgotPasswordPage (WebDriver driver){
        this.driver = driver;
    }
    public void clickLogInLink(){
        driver.findElement(logInLink).click();
    }
}
