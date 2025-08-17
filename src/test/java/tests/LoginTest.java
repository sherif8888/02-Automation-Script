package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Listeners(ExtentReportListener.class)
public class LoginTest extends BaseTest {


    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class, groups = {"login"})
    public void LoginTest(String email, String password, String expectedResult) {

        getDriver().get("https://demowebshop.tricentis.com/login");

        Loginpage loginPage = new Loginpage(getDriver());

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));

        loginPage.enteremail(email);
        loginPage.enterpassword(password);
        loginPage.clickLogin();

        SoftAssert softAssert = new SoftAssert();

        if (expectedResult.equalsIgnoreCase("successful")) {
            WebElement accountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("account")));
            String accountText = accountElement.getText();
            softAssert.assertTrue(accountText != null && !accountText.isEmpty(),
                    "Account name is empty, login might have failed");

            boolean isLogoutPresent = getDriver().findElements(By.linkText("Log out")).size() > 0;
            softAssert.assertTrue(isLogoutPresent, "Logout link not found, login might have failed");

        } else {
            if (getDriver().findElements(By.className("field-validation-error")).size() > 0) {
                WebElement emailError = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.className("field-validation-error"))
                );
                String emailErrorText = emailError.getText().toLowerCase();
                System.out.println("Email validation error: " + emailErrorText);

                softAssert.assertTrue(emailErrorText.contains("valid email"),
                        "Expected email validation error not found: " + emailErrorText);
            }
            else if (getDriver().findElements(By.className("validation-summary-errors")).size() > 0) {
                WebElement errorElement = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.className("validation-summary-errors"))
                );
                String errorText = errorElement.getText().toLowerCase();
                System.out.println("General login error: " + errorText);

                boolean errorFound = errorText.contains("login was unsuccessful") ||
                        errorText.contains("correct") ||
                        errorText.contains("try again") ||
                        errorText.contains("no customer") ||
                        errorText.contains("found");

                softAssert.assertTrue(errorFound,
                        "Expected login error message not found: " + errorText);
            }
            else {
                softAssert.fail("No error message displayed for unsuccessful login.");
            }
        }

        softAssert.assertAll();
    }


}
