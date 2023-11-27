
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author saleha
 */
public class viewOngoingRequests {
    
     private WebDriver driver;
     private final String downloadPath = "/home/saleha/Downloads"; 

    @Before
    public void setUp() {
        
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", prefs);
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/home/saleha/Downloads/chromedriver/chromedriver");

        // Initialize the ChromeDriver
        driver = new ChromeDriver();
    }

    @Test
    public void testOngoing() {
        
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
        //navigate to home screen        
        // Wait for the loading indicator to disappear
wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
        WebElement homeButton = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-success")));

    homeButton.click();
    
          List<WebElement> OngoingRequests = new WebDriverWait(driver, Duration.ofSeconds(10))
    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button.buttons-hover")));

WebElement newReq = OngoingRequests.get(1);
((JavascriptExecutor) driver).executeScript("arguments[0].click();", newReq);

WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'firebasestorage.googleapis')]")));

// Click the download link to start the download
((JavascriptExecutor) driver).executeScript("arguments[0].click();", downloadLink);
waitUntilFileIsDownloaded(downloadPath, "Mughees", 30); // Replace "expected_file_name" with the actual file name pattern or extension
    }
    
    
        private void waitUntilFileIsDownloaded(String downloadPath, String fileName, int timeoutInSeconds) {
    File dir = new File(downloadPath);
    new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until((ExpectedCondition<Boolean>) driver -> {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files found in the directory.");
            return false;
        }
        for (File file : files) {
            System.out.println("Found file: " + file.getName());
            if (file.getName().startsWith(fileName) && Files.isRegularFile(Paths.get(file.getAbsolutePath()))) {
                return true;
            }
        }
        return false;
    });
    
    }
        
     @Test
    public void testRefreshConsistency() {
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

List<WebElement> OngoingRequests = new WebDriverWait(driver, Duration.ofSeconds(10))
    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button.buttons-hover")));

WebElement newReq = OngoingRequests.get(1);
((JavascriptExecutor) driver).executeScript("arguments[0].click();", newReq);    
    
    List<WebElement> requestsBeforeRefresh = driver.findElements(By.xpath("//p[contains(text(), 'Request Type')]"));
    int countBeforeRefresh = requestsBeforeRefresh.size();

    // Refresh the page
    driver.navigate().refresh();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Request Type')]")));

    // Get the list of ongoing requests after refresh
    List<WebElement> requestsAfterRefresh = driver.findElements(By.xpath("//p[contains(text(), 'Request Type')]"));
    int countAfterRefresh = requestsAfterRefresh.size();

    // Compare the counts before and after refresh
    assertEquals("The number of ongoing requests should remain consistent after refresh.", countBeforeRefresh, countAfterRefresh);
    }
    
    
    
        @Test
    public void noOngoing() {
        
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

    emailInput.sendKeys("l21@nu.edu.pk");
    passwordInput.sendKeys("123");
    loginButton.click();
 
    
          List<WebElement> ongoingRequests = new WebDriverWait(driver, Duration.ofSeconds(10))
    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button.buttons-hover")));

WebElement newReq = ongoingRequests.get(1);
((JavascriptExecutor) driver).executeScript("arguments[0].click();", newReq);


    // Check for the "No Request Found" message
List<WebElement> noRequestsMessage = driver.findElements(By.xpath("//h1[contains(text(), 'No Request Found')]"));
    
    // If the message is present, there are no rejected requests, and the test is successful
    if (!noRequestsMessage.isEmpty()) {
        // Optionally, include an assertion here to confirm the "No Request Found" message
        // Example: assertTrue(noRequestsMessage.get(0).isDisplayed());
        System.out.println("No requests found. Test successful.");
        return;
    }
    
   
    }

    @After
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
    
}
