package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Loginpage {
    private WebDriver driver;


    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By loginButton = By.cssSelector(".button-1.login-button");
    private By account = By.className("account");




    public Loginpage (WebDriver driver) {
        this.driver = driver;
    }



    public void enteremail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterpassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }


    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getMessageText() {
        return driver.findElement(account).getText();
    }


}