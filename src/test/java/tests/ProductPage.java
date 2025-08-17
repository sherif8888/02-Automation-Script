package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    private WebDriver driver;
    private By firstProduct = By.cssSelector(".product-item .product-title a"); // أول منتج في اللستة
    private By addToCartButton = By.cssSelector(".product-item input[value='Add to cart']"); // زرار Add to Cart
    private By successMessage = By.cssSelector(".bar-notification.success"); // رسالة النجاح

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openFirstProduct() {
        driver.findElement(firstProduct).click();
    }

    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }












}

