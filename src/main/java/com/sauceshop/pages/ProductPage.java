package com.sauceshop.pages;

import com.google.common.collect.Comparators;
import com.sauceshop.helpers.HelperMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private By allProductsListLocator = By.xpath("//div[@class='inventory_item']");
    private By sortingDropDownListLocator = By.className("product_sort_container");
    private By listOfSortTypeItemsLocator = By.xpath("//select[@class='product_sort_container']//option");
    private By listOfProductsLocator = By.className("inventory_item_name");
    private By listOfPriceLocator = By.className("inventory_item_price");
    private By listOfButtonsAddToCartLocator = By.xpath("//button[@class='btn_primary btn_inventory']");
    private By cartLocator = By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']");
    private By priceOfChoosenProductLocator = By.xpath("//button[@class='btn_secondary btn_inventory']/ancestor::div[@class='pricebar']//div");

    public int countOfItems() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allProductsListLocator));
        return driver.findElements(allProductsListLocator).size();
    }

    public void chooseSortTypeFromDropDown(String sortType) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortingDropDownListLocator));
        driver.findElement(sortingDropDownListLocator).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listOfSortTypeItemsLocator));
        HelperMethods.selectFromDropDown(driver.findElements(listOfSortTypeItemsLocator), sortType);
    }

    public boolean isReverse() {
        List<String> productName;
        productName = HelperMethods.addToListString(driver.findElements(listOfProductsLocator));
        return Comparators.isInOrder(productName, Comparator.<String>reverseOrder());
    }

    public boolean isSorted() {
        List<String> productName;
        productName = HelperMethods.addToListString(driver.findElements(listOfProductsLocator));
        return Comparators.isInOrder(productName, Comparator.<String>naturalOrder());
    }

    public boolean priceToInt() {
        List<String> price;
        price = HelperMethods.addToListString(driver.findElements(listOfPriceLocator));
        List<Double> prices = HelperMethods.convertStringToDouble(price);
        return Comparators.isInOrder(prices, Comparator.<Double>naturalOrder());
    }

    public ProductPage addAllToCart() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listOfButtonsAddToCartLocator));
        for (int i = 0; i < driver.findElements(listOfButtonsAddToCartLocator).size() + 5; i++) {
            driver.findElements(listOfButtonsAddToCartLocator).get(0).click();
        }
        return this;
    }

    public String statusOfCartIcon() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartLocator));
        return driver.findElement(cartLocator).getText();
    }

    public CartPage clickOnCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartLocator)).click();
        return new CartPage(driver);
    }

    public ProductPage addRandomToCart(int numberOfItems) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listOfButtonsAddToCartLocator));
        Random random = new Random();
        for (int i = 0; i < numberOfItems; i++) {
            int rand = random.nextInt(driver.findElements(listOfButtonsAddToCartLocator).size());
            driver.findElements(listOfButtonsAddToCartLocator).get(rand).click();
        }
        return this;
    }

    public List<Double> getPriceFromChoosen() {
        List<String> price;
        price = HelperMethods.addToListString(driver.findElements(priceOfChoosenProductLocator));
        return HelperMethods.convertStringToDouble(price);
    }


}
