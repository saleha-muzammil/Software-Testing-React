import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

public class LoginTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/home/saleha/Downloads/chromedriver/chromedriver");

        // Initialize the ChromeDriver
        driver = new ChromeDriver();
    }

    @Test
    public void testSuccessfulLogin() {
        driver.get("http://localhost:3000");

        // Perform login steps
        login("l20@nu.edu.pk", "123");

        // Verify redirect to student home page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/Student-Home"));

        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/Student-Home", currentUrl);
    }

    @Test
    public void testUnsuccessfulLogin() {
        driver.get("http://localhost:3000");

        // Perform login steps
        login("l20@nu.edu.pk", "abc");

        // Verify that the login was not successful
        // This assumes the presence of an error message or the URL not changing
        // Replace with actual verification logic based on the application's behavior
        String currentUrl = driver.getCurrentUrl();
        assertNotEquals("http://localhost:3000/Student-Home", currentUrl);
        // You could also check for an error message element or the lack of a logout button, etc.
    }

private void login(String email, String password) {
    // Click the "Sign in as Student" button
    WebElement signInButton = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.className("Sign-In-Button")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signInButton);

    // Wait for the login modal to appear and enter credentials
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
    WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

    // Now using class names to locate the Login button
    WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button.btn.btn-primary.px-4.mt-2")));

    emailInput.sendKeys(email);
    passwordInput.sendKeys(password);
    loginButton.click();
}



    @After
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
