package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak5 {

//Napisati program koji:
//Ucitava stranicu https://mdbootstrap.com/docs/standard/components/toasts/
//Vrsi klik na Basic example link iz desne navigacije
//Ceka da url sadrzi #section-basic-example


    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://mdbootstrap.com/docs/standard/components/toasts/");
        driver.findElement(By.xpath("//a[text()='Basic example']")).click();
        wait
                .withMessage("URL has not changed")
                .until(ExpectedConditions.urlToBe("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example"));

        driver.quit();
    }
}
