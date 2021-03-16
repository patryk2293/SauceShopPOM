package com.sauceshop.tests;

import com.sauceshop.config.PropertiesFile;
import com.sauceshop.pages.CartPage;
import com.sauceshop.pages.LoginPage;
import com.sauceshop.pages.ProductPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class CartTests extends BaseTest {

    @Before
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(PropertiesFile.getUser(), PropertiesFile.getPassword())
                .clickOnLoginButton();
    }

    @Test
    public void checkCountOfItems() {
        ProductPage productPage = new ProductPage(driver);
        int actualCount = productPage
                .addAllToCart()
                .clickOnCart()
                .getCountOfItems();
        Assert.assertEquals("Counts of added products are not the same", 6, actualCount);
    }

    @Test
    public void checkRemoveButton() {
        ProductPage productPage = new ProductPage(driver);
        int actualCount = productPage
                .addAllToCart()
                .clickOnCart()
                .clickOnRemove()
                .getCountOfItems();
        Assert.assertEquals("Product did not remove", 5, actualCount);
    }

    @Test
    public void checkingCart() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        productPage.addRandomToCart(3);
        List<Double> choosenPrices = productPage.getPriceFromChoosen();
        productPage.clickOnCart();
        List<Double> pricesInCart = cartPage.getPriceFromCart();
        Collections.sort(choosenPrices);
        Collections.sort(pricesInCart);
        Assert.assertEquals("Lists of prices are not the same", choosenPrices, pricesInCart);

    }
}
