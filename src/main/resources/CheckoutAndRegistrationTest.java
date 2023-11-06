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
        driver.get("https://magento.softwaretestingboard.com");
    }


    @Test
    public void testPaymentMethodSelection() {
        var paymentMethod = driver.findElement(By.id("payment-method"));
        paymentMethod.click();

        var selectedMethod = driver.findElement(By.id("selected-method"));
    }

    @Test
    public void testReviewOrder() {
        var reviewOrderButton = driver.findElement(By.id("review-order-button"));
        reviewOrderButton.click();

        var orderDetails = driver.findElement(By.id("order-details"));
    }

    @Test
    public void testPlaceOrder() {
        placeOrderButton = driver.findElement(By.id("place-order-button"));
        placeOrderButton.click();

        orderConfirmation = driver.findElement(By.id("order-confirmation"));
    }

    @Test
    public void testViewOrderConfirmation() {
        orderConfirmationLink = driver.findElement(By.linkText("Order Confirmation"));
        orderConfirmationLink.click();

        orderDetails = driver.findElement(By.id("order-details"));
        Assert.assertTrue("Order details are not displayed on the page", orderDetails.isDisplayed());
    }

    @Test
    public void testUserAccountRegistration() {
        var signUpLink = driver.findElement(By.linkText("Sign Up"));
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");

        var usernameField = driver.findElement(By.id("username"));
        var emailField = driver.findElement(By.id("asadullah.mohammad0324@gmail.com"));
        var passwordField = driver.findElement(By.id("Password1"));
        var confirmPasswordField = driver.findElement(By.id("confirmPassword"));
        var registerButton = driver.findElement(By.id("registerButton"));

        usernameField.sendKeys("newuser");
        emailField.sendKeys("asadullah.mohammad0324@gmail.com");
        passwordField.sendKeys("Password1");
        confirmPasswordField.sendKeys("Password1");
        registerButton.click();

        var successMessage = driver.findElement(By.className("success-message"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
