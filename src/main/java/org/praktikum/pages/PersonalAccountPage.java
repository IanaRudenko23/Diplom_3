package org.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {//описание локаторов и методов в Личном кабинете
    private final WebDriver driver;
    private final By constructorButton = By.xpath(".//div[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Конструктор']");
    private final By createBurgerText = By.xpath(".//h1[text()='Соберите бургер']");
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");
    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    public PersonalAccountPage(WebDriver driver){
        this.driver = driver;
    }
    public void logOut (){
        driver.findElement(logoutButton).click();
    }

}
