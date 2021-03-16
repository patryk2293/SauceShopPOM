package com.sauceshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private By firstNameLocator = By.id("first-name");
    private By lastNameLocator = By.id("last-name");
    private By errorMessageLocator = By.cssSelector("h3[data-test='error']");
    private By goToNextStepButtonLocator = By.cssSelector("input[class='btn_primary cart_button']");
    private By headerLocator = By.cssSelector("div[class='subheader']");


    public CheckoutPage goToCheckOut() {
        driver.navigate().to("https://www.saucedemo.com/checkout-step-one.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        return this;
    }

    public CheckoutPage typeName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameLocator)).sendKeys(name);
        return this;
    }

    public CheckoutPage typeLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameLocator)).sendKeys(lastName);
        return this;
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)).getText();
    }

    public CheckoutStepTwoPage goToStepTwo() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(goToNextStepButtonLocator)).click();
        return new CheckoutStepTwoPage(driver);
    }


}
