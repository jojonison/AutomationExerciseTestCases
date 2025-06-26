package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.TestCasesPage;
import utils.BaseTest;

public class TestCasesPageTest extends BaseTest {
    @Test
    public void verifyNavigatedSuccessfully() {
        HomePage homePage = new HomePage(driver);
        homePage.getTestCases().click();
        TestCasesPage testCasesPage = new TestCasesPage(driver);
        Assertions.assertEquals(
                "Automation Practice Website for UI Testing - Test Cases",
                testCasesPage.getTitle()
        );
    }
}
