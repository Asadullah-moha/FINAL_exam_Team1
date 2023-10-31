import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShoppingCartTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("web-driver.chrome.driver", "path_to_chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com");
    }

    @Test
    public void testVerifyProductAddedToCart() {
    }

    @Test
    public void testViewShoppingCart() {

        var cartIcon = driver.findElement(By.id("cart-icon"));
        cartIcon.click();

        var cartContents = driver.findElement(By.id("cart-contents"));
    }

    @Test
    public void testUpdateQuantityInCart() {
    }

    @Test
    public void testRemoveProductFromCart() {

    }

    @Test
    public void testProceedToCheckout() {
        var cartIcon = driver.findElement(By.id("cart-icon"));
        cartIcon.click();

        var checkoutButton = driver.findElement(By.id("checkout-button"));
        checkoutButton.click();

        var checkoutProcess = driver.findElement(By.id("checkout-process"));
    }

    @Test
    public void testFillShippingInformation() {
        var shippingInfoField = driver.findElement(By.id("shipping-info")); // Update the locator
        shippingInfoField.sendKeys("Shipping address"); // Update with actual shipping address

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
