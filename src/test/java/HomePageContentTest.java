import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
        var logo = driver.findElement(By.id("new-logo-locator"));

        var navMenu = driver.findElement(By.id("navigation"));

        var searchBar = driver.findElement(By.id("search"));

        var featuredProducts = driver.findElement(By.className("featured-products"));
    }

    @Test
    public void testVerifyNavigationMenu() {
        WebElement category1 = driver.findElement(By.linkText("Category 1"));
        category1.click();
        String expectedCategoryTitle = "Category 1";
        String actualCategoryTitle = driver.getTitle();

        assertEquals(expectedCategoryTitle, actualCategoryTitle);
    }

    @Test
    public void testSearchForProduct() {
        var searchInput = driver.findElement(By.id("blue"));
        searchInput.sendKeys("t-shirt");

        var searchButton = driver.findElement(By.id("search-button"));
        searchButton.click();

        var searchResults = driver.findElement(By.id("search-results"));
    }

    @Test
    public void testSortAndFilterSearchResults() {
        var sortPriceButton = driver.findElement(By.id("sort-by-price"));
        sortPriceButton.click();

        var sortRelevanceButton = driver.findElement(By.id("sort-by-relevance"));
        sortRelevanceButton.click();

        var priceFilter = driver.findElement(By.id("price-filter"));
        priceFilter.click();

        var categoryFilter = driver.findElement(By.id("category-filter"));
        categoryFilter.click();

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}


