import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class KongaOrderAutomationTestNG {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to Konga
        driver.get("https://www.konga.com");
    }

    @Test(priority = 1)
    public void signIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")
        ));

        // Click the "Login / Sign Up" button
        loginLink.click();
        driver.findElement(By.id("username")).sendKeys("khodijatorire@gmail.com"); // Replace with your email
        driver.findElement(By.id("password")).sendKeys("oritokeabiks1993"); // Replace with your password
        driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
        //wait.until(ExpectedConditions.urlContains("dashboard"));
        WebElement myAccount = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='nav-bar-fix']/div[1]/div/div/div[4]/div/a/span")));
        System.out.println("Login successful! 'My Account' is visible.");    }

    @Test(priority = 2)
    public void navigateToAppleMacBooks() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement category = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")
        ));
        category.click();
        driver.findElement(By.linkText("Laptops")).click();
        Thread.sleep(10000);

        WebElement macBook = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li/a/ul/li[1]/a/label/span")
        ));
        macBook.click();
        Thread.sleep(1000);

        //driver.findElement(By.partialLinkText("Apple MacBooks")).click();
    }

    @Test(priority = 3)
    public void addToCartAndCheckout() throws InterruptedException {
        //add to cart
        WebElement addTocCart = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[1]/article/div[2]/form/div[2]/button")
        ));
        addTocCart.click();
       // checkout
        WebElement myCart = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]/span[1]")
        ));
        myCart.click();

        WebElement checkout = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")
        ));
        checkout.click();
        //Thread.sleep(5000);
       // phoneInuput.click();
        // add address
       //WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")
      //));
      // address.click();

      // WebElement confirmaddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")
        //));
        //confirmaddress.click();

        Thread.sleep(10000);

    }

    @Test(priority = 4)
    public void testInvalidCardDetails() throws InterruptedException {

        WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span")
        ));
        address.click();
        Thread.sleep(10000);

        WebElement continueToPayment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")
        ));
        continueToPayment.click();
        Thread.sleep(10000);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
