package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactUsPage {
    WebDriver driver;

    public ContactUsPage(WebDriver driver) {this.driver = driver;}

    public WebElement getGetInTouchText() {return driver.findElement(By.cssSelector(".title.text-center"));}

    public WebElement getNameInputField() {return driver.findElement(By.cssSelector("[data-qa='name']"));}
    public WebElement getEmailInputField() {return driver.findElement(By.cssSelector("[data-qa='email']"));}
    public WebElement getSubjectInputField() {return driver.findElement(By.cssSelector("[data-qa='subject']"));}
    public WebElement getMessageInputField() {return driver.findElement(By.cssSelector("[data-qa='message']"));}
    public WebElement getUploadFileInputField() {return driver.findElement(By.cssSelector("[name='upload_file']"));}
    public WebElement getSubmitButton() {return driver.findElement(By.cssSelector("[data-qa='submit-button']"));}
    public Alert getAlert() {return driver.switchTo().alert();}
    public WebElement getSuccessMessage() {return driver.findElement(By.cssSelector(".status.alert.alert-success"));}
    public WebElement getHomeLink() {return driver.findElement(By.linkText("Home"));}
}
