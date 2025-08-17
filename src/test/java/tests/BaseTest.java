package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BaseTest {

    // نخزن الـ WebDriver لكل Thread
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeClass(alwaysRun = true)
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        WebDriver drv = new ChromeDriver();
        drv.manage().window().maximize();
        driver.set(drv);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenshotOnFailure(ITestResult result) {
        // اسم الاختبار لكل DataProvider row
        String testName = result.getMethod().getMethodName() + "_" + Arrays.toString(result.getParameters());

        if (result.getStatus() == ITestResult.FAILURE && getDriver() != null) {
            try {
                TakesScreenshot ts = (TakesScreenshot) getDriver();
                File source = ts.getScreenshotAs(OutputType.FILE);

                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String fileName = testName + "_" + timestamp + ".png";

                File destDir = new File("reports/screenshots/");
                if (!destDir.exists()) destDir.mkdirs();

                File destination = new File(destDir, fileName);
                Files.copy(source.toPath(), destination.toPath());

                System.out.println("Screenshot saved: " + destination.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
