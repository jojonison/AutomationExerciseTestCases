package tests;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.HomePage;
import utils.Account;
import utils.BaseTest;

public class HomePageTest extends BaseTest {

    /**
     *
     1. Launch browser
     2. Navigate to url <a href="http://automationexercise.com">...</a>'
     3. Verify that home page is visible successfully
     4. Click on 'Signup / Login' button
     5. Verify 'New User Signup!' is visible
     6. Enter name and email address
     7. Click 'Signup' button
     8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
     9. Fill details: Title, Name, Email, Password, Date of birth
     10. Select checkbox 'Sign up for our newsletter!'
     11. Select checkbox 'Receive special offers from our partners!'
     12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
     13. Click 'Create Account button'
     14. Verify that 'ACCOUNT CREATED!' is visible
     15. Click 'Continue' button
     16. Verify that 'Logged in as username' is visible
     17. Click 'Delete Account' button
     18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
     */
    @Test
    public void registerUser() {
        HomePage homePage = new HomePage(driver);
        WebElement signupLogin = homePage.getSignupLoginLink();

        Assertions.assertTrue(homePage.getHomeIcon().isDisplayed()); // Step 3
        signupLogin.click(); // Step 4
        Assertions.assertTrue(homePage.getNewSignupText().isDisplayed()); // Step 5
        // Step 6
        String username = "asdfsadfsad" + System.currentTimeMillis(); // prevents duplicate email
        String email = username + "@example.com";
        homePage.getNameInputField().sendKeys(username);
        homePage.getSignUpEmailInputField().sendKeys(email);
        homePage.getSignUpButton().click(); // Step 7
        Assertions.assertTrue(homePage.getEnterAccountInformationText().isDisplayed()); // Step 8

        // remove ad
        removeAds(driver);

        // Step 9
        homePage.getMrRadio().click();
        homePage.getPasswordSignUp().sendKeys("kasjdfh");
        new Select(homePage.getDateOfBirthDayDropDown()).selectByValue("25");
        new Select(homePage.getDateOfBirthMonthDropDown()).selectByVisibleText("December");
        new Select(homePage.getDateOfBirthYearDropDown()).selectByValue("2001");
        homePage.getNewsLetterCheckbox().click(); // Step 10
        homePage.getOffersCheckbox().click(); // Step 11
        // Step 12
        homePage.getFirstName().sendKeys("Jotaro");
        homePage.getLastName().sendKeys("Kujo");
        homePage.getCompany().sendKeys("Star Platinum");
        homePage.getAddress1().sendKeys("asdf");
        homePage.getAddress2().sendKeys("asdfasf");
        new Select(homePage.getCountry()).selectByValue("Australia");
        homePage.getState().sendKeys("asdff");
        homePage.getCity().sendKeys("city");
        homePage.getZipcode().sendKeys("1234");
        homePage.getMobileNumber().sendKeys("91238470");
        homePage.getCreateAccountButton().click(); // Step 13
        Assertions.assertTrue(homePage.getAccountCreatedText().isDisplayed()); // Step 14
        homePage.getContinueButton().click(); // Step 15
        // Step 16
        String actualText = homePage.getLoggedInAs(username).getText();
        Assertions.assertEquals("Logged in as " + username, actualText);
        homePage.getDeleteAccount().click(); // Step 17
        // Step  18
        Assertions.assertTrue(homePage.getAccountDeletedText().isDisplayed());
        homePage.getContinueButton().click();
    }

    public static void removeAds(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelectorAll('ins.adsbygoogle, iframe[id^=\"aswift\"], div[id$=\"_host\"]').forEach(el => el.remove());");
    }

    /**
     1. Launch browser
     2. Navigate to url 'http:/<a href="/automationexercise.com'"></a>
     3. Verify that home page is visible successfully
     4. Click on 'Signup / Login' button
     5. Verify 'Login to your account' is visible
     6. Enter correct email address and password
     7. Click 'login' button
     8. Verify that 'Logged in as username' is visible
     9. Click 'Delete Account' button
     10. Verify that 'ACCOUNT DELETED!' is visible
     */
    @Test
    public void loginWithCorrectUsernameAndPassword() {
        Account account = Account.registerWithoutDeleting();
        HomePage homePage = new HomePage(driver); // Step 1
        WebElement signupLogin = homePage.getSignupLoginLink(); // Step 2
        Assertions.assertTrue(homePage.getHomeIcon().isDisplayed()); // Step 3
        signupLogin.click(); // Step 4
        Assertions.assertTrue(homePage.getLoginToYourAccountText().isDisplayed()); // Step 5
        String email = account.email(); String password = account.password(); String username = account.username();
        // Step 6
        homePage.getEmailLogin().sendKeys(email);
        homePage.getPasswordLogin().sendKeys(password);
        homePage.getLoginButton().click(); // Step 7
        // Step 8
        String actualText = homePage.getLoggedInAs(username).getText();
        Assertions.assertEquals("Logged in as " + username, actualText);
        homePage.getDeleteAccount().click(); // Step 9
        Assertions.assertTrue(homePage.getAccountDeletedText().isDisplayed()); // Step 10
    }

    /**
     *
     1. Launch browser
     2. Navigate to url '<a href="http://automationexercise.com">...</a>'
     3. Verify that home page is visible successfully
     4. Click on 'Signup / Login' button
     5. Verify 'Login to your account' is visible
     6. Enter incorrect email address and password
     7. Click 'login' button
     8. Verify error 'Your email or password is incorrect!' is visible
     */
    @Test
    public void loginWithIncorrectUsernameAndPassword() {
        HomePage homePage = new HomePage(driver); // Step 1
        WebElement signupLogin = homePage.getSignupLoginLink(); // Step 2
        Assertions.assertTrue(homePage.getHomeIcon().isDisplayed()); // Step 3
        signupLogin.click(); // Step 4
        Assertions.assertTrue(homePage.getLoginToYourAccountText().isDisplayed()); // Step 5
        homePage.getEmailLogin().sendKeys("a@a.com");
        homePage.getPasswordLogin().sendKeys("a");
        homePage.getLoginButton().click(); // Step 7
        Assertions.assertTrue(homePage.getIncorrectDetailsText().isDisplayed());
    }
}
