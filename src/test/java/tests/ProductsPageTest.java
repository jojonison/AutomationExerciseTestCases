package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ProductsPage;
import utils.BaseTest;

public class ProductsPageTest extends BaseTest {

    /**
     *
     1. Launch browser
     2. Navigate to url '<a href="http://automationexercise.com">...</a>'
     3. Verify that home page is visible successfully
     4. Click on 'Products' button
     5. Verify user is navigated to ALL PRODUCTS page successfully
     6. The products list is visible
     7. Click on 'View Product' of first product
     8. User is landed to product detail page
     9. Verify that details are visible: product name, category, price, availability, condition, brand
     */
    @Test
    public void verifyAllProductsAndProductDetailPage() {
        HomePage homePage = new HomePage(driver);
        Assertions.assertEquals("Automation Exercise", homePage.getTitle()); // Step 3
        homePage.getProducts().click(); // Step 4
        ProductsPage productsPage = new ProductsPage(driver);
        Assertions.assertEquals("Automation Exercise - All Products", productsPage.getTitle()); // Step 5
        Assertions.assertTrue(productsPage.getProductsList().isDisplayed()); // Step 6
        productsPage.getViewDetailsByIndex(1).click(); // Step 7 Step 8
        // Step 9
        Assertions.assertTrue(productsPage.getProductName().isDisplayed());
        Assertions.assertTrue(productsPage.getCategory().isDisplayed());
        Assertions.assertTrue(productsPage.getPrice().isDisplayed());
        Assertions.assertTrue(productsPage.getAvailability().isDisplayed());
        Assertions.assertTrue(productsPage.getCondition().isDisplayed());
        Assertions.assertTrue(productsPage.getBrand().isDisplayed());
        System.out.println(productsPage.getProductName().getText());
        System.out.println(productsPage.getCategory().getText());
        System.out.println(productsPage.getPrice().getText());
        System.out.println(productsPage.getAvailability().getText());
        System.out.println(productsPage.getCondition().getText());
        System.out.println(productsPage.getBrand().getText());
    }

    /**
     *
     1. Launch browser
     2. Navigate to url 'h<a href="ttp://automationexercise.com'">...</a>
     3. Verify that home page is visible successfully
     4. Click on 'Products' button
     5. Verify user is navigated to ALL PRODUCTS page successfully
     6. Enter product name in search input and click search button
     7. Verify 'SEARCHED PRODUCTS' is visible
     8. Verify all the products related to search are visible
     */
    @Test
    public void searchProduct() {
        HomePage homePage = new HomePage(driver);
        Assertions.assertEquals("Automation Exercise", homePage.getTitle()); // Step 3
        homePage.getProducts().click(); // Step 4
        ProductsPage productsPage = new ProductsPage(driver);
        Assertions.assertEquals("Automation Exercise - All Products", productsPage.getTitle()); // Step 5
        // Step 6
        productsPage.getSearchProductInput().sendKeys("dress");
        productsPage.getSearchProductsSearchButton().click();
        Assertions.assertEquals("SEARCHED PRODUCTS", productsPage.getSearchedProductsText().getText()); // Step 7
        Assertions.assertTrue(productsPage.getProductsList().isDisplayed()); // Step 8
    }
}
