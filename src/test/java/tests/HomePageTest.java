package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;
import pages.HomePage;
import utils.BaseTest;

public class HomePageTest extends BaseTest {
    /**
     *
     1. Launch browser
     2. Navigate to url '<a href="http://automationexercise.com">...</a>'
     3. Verify that home page is visible successfully
     4. Scroll down to footer
     5. Verify text 'SUBSCRIPTION'
     6. Enter email address in input and click arrow button
     7. Verify success message 'You have been successfully subscribed!' is visible
     */
    @Test
    public void verifySubscriptions() {
        HomePage homePage = new HomePage(driver);
        Assertions.assertEquals("Automation Exercise", homePage.getTitle()); // Step 3
        new Actions(driver).scrollToElement(homePage.getFooter()).perform(); // Step 4
        Assertions.assertEquals("SUBSCRIPTION", homePage.getSubscriptionText().getText()); // Step 5
        // Step 6
        homePage.getEmailInput().sendKeys("a@a.com");
        homePage.getSubscribeButton().click();
        Assertions.assertTrue(homePage.getSuccessAlert().isDisplayed()); // Step 7
    }
}
