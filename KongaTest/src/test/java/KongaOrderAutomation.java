import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class KongaOrderAutomation {
    private WebDriver driver;

    @Test
    public void setup() throws InterruptedException {
        // Set up WebDriver for Chrome
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        Thread.sleep( 10000);
        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // Visit Konga URL
            driver.get("https://www.konga.com");
            // Maximize the window
            driver.manage().window().maximize();



            // Sign in to Konga

                //driver.findElement(By.linkText("Login / Sign Up")).click();
                // Explicit wait for the "Login / Sign Up" button to be visible
                 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")
                ));

                // Click the "Login / Sign Up" button
                loginLink.click();
                driver.findElement(By.id("username")).sendKeys("khodijatorire@gmail.com"); // Replace with your email
                driver.findElement(By.id("password")).sendKeys("oritokeabiks1993"); // Replace with your password
                driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
                // Wait until login is successful
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.urlContains("dashboard"));


                // Navigate to Computers and Accessories
                WebElement categories = driver.findElement(By.linkText("Categories"));
                Actions actions = new Actions(driver);
                actions.moveToElement(categories).perform();
                driver.findElement(By.linkText("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();

                // Click on Laptop SubCategory
                driver.findElement(By.linkText("Laptops")).click();

                // Click on Apple MacBooks
                driver.findElement(By.partialLinkText("Apple MacBooks")).click();

                // Add an item to the cart
                driver.findElement(By.xpath("//button[contains(text(), 'Add to cart')]")).click();

                // Proceed to Checkout
                driver.findElement(By.xpath("//button[contains(text(), 'Checkout')]")).click();

                // Select Address
                driver.findElement(By.xpath("//button[contains(text(), 'Select Address')]")).click();

                // Continue to Payment
                driver.findElement(By.xpath("//button[contains(text(), 'Continue to Payment')]")).click();

                // Select Card Payment Method
                driver.findElement(By.xpath("//button[contains(text(), 'Card')]")).click();

                // Switch to iframe for card payment
                WebElement iframe = driver.findElement(By.tagName("iframe"));
                driver.switchTo().frame(iframe);

                // Input invalid card details
                driver.findElement(By.id("cardNumber")).sendKeys("1234 5678 9012 3456");
                driver.findElement(By.id("expiryDate")).sendKeys("12/25");
                driver.findElement(By.id("cvv")).sendKeys("123");
                driver.findElement(By.xpath("//button[contains(text(), 'Pay')]")).click();

                // Print out the error message
                WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(), 'Invalid card number')]"));
                System.out.println("Error Message: " + errorMessage.getText());

                // Close the iFrame
                driver.switchTo().defaultContent();

                // Quit the browser
                driver.quit();

            } catch (Exception e) {
                e.printStackTrace();
                driver.quit();
            }


        }
        @AfterTest
        public void closeBrowser(){
            //quit the browser
            driver.quit();
        }
    }
