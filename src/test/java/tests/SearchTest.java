package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(tests.ExtentReportListener.class)
public class SearchTest extends BaseTest {

    @Test(dataProvider = "searchData", dataProviderClass = TestDataProvider.class, groups = {"search"})
    public void searchFunctionTest(String keyword, String expectedResult) {
        getDriver().get("https://demowebshop.tricentis.com/");

        SearchPage searchPage = new SearchPage(getDriver()
        );

        searchPage.enterSearchKeyword(keyword);
        searchPage.clickSearch();

        String actualResult;

        // التحقق من وجود Alert
        try {
            Alert alert = getDriver()
                    .switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Popup message: " + alertText);
            alert.accept(); // نغلق البوب أب
            actualResult = "not_found";
        } catch (NoAlertPresentException e) {
            // مفيش بوب أب، يبقى نكمل الفحص بالطريقة العادية
            if (searchPage.getResultsCount() > 0) {
                actualResult = "found";
            } else {
                actualResult = "not_found";
            }
        }

        // المقارنة
        if (actualResult.equalsIgnoreCase(expectedResult)) {
            System.out.println("✅ Test Passed - Expected and actual match: " + expectedResult);
        } else {
            Assert.fail("❌ Test Failed - Expected: " + expectedResult + " but got: " + actualResult);
        }
    }
}