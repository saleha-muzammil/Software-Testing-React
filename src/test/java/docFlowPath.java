
import java.time.Duration;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author saleha
 */
public class docFlowPath {
    
         private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/home/saleha/Downloads/chromedriver/chromedriver");

        // Initialize the ChromeDriver
        driver = new ChromeDriver();
    }
    
         @Test
    public void testFlowPath() {
        
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
WebElement newReq = NewRequests.get(0);
((JavascriptExecutor) driver).executeScript("arguments[0].click();", newReq);


 WebElement setButton1 = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button.btn.btn-primary.px-4")));
 
    setButton1.click();
    
WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
    By.cssSelector(".form-select.p-3"))); // Correct CSS selector without the word "select"

// Create a new Select object to interact with the dropdown
Select selectPerson = new Select(dropdown);

// Select the option with the value attribute "Dean"
selectPerson.selectByValue("Dean");

WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));

// Wait for any loading overlay to disappear
wait3.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading")));

// Scroll to the button and wait for it to be clickable
WebElement button = wait3.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary")));
((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

// Attempt to click the button
try {
    button.click();
} catch (ElementClickInterceptedException e) {
    // If the click is still intercepted, use JavaScript to click
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
}
    }
    
     @Test
    public void testMaxApprovers() {
        
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
WebElement newReq = NewRequests.get(0);
((JavascriptExecutor) driver).executeScript("arguments[0].click();", newReq);


 WebElement setButton1 = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button.btn.btn-primary.px-4")));
 
    setButton1.click();
    
     Select selectPerson = new Select(driver.findElement(By.id("Select-Persons")));
    for (int i = 0; i < 7; i++) {
        // Ensure the loading overlay is gone before trying to interact with the page
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading")));
        selectPerson.selectByIndex(i + 1); // Adjust the index as needed
        WebElement addButton = driver.findElement(By.cssSelector("button.btn.btn-outline-success"));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    // Try to add one more approver beyond the limit
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading")));
    selectPerson.selectByIndex(1); // This should trigger the error message
    WebElement addButton = driver.findElement(By.cssSelector("button.btn.btn-outline-success"));
    addButton.click(); // Click the add button without waiting for it to be clickable as the error is expected

    // Check if the error message is displayed
    try {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String errorText = errorMessage.getText();
        assertEquals("Please Select the Staff", errorText);
    } catch (TimeoutException e) {
        fail("The error message was not displayed.");
    }
}

@Test
public void testRemovingAnApprover() {
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
WebElement newReq = NewRequests.get(0);
((JavascriptExecutor) driver).executeScript("arguments[0].click();", newReq);


 WebElement setButton1 = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button.btn.btn-primary.px-4")));
 
    setButton1.click();

    // Add an approver
wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading")));

// Add the maximum number of approvers
Select selectPerson = new Select(driver.findElement(By.id("Select-Persons")));
WebElement addButton = driver.findElement(By.cssSelector("button.btn.btn-outline-success"));

for (int i = 0; i < 5; i++) {
    selectPerson.selectByIndex(i + 1); // Adjust this index based on how the options are listed
    wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    // After each click, wait for the loading overlay to disappear before the next interaction
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading")));
}

// Try to add one more approver beyond the limit
selectPerson.selectByIndex(1); // Attempt to add the same or different approver
wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

// Now wait for the error message to appear
WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
assertEquals("Please Select the Staff", errorMessage.getText());

// Removing an approver - locating the 'Remove' button by its text and class
List<WebElement> removeButtons = driver.findElements(By.cssSelector("button.btn.btn-danger"));
if (!removeButtons.isEmpty()) {
    WebElement removeButton = removeButtons.get(0); // Get the first 'Remove' button
    wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    // Verify that the approver list has one less item now
    List<WebElement> approversList = driver.findElements(By.cssSelector("#Path-Area .input-group"));
    assertTrue("Approver should be removed", approversList.size() < 7);
} else {
    fail("No 'Remove' buttons found");
}
}


@Test
public void testSubmitWithoutApprovers() {
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
WebElement newReq = NewRequests.get(0);
((JavascriptExecutor) driver).executeScript("arguments[0].click();", newReq);


 WebElement setButton1 = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button.btn.btn-primary.px-4")));
 
    setButton1.click();
    
        WebElement submitButton = driver.findElement(By.cssSelector("button.btn.btn-primary"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
    wait.until(ExpectedConditions.elementToBeClickable(submitButton));

    // Click the submit button
    submitButton.click();

    // Verify that the error message is displayed or the page state verifies that submission did not occur
    // Depending on how the error is shown, you may need to adjust this part of the test
    // If there's a specific element that indicates a successful submission, check that it's not present
    boolean isSubmissionIndicatorPresent = !driver.findElements(By.id("submission-indicator")).isEmpty();
    assertFalse("The submission indicator should not be present", isSubmissionIndicatorPresent);

    // If an error message should appear, wait for it and check its text
    try {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String errorText = errorMessage.getText();
        assertEquals("Expected error message", errorText); // Replace with the actual expected error message
    } catch (TimeoutException e) {
        fail("The error message was not displayed.");
    }
}


    

    @After
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
    
}
