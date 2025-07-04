package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {return driver.getTitle();}

    public WebElement getCartPageLink() {return driver.findElement(By.linkText("Cart"));}
    public WebElement getContactUsLink() {return driver.findElement(By.linkText("Contact us"));}
    public WebElement getSignupLoginLink() {return driver.findElement(By.linkText("Signup / Login"));}
    public WebElement getDeleteAccount() {return driver.findElement(By.cssSelector("a[href='/delete_account']"));}
    public WebElement getLogout() {return driver.findElement(By.cssSelector("a[href='/logout']"));}
    public WebElement getTestCases() {return driver.findElement(By.cssSelector("a[href='/test_cases']"));}
    public WebElement getProducts() {return driver.findElement(By.cssSelector("a[href='/products']"));}
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
