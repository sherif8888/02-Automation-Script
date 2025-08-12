package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import tests.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class RegistrationTest extends BaseTest {

    @Test(dataProvider = "registrationData", dataProviderClass = TestDataProvider.class, retryAnalyzer = RetryAnalyzer.class, groups = {"registration"})
    public void RegistrationTest( String gender, String firstName, String lastName, String email, String password,String confirmPassword, String expectedResult) {


        driver.get("https://demowebshop.tricentis.com/register");

        Registrationpage regPage = new Registrationpage(driver);

        if (gender.equalsIgnoreCase("male")) {
            regPage.selectMale();
        } else {
            regPage.selectFemale();
        }

        regPage.enterfirstname(firstName);
        regPage.enterlastName(lastName);
        regPage.enteremail(email);
        regPage.enterpassword(password);
        regPage.enterconfirmPassword(confirmPassword);
        regPage.clickRegister();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        SoftAssert softAssert = new SoftAssert();


        WebElement messageElement;
        String messageText;

        if (expectedResult.equalsIgnoreCase("successful")) {
            messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("result")));
            messageText = messageElement.getText();
            softAssert.assertTrue(messageText.contains("Your registration completed"), "Success message not found");
        } else {
            messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("field-validation-error")));
            messageText = messageElement.getText();
            boolean errorFound = messageText.toLowerCase().contains("already exists") ||
                    messageText.toLowerCase().contains("required") ||
                    messageText.toLowerCase().contains("does not match") ||
                    messageText.toLowerCase().contains("email") ||
                    messageText.toLowerCase().contains("invalid") ||
                    messageText.toLowerCase().contains("password");

            if (!errorFound) {
                softAssert.fail("Expected error message not found: " + messageText);
            }
        }


        softAssert.assertAll();
    }

}


