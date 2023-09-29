package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak1 {

//Napisati program koji ucitava stranicu https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort=
//Klik na Type drawdown
//Klik na Public iz drowdowna
//Ceka da se Clear dugme u desnom uglu prikaze koristeci explicit wait
//Kilk na Clear filter u desnom uglu


    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort=");
        driver.findElement(By.cssSelector("#type-options summary.btn")).click();
        driver.findElement(By.cssSelector("#type-options > details-menu > div > div > label:nth-child(2)")).click();
        wait
                .withMessage("Button has not appeared")
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText("Clear filter")));
        driver.findElement(By.linkText("Clear filter")).click();
        driver.quit();
    }
}
