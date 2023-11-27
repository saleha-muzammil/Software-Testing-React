
import java.time.Duration;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
public class readDocument {
    
     private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/home/saleha/Downloads/chromedriver/chromedriver");

        // Initialize the ChromeDriver
        driver = new ChromeDriver();
    }
    @Test
    public void testReadDocument() {
        
        driver.get("http://localhost:3000");

// Log into the system as Assistant Registrar
WebElement assistantRegistrarButton = new WebDriverWait(driver, Duration.ofSeconds(10))
    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Asst.Registrar')]")));
((JavascriptExecutor) driver).executeScript("arguments[0].click();", assistantRegistrarButton);



    // Wait for the login modal to appear and enter credentials
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
    WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

    // Now using class names to locate the Login button
    WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button.btn.btn-primary.px-4.mt-2")));

    emailInput.sendKeys("sm@gmail.com");
    passwordInput.sendKeys("123");
    loginButton.click();

      List<WebElement> NewRequests = new WebDriverWait(driver, Duration.ofSeconds(10))
    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button.buttons-hover")));

// Assuming the third button is for the Assistant Registrar
WebElement newReq = NewRequests.get(1);
((JavascriptExecutor) driver).executeScript("arguments[0].click();", newReq);

WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
    List<WebElement> newRequests;

    try {
        newRequests = wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button.accordion-button")));
        if (newRequests.isEmpty()) {
            // No documents present, can perform other actions or exit
            System.out.println("No documents to read. Exiting.");
            return; // or driver.quit() to exit the browser if that's the end of the test
        }
    } catch (TimeoutException e) {
        // No documents were found within the given time
        System.out.println("No documents to read. Exiting.");
        return; // or driver.quit() to exit the browser if that's the end of the test
    }

    // If documents are present, click on the first one
    WebElement firstDocument = newRequests.get(0);
    firstDocument.click();

    }
    
    

    @After
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
    
    
}
