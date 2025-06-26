package pages;

import org.openqa.selenium.WebDriver;

public class TestCasesPage {
    WebDriver driver;

    public TestCasesPage(WebDriver driver) {this.driver = driver;}

    public String getTitle() {return driver.getTitle();}
}
