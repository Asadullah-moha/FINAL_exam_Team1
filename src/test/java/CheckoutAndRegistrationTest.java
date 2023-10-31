import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckoutAndRegistrationTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com"); // Update URL
    }

    // ... Existing test cases for HomePageContent, Navigation Menu, Search, Sorting, Cart operations, and Shipping Info ...

    @Test
    public void testPaymentMethodSelection() {
        WebElement paymentMethod = driver.findElement(By.id("payment-method")); // Update the locator
        paymentMethod.click();

        WebElement selectedMethod = driver.findElement(By.id("selected-method")); // Update the locator
    }

    @Test
    public void testReviewOrder() {
        WebElement reviewOrderButton = driver.findElement(By.id("review-order-button")); // Update the locator
        reviewOrderButton.click();

        WebElement orderDetails = driver.findElement(By.id("order-details")); // Update the locator
    }

    @Test
    public void testPlaceOrder() {
        WebElement placeOrderButton = driver.findElement(By.id("place-order-button")); // Update the locator
        placeOrderButton.click();

        WebElement orderConfirmation = driver.findElement(By.id("order-confirmation")); // Update the locator
    }

    @Test
    public void testViewOrderConfirmation() {
        WebElement orderConfirmationLink = driver.findElement(By.linkText("Order Confirmation")); // Update the link text
        orderConfirmationLink.click();

        WebElement orderDetails = driver.findElement(By.id("order-details"));
        Assert.assertTrue("Order details are not displayed on the page", orderDetails.isDisplayed());
    }

    @Test
    public void testUserAccountRegistration() {
        WebElement signUpLink = driver.findElement(By.linkText("Sign Up"));
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement emailField = driver.findElement(By.id("asadullah.mohammad0324@gmail.com"));
        WebElement passwordField = driver.findElement(By.id("Password1"));
        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
        WebElement registerButton = driver.findElement(By.id("registerButton"));

        usernameField.sendKeys("newuser");
        emailField.sendKeys("asadullah.mohammad0324@gmail.com");
        passwordField.sendKeys("Password1");
        confirmPasswordField.sendKeys("Password1");
        registerButton.click();

        WebElement successMessage = driver.findElement(By.className("success-message")); // Update with actual class name
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
