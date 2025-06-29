package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogInAndRegistrationPage {
    WebDriver driver;

    public LogInAndRegistrationPage(WebDriver driver) {this.driver = driver;}

    public WebElement getNewSignupText() {
        return driver.findElement(By.xpath("//h2[contains(text(), 'New User Signup!')]"));
    }
    public WebElement getLoginToYourAccountText() {
        return driver.findElement(By.xpath("//h2[contains(text(), 'Login to your account')]"));
    }
    public WebElement getNameInputField() {return driver.findElement(By.cssSelector("[data-qa='signup-name']"));}
    public WebElement getSignUpEmailInputField() {return driver.findElement(By.cssSelector("[data-qa='signup-email']"));}

    public WebElement getSignUpButton() {return driver.findElement(By.cssSelector("[data-qa='signup-button']"));}
    public WebElement getEnterAccountInformationText() {
        return driver.findElement(By.xpath("//b[text()='Enter Account Information']"));
    }
    public WebElement getMrRadio() {return driver.findElement(By.id("id_gender1"));}
    public WebElement getPasswordSignUp() {return driver.findElement(By.id("password"));}
    public WebElement getDateOfBirthDayDropDown() {return driver.findElement(By.id("days"));}
    public WebElement getDateOfBirthMonthDropDown() {return driver.findElement(By.id("months"));}
    public WebElement getDateOfBirthYearDropDown() {return driver.findElement(By.id("years"));}
    public WebElement getNewsLetterCheckbox() {return driver.findElement(By.id("newsletter"));}
    public WebElement getOffersCheckbox() {return driver.findElement(By.id("optin"));}
    public WebElement getFirstName() {return driver.findElement(By.id("first_name"));}
    public WebElement getLastName() {return driver.findElement(By.id("last_name"));}
    public WebElement getCompany() {return driver.findElement(By.id("company"));}
    public WebElement getAddress1() {return driver.findElement(By.id("address1"));}
    public WebElement getAddress2() {return driver.findElement(By.id("address2"));}
    public WebElement getCountry() {return driver.findElement(By.id("country"));}
    public WebElement getState() {return driver.findElement(By.id("state"));}
    public WebElement getCity() {return driver.findElement(By.id("city"));}
    public WebElement getZipcode() {return driver.findElement(By.id("zipcode"));}
    public WebElement getMobileNumber() {return driver.findElement(By.id("mobile_number"));}
    public WebElement getCreateAccountButton() {return driver.findElement(By.cssSelector("[data-qa='create-account']"));}
    public WebElement getAccountCreatedText() {
        return driver.findElement(By.cssSelector("[data-qa='account-created']"));
    }
    public WebElement getContinueButton() {return driver.findElement(By.cssSelector("[data-qa='continue-button']"));}
    public WebElement getAccountDeletedText() {
        return driver.findElement(By.cssSelector("[data-qa='account-deleted']"));
    }
    public WebElement getDeleteAccount() {return driver.findElement(By.cssSelector("a[href='/delete_account']"));}

    public WebElement getEmailLogin() {return driver.findElement(By.cssSelector("[data-qa='login-email']"));}
    public WebElement getPasswordLogin() {return driver.findElement(By.cssSelector("[data-qa='login-password']"));}
    public WebElement getLoginButton() {return driver.findElement(By.cssSelector("[data-qa='login-button']"));}

    public WebElement getLoggedInAs(String username) {
        return driver.findElement(By.xpath("//a[.//b[text()='" + username + "']]"));
    }

    public WebElement getIncorrectDetailsText() {
        return driver.findElement(By.xpath("//p[text()='Your email or password is incorrect!']"));
    }

    public WebElement getLogout() {return driver.findElement(By.cssSelector("a[href='/logout']"));}

    public WebElement getEmailAlreadyExistsTest() {
        return driver.findElement(By.xpath("//p[text()='Email Address already exist!']"));
    }

}
