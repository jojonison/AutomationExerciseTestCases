package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.HomePage;

import static tests.HomePageTest.removeAds;

public record Account(String username, String email, String password) {

    public static Account registerWithoutDeleting() {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize(); // Step 1
        driver.get("https://automationexercise.com/"); // Step 2
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
        String password = "password";
        homePage.getPasswordSignUp().sendKeys(password);
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
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        driver.quit();
        return new Account(username, email, password);
    }
}
