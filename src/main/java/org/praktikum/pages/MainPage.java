package org.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {//описание локаторов и методов на Главной странице
    private final WebDriver driver;
    private final By bunIngredient = By.xpath(".//span[text()='Булки']//parent::div");
    private final By souceIngredient = By.xpath(".//span[text()='Соусы']//parent::div");
    private final By fillingsIngredient = By.xpath(".//span[text()='Начинки']//parent::div");
    private final By createBurgerText = By.xpath(".//h1[text()='Соберите бургер']");
    private final By accountButton = By.xpath(".//div/button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickPersonalAccountButtonOnMainPage(){//метод кликнуть на кнопку Личный кабинет на главной странице
        driver.findElement(accountButton).click();
    }
    public void enterPersonalAccount(){//метод для входа в личный кабинет для залогиненого пользователя
        driver.findElement(personalAccountButton).click();
    }
    public boolean IsSouseTabSelected(){
        return driver.findElement(souceIngredient).isDisplayed();
    }
    public boolean IsBunTabSelected(){
        return driver.findElement(bunIngredient).isDisplayed();
    }
    public boolean FillingsAreSelected(){
        return driver.findElement(fillingsIngredient).isDisplayed();
    }
    public void changeIngridientToSouse(){//метод конструктора Смена на соус
        driver.findElement(souceIngredient).click();
    }
    public void changeIngridientToBuns(){//метод конструктора Смена на булки
        driver.findElement(bunIngredient).click();
    }
    public void changeIngredientToFillings(){//метод конструктора Смена на начинки
        driver.findElement(fillingsIngredient).click();
    }
    public void loginWithAccountButton(){//метод Вход в аккаунт с главной страницы
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(accountButton));
        driver.findElement(accountButton).click();
    }
    public String getCreateBurgerText(){
        return driver.findElement(createBurgerText).getText();
    }
}
