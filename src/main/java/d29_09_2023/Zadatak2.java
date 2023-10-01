package d29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html");
        Select delay = new Select(driver.findElement(By.id("delay-select")));
        delay.selectByValue("2000");

        List<WebElement> boxes = driver.findElements(By.cssSelector("div.item"));

        for (int i = 0; i <boxes.size() ; i++) {
            new Actions(driver).scrollToElement(boxes.get(i)).perform();
            Thread.sleep(2000);
        }

        driver.findElement(By.cssSelector("span.active-text")).click();

        wait.until(ExpectedConditions
                .numberOfElementsToBe(By.xpath("//div[@id='infinite-scroll-container']/div[@class='item']"), 8));
        wait
                .withMessage("Elements not loaded.")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.disabled-text"), "Loading more items..."));
        driver.quit();

    }
}
