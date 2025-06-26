package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

public class ProductsPage {
    WebDriver driver;

    public ProductsPage(WebDriver driver) {this.driver = driver;}

    public String getTitle() {return driver.getTitle();}

    public WebElement getProductsList() {return driver.findElement(By.className("features_items"));}

    public WebElement getViewDetailsByIndex(int index) {
        String selector = String.format("a[href='/product_details/%d']", index);
        return driver.findElement(By.cssSelector(selector));
    }

    // product name, category, price, availability, condition, brand
    public WebElement getProductName() {
        return driver.findElement(new ByChained(
                By.className("product-information"), By.tagName("h2")
        ));
    }

    public WebElement getCategory() {
        return driver.findElement(new ByChained(
                By.className("product-information"),
                By.xpath(".//p[starts-with(text(), 'Category:')]")
        ));
    }

    public WebElement getPrice() {
        return driver.findElement(new ByChained(
                By.className("product-information"), By.tagName("span"),By.tagName("span")
        ));
    }

    public WebElement getAvailability() {
        return driver.findElement(new ByChained(
                By.className("product-information"),
                By.xpath(".//p[b[text()='Availability:']]")
        ));
    }

    public WebElement getCondition() {
        return driver.findElement(new ByChained(
                By.className("product-information"),
                By.xpath(".//p[b[text()='Condition:']]")
        ));
    }

    public WebElement getBrand() {
        return driver.findElement(new ByChained(
                By.className("product-information"),
                By.xpath(".//p[b[text()='Brand:']]")
        ));
    }

    public WebElement getSearchProductInput() {return driver.findElement(By.id("search_product"));}
    public WebElement getSearchProductsSearchButton() {return driver.findElement(By.id("submit_search"));}
    public WebElement getSearchedProductsText() {
        return driver.findElement(By.cssSelector("h2.title.text-center"));
    }

}
