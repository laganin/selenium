package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak5 {

//Ucitati stranicu http://seleniumdemo.com/?product=bdd-cucumber
//Klik na korpu iz gornjeg desnog ugla
//Sacekati da naslov stranice bude Cart – Selenium Demo Page
//Proveriti da li element koji prikazuje stanje korpe ima tekst Your cart is currently empty.

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("http://seleniumdemo.com/?product=bdd-cucumber");
        driver.findElement(By.cssSelector("li.nav__woocart a.woocart")).click();
        driver.findElement(By.cssSelector("li.nav__woocart a.woocart")).click();
        wait
                .withMessage("Page not loaded")
                .until(ExpectedConditions.titleIs("Cart – Selenium Demo Page"));

        String emptyCart = "Your cart is currently empty.";

        if (driver.findElement(By.className("woocommerce")).getText().contains(emptyCart)) {
            System.out.println("Contains");
        } else System.out.println("Does not contain");
        driver.quit();
    }
}
