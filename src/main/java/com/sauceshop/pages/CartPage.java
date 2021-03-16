package com.sauceshop.pages;

import com.sauceshop.helpers.HelperMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By quantityLocator = By.className("cart_quantity");
    private By listOfRemoveButtonsLocator = By.cssSelector("button[class='btn_secondary cart_button']");
    private By listPricesLocators = By.className("inventory_item_price");

    public int getCountOfItems() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quantityLocator)).size();
    }

    public CartPage clickOnRemove() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listOfRemoveButtonsLocator));
        driver.findElements(listOfRemoveButtonsLocator).get(0).click();
        return this;
    }

    public List<Double> getPriceFromCart() {
        List<String> price;
        price = HelperMethods.addToListString(driver.findElements(listPricesLocators));
        return HelperMethods.convertStringToDouble(price);
    }

}
