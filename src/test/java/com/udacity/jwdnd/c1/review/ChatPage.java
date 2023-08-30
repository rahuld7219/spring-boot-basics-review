package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ChatPage {

    WebDriver webDriver;

    // we don't need to use annotation if field name here and id/name attribute's value is same,
    // by default PageFactory.initElements() method try to load using the specified field name by id/name.
    @FindBy(id = "messageText")
    private WebElement messageTextInput;

    @FindBy(id = "messageType")
    private WebElement messageTypeDropdown;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(className = "chatMessageUsername")
    private WebElement firstMessageUsername;

    @FindBy(className = "chatMessageText")
    private WebElement firstMessageText;

//    @FindBy(tagName = "span")
//    private List<WebElement> chatMessages;

    public ChatPage(WebDriver driver) {
        this.webDriver = driver;
//        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("messageText")));
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("messageType")));
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit-button")));
        PageFactory.initElements(this.webDriver, this); // do the lazy loading

        // instead of using above By methods we use below method code, using
        isLoaded();
    }

//    public void setMessageTextInput(String messageText) {
//        this.messageTextInput.sendKeys(messageText);
//    }

    public void sendChatMessage(String text, String messageType) {
        this.messageTextInput.sendKeys(text);
        Select drop = new Select(this.messageTypeDropdown);
        drop.selectByValue(messageType);
        this.submitButton.submit();
//        new WebDriverWait(this.webDriver, Duration.ofSeconds(5))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.className("chatMessageUsername")));
//        new WebDriverWait(this.webDriver, Duration.ofSeconds(5))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.className("chatMessageText")));

        // instead of above By methods, we use below code
        List<WebElement> marker = new WebDriverWait(this.webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElements(List.of(firstMessageUsername, firstMessageText)));
    }

//    public void setMessageTypeOption(String messageType) {
//        Select drop = new Select(this.messageTypeDropdown);
//        drop.selectByValue(messageType);
//    }

//    public String getChatUsername(int index) {
//        return this.chatMessages.get(index).getText();
//    }
//
//    public String getChatText(int index) {
//        return this.chatMessages.get(index).getText();
//    }

    public ChatMessage getFirstMessage() {
        ChatMessage message = new ChatMessage();
        message.setUsername(this.firstMessageUsername.getText());
        message.setMessageText(this.firstMessageText.getText());
        return message;
    }

    private void load() {
        // can write driver.get(url) here and call it in the constructor
    }

    private void isLoaded() throws Error {
        List<WebElement> marker = new WebDriverWait(this.webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElements(List.of(messageTextInput, messageTypeDropdown, submitButton)));
    }
}
