package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak4 {

//    Napisati program koji:
//Maksimizuje stranicu
//Ucitava stranicu https://demoqa.com/webtables
//Vrsi klik na edit dugme prvog reda
//Unosi informacije za sva polja u edit formi
//Klik na submit
//Ceka par sekundi
//Zatvara pretrazivac

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://demoqa.com/webtables");
        driver.findElement(By.id("edit-record-1")).click();
        driver.findElement(By.id("firstName")).clear();
        driver.findElement(By.id("firstName")).sendKeys("Milan");
        driver.findElement(By.id("lastName")).clear();
        driver.findElement(By.id("lastName")).sendKeys("Jovanovic");
        driver.findElement(By.id("userEmail")).clear();
        driver.findElement(By.id("userEmail")).sendKeys("milan@milanjovanovic.net");
        driver.findElement(By.id("salary")).clear();
        driver.findElement(By.id("salary")).sendKeys("1000");
        driver.findElement(By.id("department")).clear();
        driver.findElement(By.id("department")).sendKeys("IT");
        driver.findElement(By.id("submit")).click();
        Thread.sleep(2000);
        driver.quit();
    }
}
