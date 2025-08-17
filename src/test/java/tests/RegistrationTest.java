package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

@Listeners(ExtentReportListener.class)
public class RegistrationTest extends BaseTest {

    @Test(dataProvider = "registrationData", dataProviderClass = TestDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class, groups = {"registration"})
    public void RegistrationTest(String gender, String firstName, String lastName,
                                 String email, String password, String confirmPassword,
                                 String expectedResult) {

        getDriver().get("https://demowebshop.tricentis.com/register");

        Registrationpage regPage = new Registrationpage(getDriver());


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

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        SoftAssert softAssert = new SoftAssert();

        WebElement messageElement;
        String messageText;

        if (expectedResult.equalsIgnoreCase("successful")) {

            messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("result")));
            messageText = messageElement.getText();
            System.out.println("Success message: " + messageText);
            softAssert.assertTrue(messageText.contains("Your registration completed"), "Success message not found");

        } else {

            messageElement = wait.until(driver -> {
                try {
                    WebElement fieldError = driver.findElement(By.className("field-validation-error"));
                    if (fieldError.isDisplayed()) return fieldError;
                } catch (Exception ignored) {}

                try {
                    WebElement summaryError = driver.findElement(By.className("validation-summary-errors"));
                    if (summaryError.isDisplayed()) return summaryError;
                } catch (Exception ignored) {}

                return null;
            });

            messageText = messageElement.getText();
            System.out.println("Error message: " + messageText);

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
