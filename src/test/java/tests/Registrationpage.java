package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Registrationpage {
    private WebDriver driver;

    private By maleRadio = By.id("gender-male");
    private By femaleRadio = By.id("gender-female");
    private By firstnameField = By.id("FirstName");
    private By lastnameField = By.id("LastName");
    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By confirmpasswordField = By.id("ConfirmPassword");
    private By registerButton = By.id("register-button");
    private By message = By.className("result");




    public Registrationpage(WebDriver driver) {
        this.driver = driver;
    }
    public void selectMale() {
        driver.findElement(maleRadio).click();
    }

    public void selectFemale() {
        driver.findElement(femaleRadio).click();
    }

    public void enterfirstname(String firstName) {
        driver.findElement(firstnameField).sendKeys(firstName);
    }

    public void enterlastName(String lastName) {
        driver.findElement(lastnameField).sendKeys(lastName);
    }


    public void enteremail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterpassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterconfirmPassword(String confirmPassword) {
        driver.findElement(confirmpasswordField).sendKeys(confirmPassword);
    }


    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public String getMessageText() {
        return driver.findElement(message).getText();
    }


}
