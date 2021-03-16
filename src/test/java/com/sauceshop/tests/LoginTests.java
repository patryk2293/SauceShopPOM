package com.sauceshop.tests;

import com.sauceshop.config.PropertiesFile;
import com.sauceshop.pages.LoginPage;
import org.junit.Assert;
import org.junit.Test;

public class LoginTests extends BaseTest {

    private String failUser = "Test1";
    private String failPassword = "Test2";

    @Test
    public void loginSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(PropertiesFile.getUser(), PropertiesFile.getPassword())
                .clickOnLoginButton();
        Assert.assertEquals("Login failed. Please check credentials", "https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @Test
    public void loginFailed() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(failUser, failPassword)
                .clickOnLoginButton();
        Assert.assertEquals("Text does not match", "Epic sadface: Username and password do not match any user in this service", loginPage.errorMessage());
    }

    @Test
    public void loginFailedByEmptyUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials("", PropertiesFile.getPassword())
                .clickOnLoginButton();
        Assert.assertEquals("Error message is not the same", "Epic sadface: Username is required", loginPage.errorMessage());
    }

    @Test
    public void loginFailedByEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(PropertiesFile.getUser(), "")
                .clickOnLoginButton();
        Assert.assertEquals("Error message is not the same", "Epic sadface: Password is required", loginPage.errorMessage());
    }

}
