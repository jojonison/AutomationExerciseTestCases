package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.HomePage;
import pages.LogInAndRegistrationPage;
import utils.Account;
import utils.BaseTest;

public class LogInAndRegistrationPageTest extends BaseTest {

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
        LogInAndRegistrationPage logInAndRegistrationPage = new LogInAndRegistrationPage(driver);
        HomePage homePage = new HomePage(driver);
        Assertions.assertEquals("Automation Exercise", homePage.getTitle()); // Step 3
        homePage.getSignupLoginLink().click(); // Step 4
        Assertions.assertTrue(logInAndRegistrationPage.getNewSignupText().isDisplayed()); // Step 5
        // Step 6
        String username = "asdfsadfsad" + System.currentTimeMillis(); // prevents duplicate email
        String email = username + "@example.com";
        logInAndRegistrationPage.getNameInputField().sendKeys(username);
        logInAndRegistrationPage.getSignUpEmailInputField().sendKeys(email);
        logInAndRegistrationPage.getSignUpButton().click(); // Step 7
        Assertions.assertTrue(logInAndRegistrationPage.getEnterAccountInformationText().isDisplayed()); // Step 8

        // remove ad
        removeAds(driver);

        // Step 9
        logInAndRegistrationPage.getMrRadio().click();
        logInAndRegistrationPage.getPasswordSignUp().sendKeys("kasjdfh");
        new Select(logInAndRegistrationPage.getDateOfBirthDayDropDown()).selectByValue("25");
        new Select(logInAndRegistrationPage.getDateOfBirthMonthDropDown()).selectByVisibleText("December");
        new Select(logInAndRegistrationPage.getDateOfBirthYearDropDown()).selectByValue("2001");
        logInAndRegistrationPage.getNewsLetterCheckbox().click(); // Step 10
        logInAndRegistrationPage.getOffersCheckbox().click(); // Step 11
        // Step 12
        logInAndRegistrationPage.getFirstName().sendKeys("Jotaro");
        logInAndRegistrationPage.getLastName().sendKeys("Kujo");
        logInAndRegistrationPage.getCompany().sendKeys("Star Platinum");
        logInAndRegistrationPage.getAddress1().sendKeys("asdf");
        logInAndRegistrationPage.getAddress2().sendKeys("asdfasf");
        new Select(logInAndRegistrationPage.getCountry()).selectByValue("Australia");
        logInAndRegistrationPage.getState().sendKeys("asdff");
        logInAndRegistrationPage.getCity().sendKeys("city");
        logInAndRegistrationPage.getZipcode().sendKeys("1234");
        logInAndRegistrationPage.getMobileNumber().sendKeys("91238470");
        logInAndRegistrationPage.getCreateAccountButton().click(); // Step 13
        Assertions.assertTrue(logInAndRegistrationPage.getAccountCreatedText().isDisplayed()); // Step 14
        logInAndRegistrationPage.getContinueButton().click(); // Step 15
        // Step 16
        String actualText = logInAndRegistrationPage.getLoggedInAs(username).getText();
        Assertions.assertEquals("Logged in as " + username, actualText);
        logInAndRegistrationPage.getDeleteAccount().click(); // Step 17
        // Step  18
        Assertions.assertTrue(logInAndRegistrationPage.getAccountDeletedText().isDisplayed());
        logInAndRegistrationPage.getContinueButton().click();
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
        Account account = Account.registerWithoutDeleting(); // Makes an account
        LogInAndRegistrationPage logInAndRegistrationPage = new LogInAndRegistrationPage(driver); // Step 1 Step 2
        HomePage homePage = new HomePage(driver);
        Assertions.assertEquals("Automation Exercise", homePage.getTitle()); // Step 3
        homePage.getSignupLoginLink().click(); // Step 4
        Assertions.assertTrue(logInAndRegistrationPage.getLoginToYourAccountText().isDisplayed()); // Step 5
        String email = account.email(); String password = account.password(); String username = account.username();
        // Step 6
        logInAndRegistrationPage.getEmailLogin().sendKeys(email);
        logInAndRegistrationPage.getPasswordLogin().sendKeys(password);
        logInAndRegistrationPage.getLoginButton().click(); // Step 7
        // Step 8
        String actualText = logInAndRegistrationPage.getLoggedInAs(username).getText();
        Assertions.assertEquals("Logged in as " + username, actualText);
        logInAndRegistrationPage.getDeleteAccount().click(); // Step 9
        Assertions.assertTrue(logInAndRegistrationPage.getAccountDeletedText().isDisplayed()); // Step 10

        // Delete
        homePage.getDeleteAccount().click();
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
        LogInAndRegistrationPage logInAndRegistrationPage = new LogInAndRegistrationPage(driver); // Step 1 Step 2
        HomePage homePage = new HomePage(driver);
        Assertions.assertEquals("Automation Exercise", homePage.getTitle()); // Step 3
        homePage.getSignupLoginLink().click(); // Step 4
        Assertions.assertTrue(logInAndRegistrationPage.getLoginToYourAccountText().isDisplayed()); // Step 5
        logInAndRegistrationPage.getEmailLogin().sendKeys("a@a.com");
        logInAndRegistrationPage.getPasswordLogin().sendKeys("a");
        logInAndRegistrationPage.getLoginButton().click(); // Step 7
        Assertions.assertTrue(logInAndRegistrationPage.getIncorrectDetailsText().isDisplayed());
    }

    /**
     *
     1. Launch browser
     2. Navigate to url '<a href="http://automationexercise.com">...</a>'
     3. Verify that home page is visible successfully
     4. Click on 'Signup / Login' button
     5. Verify 'Login to your account' is visible
     6. Enter correct email address and password
     7. Click 'login' button
     8. Verify that 'Logged in as username' is visible
     9. Click 'Logout' button
     10. Verify that user is navigated to login page
     */
    @Test
    public void logoutUser() {
        Account account = Account.registerWithoutDeleting();
        LogInAndRegistrationPage logInAndRegistrationPage = new LogInAndRegistrationPage(driver); // Step 1
        HomePage homePage = new HomePage(driver);
        Assertions.assertEquals("Automation Exercise", homePage.getTitle()); // Step 3
        homePage.getSignupLoginLink().click(); // Step 4
        Assertions.assertTrue(logInAndRegistrationPage.getLoginToYourAccountText().isDisplayed()); // Step 5
        String email = account.email(); String password = account.password(); String username = account.username();
        // Step 6
        logInAndRegistrationPage.getEmailLogin().sendKeys(email);
        logInAndRegistrationPage.getPasswordLogin().sendKeys(password);
        logInAndRegistrationPage.getLoginButton().click(); // Step 7
        // Step 8
        String actualText = logInAndRegistrationPage.getLoggedInAs(username).getText();
        Assertions.assertEquals("Logged in as " + username, actualText);
        logInAndRegistrationPage.getLogout().click(); // Step 9
        Assertions.assertTrue(logInAndRegistrationPage.getLoginToYourAccountText().isDisplayed()); // Step 10

        // Delete Account
        logInAndRegistrationPage.getEmailLogin().sendKeys(account.email());
        logInAndRegistrationPage.getPasswordLogin().sendKeys(account.password());
        logInAndRegistrationPage.getLoginButton().click();
        homePage.getDeleteAccount().click();
    }

    /**
     *
     1. Launch browser
     2. Navigate to url '<a href="http://automationexercise.com">...</a>'
     3. Verify that home page is visible successfully
     4. Click on 'Signup / Login' button
     5. Verify 'New User Signup!' is visible
     6. Enter name and already registered email address
     7. Click 'Signup' button
     8. Verify error 'Email Address already exist!' is visible

     FINDINGS: Error: 'Email Address already exist!' is not always caught
     */
    @RepeatedTest(3)
    public void registerWithExistingEmail() {
        Account account = Account.registerWithoutDeleting();
        LogInAndRegistrationPage logInAndRegistrationPage = new LogInAndRegistrationPage(driver); // Step 1
        HomePage homePage = new HomePage(driver);
        Assertions.assertEquals("Automation Exercise", homePage.getTitle()); // Step 3
        homePage.getSignupLoginLink().click(); // Step 4
        // setting up an account
        String email = account.email(); String password = account.password(); String username = account.username();
        logInAndRegistrationPage.getEmailLogin().sendKeys(email);
        logInAndRegistrationPage.getPasswordLogin().sendKeys(password);
        logInAndRegistrationPage.getLoginButton().click();
        String actualText = logInAndRegistrationPage.getLoggedInAs(username).getText();
        Assertions.assertEquals("Logged in as " + username, actualText);
        homePage.getLogout().click();

        Assertions.assertTrue(logInAndRegistrationPage.getNewSignupText().isDisplayed()); // Step 5
        // Step 6
        logInAndRegistrationPage.getNameInputField().sendKeys(account.username());
        logInAndRegistrationPage.getSignUpEmailInputField().sendKeys(account.email());
        logInAndRegistrationPage.getSignUpButton().click(); // Step 7
        Assertions.assertTrue(logInAndRegistrationPage.getEmailAlreadyExistsTest().isDisplayed()); // Step 8

        // Delete Account
        logInAndRegistrationPage.getEmailLogin().sendKeys(account.email());
        logInAndRegistrationPage.getPasswordLogin().sendKeys(account.password());
        logInAndRegistrationPage.getLoginButton().click();
        homePage.getDeleteAccount().click();
    }


}
