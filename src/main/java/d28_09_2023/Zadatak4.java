package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak4 {

//Ucitati stranicu http://seleniumdemo.com/?post_type=product
//Klik na search dugme u gornjem desnom uglu
//Cekati da forma za pretragu bude vidljiva
//Uneti sledeci tekst za pretragu BDD Cucumber i ENTER
//Dohvatiti prvi rezultat pretrage i proveriti da li u nazivu sadrzi tekst koji je unet za pretragu. Ispisati odgovarajuce poruke u terminalu


    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("http://seleniumdemo.com/?post_type=product");
        driver.findElement(By.cssSelector("div.topbar-navbar__wrapper a.icn-search")).click();
        wait
                .withMessage("Field has not appeared")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("s-651536d633c09")));

        String searchItem = "BDD Cucumber";

        driver.findElement(By.id("s-651536d633c09")).sendKeys(searchItem);
        driver.findElement(By.id("s-651536d633c09")).sendKeys(Keys.ENTER);

        wait
                .withMessage("No elements present")
                .until(ExpectedConditions.presenceOfElementLocated(By.className("grid__item")));

        if (searchItem.equals(driver.findElement(By.className("czr-title")).getText())) {
            System.out.println("Contains");
        } else System.out.println("Does not contain");

        driver.quit();
    }
}
