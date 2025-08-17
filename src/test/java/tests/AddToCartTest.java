package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

@Listeners(ExtentReportListener.class)
public class AddToCartTest extends BaseTest {

    @Test(dataProvider = "cartData", dataProviderClass = TestDataProvider.class, groups = {"cart"})
    public void testAddToCart(String categoryUrl, String expectedMessage) {
        getDriver().get(categoryUrl);

        ProductPage productPage = new ProductPage(getDriver());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        SoftAssert softAssert = new SoftAssert();

        productPage.clickAddToCart();

        WebElement messageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bar-notification.success"))
        );

        String actualMessage = messageElement.getText();
        System.out.println("Cart message: " + actualMessage);

        softAssert.assertTrue(actualMessage.contains(expectedMessage),
                "Expected message not found! Actual: " + actualMessage);

        WebElement cartQty = getDriver().findElement(By.cssSelector("span.cart-qty"));
        String qtyText = cartQty.getText(); // زي: (1)
        softAssert.assertTrue(!qtyText.equals("(0)"), "Cart quantity did not increase!");

        softAssert.assertAll();
    }
}
