import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserAccountAndSupportTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com"); // Update URL
    }

    @Test
    public void testUserLogin() {
        // Click on the "Login" link
        var loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();

        var usernameField = driver.findElement(By.id("asadullah.mohammad0324@gmail.com"));
        var passwordField = driver.findElement(By.id("Password1"));
        var loginButton = driver.findElement(By.id("loginButton"));

        usernameField.sendKeys("asadullah.mohammad0324@gmail.com");
        passwordField.sendKeys("Password1");
        loginButton.click();

        var userDashboard = driver.findElement(By.id("user-dashboard"));
    }

    @Test
    public void testPasswordReset() {
        var forgotPasswordLink = driver.findElement(By.linkText("Forgot Password"));
        forgotPasswordLink.click();

        var emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("asadullah.mohammad0324@gmail.com");

        var resetButton = driver.findElement(By.id("resetButton"));
        resetButton.click();

        var confirmationMessage = driver.findElement(By.id("reset-confirmation"));
    }

    @Test
    public void testProductReviewSubmission() {

        // Submit a product review and rating
        var reviewField = driver.findElement(By.id("review"));
        var ratingField = driver.findElement(By.id("rating"));
        var submitButton = driver.findElement(By.id("submit-review"));

        reviewField.sendKeys("This product is great!");
        ratingField.sendKeys("5");
        submitButton.click();

        // Verify that the review is posted
        var postedReview = driver.findElement(By.className("review-posted"));
    }

    @Test
    public void testContactCustomerSupport() {
        var contactUsLink = driver.findElement(By.linkText("Contact Us"));
        contactUsLink.click();

        var nameField = driver.findElement(By.id("asad mohammad"));
        var emailField = driver.findElement(By.id("asadullah.mohammad0324@gmail.com"));
        var messageField = driver.findElement(By.id("message"));
        var sendButton = driver.findElement(By.id("send-button"));

        nameField.sendKeys("asad mohammad");
        emailField.sendKeys("asadullah.mohammad0324@gmail.com");
        messageField.sendKeys("This is a test message.");
        sendButton.click();

        var confirmationMessage = driver.findElement(By.id("need support-confirmation"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
