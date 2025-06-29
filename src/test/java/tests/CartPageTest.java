package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;
import pages.CartPage;
import pages.HomePage;
import utils.BaseTest;

public class CartPageTest extends BaseTest {
    /**
     *
     1. Launch browser
     2. Navigate to url 'h<a href="ttp://automationexercise.com'">...</a>
     3. Verify that home page is visible successfully
     4. Click 'Cart' button
     5. Scroll down to footer
     6. Verify text 'SUBSCRIPTION'
     7. Enter email address in input and click arrow button
     8. Verify success message 'You have been successfully subscribed!' is visible
     */
    @Test
    public void verifySubscription() {
        HomePage homePage = new HomePage(driver);
        Assertions.assertEquals("Automation Exercise", homePage.getTitle()); // Step 3
        homePage.getCartPageLink().click(); // Step 4
        CartPage cartPage = new CartPage(driver);
        new Actions(driver).scrollToElement(cartPage.getFooter()).perform(); // Step 5
        Assertions.assertEquals("SUBSCRIPTION", cartPage.getSubscriptionText().getText()); // Step 6
        // Step 7
        cartPage.getEmailInput().sendKeys("a@a.com");
        cartPage.getSubscribeButton().click();
        Assertions.assertTrue(cartPage.getSuccessAlert().isDisplayed()); // Step 8
    }
}
