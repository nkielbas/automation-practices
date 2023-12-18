import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class RegistrationAndLogin {
    WebDriver driver = new ChromeDriver();

    @BeforeEach
    public void before (){
        driver.get("https://www.automationpractice.pl/");

        WebElement SingInButton = driver.findElement(By.cssSelector("[class='header_user_info']"));
        SingInButton.click();
        Assertions.assertTrue(driver.getCurrentUrl().equals("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account"), "Incorrect URL");
    }

    @AfterEach
    public void after () {
      driver.quit();
    }

    @Test
    public void Registration() {

        //create random email to registration
        RandomStringGenerator email = new RandomStringGenerator();
        String EmailForRegistration = email.generateRandomString(6) + "@nktest.pl";

        driver.findElement(By.id("email_create")).sendKeys(EmailForRegistration);
        driver.findElement(By.id("SubmitCreate")).click();

        //wait for clickable gender button
        Duration waitTime = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender1")));

        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("nktestname");
        driver.findElement(By.id("customer_lastname")).sendKeys("nktestlastname");
        driver.findElement(By.id("passwd")).sendKeys("nktestpass");
        driver.findElement(By.id("submitAccount")).click();

        Assertions.assertTrue(driver.findElement(By.xpath("//p[contains(@class, 'alert-success')]")).isDisplayed(), "The success alert is not displayed");
    }
    @Test
    public void Login(){

        driver.findElement(By.id("email")).sendKeys("nktest@test.pl");
        driver.findElement(By.id("passwd")).sendKeys("nktestpass");
        driver.findElement(By.id("SubmitLogin")).click();

        Assertions.assertTrue(driver.getCurrentUrl().equals("http://www.automationpractice.pl/index.php?controller=my-account"), "Incorrect URL");
    }

}




