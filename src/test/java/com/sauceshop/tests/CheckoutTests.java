package com.sauceshop.tests;

import com.sauceshop.config.PropertiesFile;
import com.sauceshop.pages.CheckoutPage;
import com.sauceshop.pages.LoginPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckoutTests extends BaseTest {

    private String name = "Patryk";
    private String lastName = "eloe2";

    @Before
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(PropertiesFile.getUser(), PropertiesFile.getPassword())
                .clickOnLoginButton();
    }

    @Test
    public void emptyName() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage
                .goToCheckOut()
                .typeName("")
                .goToStepTwo();
        String actualError = checkoutPage.getErrorMessage();
        Assert.assertEquals("Message does not appera", "Error: First Name is required", actualError);
    }

    @Test
    public void emptyLastName() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage
                .goToCheckOut()
                .typeName(name)
                .goToStepTwo();
        String actualError = checkoutPage.getErrorMessage();
        Assert.assertEquals("Message does not appera", "Error: Last Name is required", actualError);
    }

    @Test
    public void emptyPostalCode() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage
                .goToCheckOut()
                .typeName(name)
                .typeLastName(lastName)
                .goToStepTwo();
        String actualError = checkoutPage.getErrorMessage();
        Assert.assertEquals("Message does not appera", "Error: Postal Code is required", actualError);
    }
}
