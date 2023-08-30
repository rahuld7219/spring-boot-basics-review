package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameInput;

//    @FindBy(css = "#inputFirstName")
//    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameInput;

    @FindBy(id = "inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "submit-button")
    private WebElement signUpButton;

    public SignupPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("submit-button")));
        PageFactory.initElements(driver, this);
    }

//    public void setFirstNameInput(String firstname) {
//        this.firstNameInput.sendKeys(firstname);
//    }
//
//    public void setLastNameInput(String lastname) {
//        this.firstNameInput.sendKeys(lastname);
//    }
//
//    public void setUsernameInput(String username) {
//        this.firstNameInput.sendKeys(username);
//    }
//
//    public void setPasswordInput(String password) {
//        this.firstNameInput.sendKeys(password);
//    }

//    public void submitSignup() {
//        this.signUpButton.submit();
//    }

    public void signup(WebDriver driver, String firstName, String lastName, String username, String password) {
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.signUpButton.submit();
        WebElement marker = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));
    }

}
