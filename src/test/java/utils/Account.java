package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.HomePage;
import pages.LogInAndRegistrationPage;

public record Account(String username, String email, String password) {

    public static void removeAds(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelectorAll('ins.adsbygoogle, iframe[id^=\"aswift\"], div[id$=\"_host\"]').forEach(el => el.remove());");
    }

    public static Account registerWithoutDeleting() {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize(); // Step 1
        driver.get("https://automationexercise.com/"); // Step 2
        LogInAndRegistrationPage logInAndRegistrationPage = new LogInAndRegistrationPage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.getSignupLoginLink().click(); // Step 4
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
        String password = "password";
        logInAndRegistrationPage.getPasswordSignUp().sendKeys(password);
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
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        driver.quit();
        return new Account(username, email, password);
    }
}
