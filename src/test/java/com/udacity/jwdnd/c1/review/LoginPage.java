package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "submit-button")
    private WebElement loginInButton;

    public LoginPage(WebDriver driver) {
        WebElement marker = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(webDriver -> webDriver.findElement(By.id("submit-button")));
        PageFactory.initElements(driver, this);
    }

//    public void setUsernameInput(String usernameInput) {
//        this.usernameInput.sendKeys(usernameInput);
//    }
//
//    public void setPasswordInput(String passwordInput) {
//        this.passwordInput.sendKeys(passwordInput);
//    }
//
//    public void submitLoginButton() {
//        this.loginInButton.submit();
//    }

    public void login(String username, String password) {
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.loginInButton.submit();
    }
}
