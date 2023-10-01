package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak4 {

//Napisati program koji
//ucitava stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//Klik Primary dugme
//Ceka da se pojavi toasts u gornjem desnom uglu
//Ispisuje da se element pojavio
//Ceka da se izgubi toasts u gornjem desnom uglu
//Ispisuje da se elment izgubio
//Klik na primary dugme
//Ceka da se pojavi toasts u gornjem desnom uglu
//Ispisuje da se element pojavio
//Klik na x dugme iz toasts-a
//Ceka da se element izgubi
//Ispisuje da se element izgubio

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");

        driver.findElement(By.id("basic-primary-trigger")).click();
        wait
                .withMessage("Toast has not appeared")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class, 'toast fade')]"))));
        System.out.println("Element has appeared");

        wait
                .withMessage("Toast still visible")
                .until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class, 'toast fade')]"))));
        System.out.println("Element has disappeared");

        driver.findElement(By.id("basic-primary-trigger")).click();
        wait
                .withMessage("Toast has not appeared")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class, 'toast fade')]"))));
        System.out.println("Element has appeared");

        driver.findElement(By.cssSelector("div.toast-primary button.btn-close")).click();

        wait
                .withMessage("Toast still visible")
                .until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class, 'toast fade')]"))));
        System.out.println("Element has disappeared");

        driver.quit();
    }
}
