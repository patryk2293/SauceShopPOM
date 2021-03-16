package com.sauceshop.tests;

import com.sauceshop.config.PropertiesFile;
import com.sauceshop.pages.LoginPage;
import com.sauceshop.pages.ProductPage;
import org.junit.*;

public class ProductTests extends BaseTest {

    @Before
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(PropertiesFile.getUser(), PropertiesFile.getPassword())
                .clickOnLoginButton();
    }

    @Test
    public void countOfProducts() {
        ProductPage productsPage = new ProductPage(driver);
        Assert.assertEquals("Counts of products are not equal 6", 6, productsPage.countOfItems());
    }

    @Test
    public void selectByDropAndReverseSort() {
        ProductPage productsPage = new ProductPage(driver);
        productsPage.chooseSortTypeFromDropDown("Name (Z to A)");
        Assert.assertEquals("Is not reversed", true, productsPage.isReverse());
    }

    @Test
    public void selectByDropAndNaturalSort() {
        ProductPage productsPage = new ProductPage(driver);
        productsPage.chooseSortTypeFromDropDown("Name (A to Z)");
        Assert.assertEquals("Is not sorted", true, productsPage.isSorted());
    }

    @Test
    public void addAllToCart() {
        ProductPage productsPage = new ProductPage(driver);
        productsPage.addAllToCart();
        Assert.assertEquals("Items added are not equal with status icon", "6", productsPage.statusOfCartIcon());
    }

}
