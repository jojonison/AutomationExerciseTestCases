package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.ContactUsPage;
import pages.HomePage;
import utils.BaseTest;

import java.io.File;

public class ContactUsPageTest extends BaseTest {

    public static void removeAds(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelectorAll('ins.adsbygoogle, iframe[id^=\"aswift\"], div[id$=\"_host\"]').forEach(el => el.remove());");
    }

    /**
     *
     1. Launch browser
     2. Navigate to url '<a href="http://automationexercise.com">...</a>'
     3. Verify that home page is visible successfully
     4. Click on 'Contact Us' button
     5. Verify 'GET IN TOUCH' is visible
     6. Enter name, email, subject and message
     7. Upload file
     8. Click 'Submit' button
     9. Click OK button
     10. Verify success message 'Success! Your details have been submitted successfully.' is visible
     11. Click 'Home' button and verify that landed to home page successfully
     */
    @Test
    public void contactUsForm() {
        HomePage homePage = new HomePage(driver);
        Assertions.assertEquals("Automation Exercise", homePage.getTitle()); // Step 3
        homePage.getContactUsLink().click(); // Step 5
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        Assertions.assertTrue(contactUsPage.getGetInTouchText().isDisplayed()); // Step 5
        // Step 6
        removeAds(driver);
        contactUsPage.getNameInputField().sendKeys("asdf");
        contactUsPage.getEmailInputField().sendKeys("asdf@asadf.com");
        contactUsPage.getSubjectInputField().sendKeys("Hello");
        contactUsPage.getMessageInputField().sendKeys("Hi");
        // Step 7
        File file = new File("src/test/java/utils/dummyfile.txt");
        contactUsPage.getUploadFileInputField().sendKeys(file.getAbsolutePath()); // Step 7
        contactUsPage.getSubmitButton().click(); // Step 8
        contactUsPage.getAlert().accept(); // Step 9
        Assertions.assertTrue(contactUsPage.getSuccessMessage().isDisplayed()); // Step 10
        // Step 11
        contactUsPage.getHomeLink().click();
        Assertions.assertEquals("Automation Exercise", homePage.getTitle());
    }
}
