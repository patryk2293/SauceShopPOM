package com.sauceshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By loginFieldLocator = By.id("user-name");
    private By passwordFieldLocator = By.id("password");
    private By loginButtonLocator = By.id("login-button");
    private By errorMessageLocator = By.xpath("//h3[@data-test='error']");


    public LoginPage enterCredentials(String user, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginFieldLocator));
        driver.findElement(loginFieldLocator).sendKeys(user);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFieldLocator));
        driver.findElement(passwordFieldLocator).sendKeys(password);
        return this;
    }

    public void clickOnLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        driver.findElement(loginButtonLocator).click();
    }

    public String errorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return driver.findElement(errorMessageLocator).getText();
    }
}
