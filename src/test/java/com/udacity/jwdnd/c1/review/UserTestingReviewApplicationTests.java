package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTestingReviewApplicationTests {

    @LocalServerPort
    private Integer port;

    private String baseURL;

    private static WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
//        WebDriverManager.firefoxdriver().setup(); // not required from selenium 4.6
        driver = new FirefoxDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + this.port;
    }

    @Test
    public void testUserSignupLoginAndSubmitMessage() {

        driver.get(baseURL + "/signup");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(driver,"Rahul", "Dubey", "rahuld", "rahuldubey");
//        Thread.sleep(5000);
        driver.get(baseURL + "/login");
//        Thread.sleep(5000);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("rahuld", "rahuldubey");

        // Our Spring Security configuration is set up to automatically
        // forward us to the /chat URL on successful login,
        // we don't have to navigate with driver.get again.
        ChatPage chatPage = new ChatPage(driver);
//        Thread.sleep(5000);
        chatPage.sendChatMessage("Hello there!", "Shout");
//        Thread.sleep(5000);
        ChatMessage sentMessage = chatPage.getFirstMessage();

        assertEquals("rahuld", sentMessage.getUsername());
        assertEquals("HELLO THERE!", sentMessage.getMessageText());

//        driver.get("http://localhost:" + port + "/signup");
//        SignUpPage signUp = new SignUpPage(driver);
//        signUp.setFirstNameInput("rahul");
//        signUp.setLastNameInput("dubey");
//        signUp.setUsernameInput("rahuld");
//        signUp.setPasswordInput("rahuldubey");
//        Thread.sleep(10000);
//        signUp.submitSignup();
//        driver.get("http://localhost:" + port + "/login");
//        LoginPage login = new LoginPage(driver);
//        login.setUsernameInput("rahuld");
//        login.setPasswordInput("rahuldubey");
//        Thread.sleep(5000);
//        login.submitLoginButton();
//        driver.get("http://localhost:" + port + "/chat");
//        ChatPage chat = new ChatPage(driver);
//        chat.setMessageTextInput("Hi");
//        chat.setMessageTypeOption("Shout");
//        Thread.sleep(5000);
//        chat.submitMessage();
////        ChatPage chatS = new ChatPage(driver);
//        String actualUsername = chat.getChatUsername(0);
//        String actualChatText = chat.getChatText(1);
//        assertEquals("rahuld", actualUsername);
//        assertEquals("SHOUT", actualChatText);

    }
}
