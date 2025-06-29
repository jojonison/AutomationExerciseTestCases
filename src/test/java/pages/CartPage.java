package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {this.driver = driver;}

    public WebElement getFooter() {return driver.findElement(By.id("footer"));}
    public WebElement getSubscriptionText() {
        return driver.findElement(By.xpath(".//h2[text()='Subscription']"));
    }
    public WebElement getEmailInput() {return driver.findElement(By.id("susbscribe_email"));}
    public WebElement getSubscribeButton() {return driver.findElement(By.id("subscribe"));}
    public WebElement getSuccessAlert() {
        return driver.findElement(By.xpath("//div[text()='You have been successfully subscribed!']"));
    }
}
