package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak2 {

//    Napisati program koji ucitava stranicu https://youtube.com i u search baru unosi tekste Breskvica i ceka da se pojavi vise od 3 rezultata iz padajuceg menija i zatim klikce na prvi.

    public static void main(String[] args) throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://youtube.com");
        driver.findElement(By.cssSelector("input#search")).click();
        driver.findElement(By.cssSelector("input#search")).sendKeys("Breskvica");
        Thread.sleep(2000);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("ul.sbsb_b li"), 3));
        driver.findElement(By.cssSelector("ul.sbsb_b li:first-child")).click();
        driver.quit();
    }
}
