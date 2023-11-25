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
import org.openqa.selenium.NoAlertPresentException;

public class SubmissionRequest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/home/saleha/Downloads/chromedriver/chromedriver");

        // Initialize the ChromeDriver
        driver = new ChromeDriver();
    }

    @Test
    public void testRequestSubmission() {
        
        driver.get("http://localhost:3000");

        // Log into the system
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

    emailInput.sendKeys("l20@nu.edu.pk");
    passwordInput.sendKeys("123");
    loginButton.click();

        // Navigate to the request page
        WebElement makeRequestButton = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.buttons-hover")));

    // Click the "Make a Request" button
    makeRequestButton.click();

        // Select a specific request type
        WebElement submitrequest = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.Request.buttons-hover")));

    submitrequest.click();

        // Wait for the file upload page to load
            WebElement fileUploadInput = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("inputGroupFile")));

        // Specify the path to the file to be uploaded
        String filePath = "/home/saleha/Downloads/Paper.pdf"; // Replace with the actual file path

        // Upload the file
        fileUploadInput.sendKeys(filePath);

        // Submit the request
         WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary.buttons-hover.px-4.mt-2")));
((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

        // Verify the submission (you will need to replace the below condition based on your application's behavior)
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            // Optionally, include an assertion here to confirm successful submission
            // Example: assertTrue(driver.getCurrentUrl().contains("expected_url_after_submission"));
        } catch (NoAlertPresentException e) {
            fail("Alert was not present when expected");
        }
    }

    @After
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
