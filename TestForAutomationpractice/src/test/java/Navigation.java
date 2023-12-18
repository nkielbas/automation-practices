
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


public class Navigation {

    WebDriver driver = new ChromeDriver();

    @BeforeEach
    public void before (){
        driver.get("https://www.automationpractice.pl/");
    }

    @AfterEach
    public void after () {
        driver.quit();
    }

    @Test
    public void GoToWomenCategory () {
        WebElement WomenCategory = driver.findElement(By.cssSelector("[class='sf-with-ul']"));
        WomenCategory.click();

        Assertions.assertTrue(driver.getCurrentUrl().contains("id_category=3"), "Incorrect URL");
    }

    @Test
    public void GoToTopsSubcategory () {
        WebElement WomenCategory = driver.findElement(By.cssSelector("[class='sf-with-ul']"));
        Actions action = new Actions(driver);
        action.moveToElement(WomenCategory).perform();
        WebElement TopsSubcategory = driver.findElement(By.xpath("//a[@title='Tops' and contains(@href, 'id_category=4')]"));
        TopsSubcategory.click();

        Assertions.assertTrue(driver.getCurrentUrl().contains("id_category=4"), "Incorrect URL");
    }

    @Test
    public void GoToBestsellerTab (){
        WebElement BestsellerTab = driver.findElement(By.xpath("//a[@class='blockbestsellers' and contains(@href, 'blockbestsellers')]"));
        BestsellerTab.click();

        //assertion
        WebElement ulElement = driver.findElement(By.id("home-page-tabs"));
        List<WebElement> liElements = ulElement.findElements(By.tagName("li"));
        for (WebElement li : liElements) {
            String classAttributeValue = li.getAttribute("class");
            if(classAttributeValue.contains("active")){
                WebElement aElement = li.findElement(By.tagName("a"));
                Assertions.assertTrue(aElement.getAttribute("class").contains("blockbestsellers"), "Incorret tab is active");
                break;
            }
        }
    }
}
