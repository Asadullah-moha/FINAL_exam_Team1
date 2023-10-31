import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class HomePageContentTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com");
    }

    @Test
    public void testVerifyHomePageContent() {
        // Verify that the homepage displays a logo
        WebElement logo = driver.findElement(By.id("new-logo-locator"));

        // Verify that the navigation menu is visible
        WebElement navMenu = driver.findElement(By.id("navigation"));

        // Verify that the search bar is visible
        WebElement searchBar = driver.findElement(By.id("search"));

        // Verify that featured products are displayed on the homepage
        WebElement featuredProducts = driver.findElement(By.className("featured-products"));
    }

    @Test
    public void testVerifyNavigationMenu() {
        // Click on each main category in the navigation menu
        WebElement category1 = driver.findElement(By.linkText("Category 1"));
        category1.click();
        String expectedCategoryTitle = "Category 1"; // Replace with the expected category page title
        String actualCategoryTitle = driver.getTitle(); // Get the actual page title

// Assert that the actual page title matches the expected category title
        assertEquals(expectedCategoryTitle, actualCategoryTitle);
    }

    @Test
    public void testSearchForProduct() {
        // Enter a search term in the search bar
        WebElement searchInput = driver.findElement(By.id("search")); // Update the locator
        searchInput.sendKeys("t-shirt"); // Use the desired search term

        // Click the search button
        WebElement searchButton = driver.findElement(By.id("search-button")); // Update the locator
        searchButton.click();

        // Verify that search results are displayed
        WebElement searchResults = driver.findElement(By.id("search-results")); // Update the locator
        // Add an assertion to check if search results are displayed
    }

    @Test
    public void testSortAndFilterSearchResults() {
        // Perform a search (if not already performed in a previous test case)
        // Sort the search results by price
        WebElement sortPriceButton = driver.findElement(By.id("sort-by-price")); // Update the locator
        sortPriceButton.click();

        // Sort the search results by relevance
        WebElement sortRelevanceButton = driver.findElement(By.id("sort-by-relevance")); // Update the locator
        sortRelevanceButton.click();

        // Apply filters (e.g., price range, category)
        WebElement priceFilter = driver.findElement(By.id("price-filter")); // Update the locator
        priceFilter.click();

        WebElement categoryFilter = driver.findElement(By.id("category-filter")); // Update the locator
        categoryFilter.click();

        // Verify that results are sorted and filtered accordingly
        // Add assertions to check the sorting and filtering results
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}


