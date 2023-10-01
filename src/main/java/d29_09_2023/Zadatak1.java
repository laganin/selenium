package d29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak1 {

//Napisati program koji testira infinity scroll.
//Ucitati stranu https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html
//Selektujte delay od 2000ms, koristeci Select klasu.
//Skrol do Show more dugmeta koje se nalazi na dnu stranice
//Sacekajte da dugme bude klikljivo
//Klik na Show more dugme
//Sacekjate da broj elemenata bude X (X je koliko se kod vas ucitava)
//Sacekajte da dugme vise ne bude klikljivo


    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html");
        Select delay = new Select(driver.findElement(By.id("delay-select")));
        delay.selectByValue("2000");
        WebElement bottom = driver.findElement(By.cssSelector("div.footer"));
        WebElement element = driver.findElement(By.cssSelector("#infinite-scroll-container > div:nth-child(5)"));
        new Actions(driver).scrollToElement(element).perform();
        Thread.sleep(2000);
        new Actions(driver).scrollToElement(bottom).perform();
        driver.findElement(By.cssSelector("span.active-text")).click();

        wait
                .withMessage("New items not loaded")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='infinite-scroll-container']/div[@class='item']"), 8));
        wait
                .withMessage("Elements not loaded.")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.disabled-text"), "Loading more items..."));
        driver.quit();
    }
}
