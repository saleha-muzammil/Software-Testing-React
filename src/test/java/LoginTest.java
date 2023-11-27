import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/saleha/Downloads/chromedriver/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
    }

    
    @Test
    public void testNoEmailEntered() {
        driver.get("http://localhost:3000");
        login("", "123"); // No email entered
        verifyErrorMessage("Email is required");
    }

    @Test
    public void testNoPasswordEntered() {
        driver.get("http://localhost:3000");
        login("l20@nu.edu.pk", ""); // No password entered
        WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']/following-sibling::div[@class='error']")));
        assertEquals("Password is required", passwordError.getText());
    }

    @Test
    public void testInvalidEmailEntered() {
        driver.get("http://localhost:3000");
        login("hello", "123"); // Invalid email entered
        verifyErrorMessage("Please enter a valid email address");
    }

    @Test
    public void testNoCredentialsEntered() {
        driver.get("http://localhost:3000");
        login("", ""); // No credentials entered
        WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']/following-sibling::div[@class='error']")));
        assertEquals("Email is required", emailError.getText());
        WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']/following-sibling::div[@class='error']")));
        assertEquals("Password is required", passwordError.getText());
    }

    private void verifyErrorMessage(String expectedError) {
        WebElement errorDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String actualError = errorDiv.getText();
        assertTrue("The expected error message is not displayed.", actualError.contains(expectedError));
    }

    private void login(String email, String password) {
        // Click the "Sign in as Student" button
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("Sign-In-Button")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signInButton);

        // Wait for the login modal to appear and enter credentials
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        // Now using class names to locate the Login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary.px-4.mt-2")));

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
