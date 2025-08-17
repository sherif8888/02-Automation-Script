package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    private WebDriver driver;

    private By searchField = By.id("small-searchterms");
    private By searchButton = By.cssSelector("input.button-1.search-box-button");
    private By searchResults = By.cssSelector(".product-item");
    private By noResultsMessage = By.cssSelector(".no-result");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchKeyword(String keyword) {
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(keyword);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public int getResultsCount() {
        return driver.findElements(searchResults).size();
    }

    public String getNoResultsMessage() {
        return driver.findElement(noResultsMessage).getText();
    }
}