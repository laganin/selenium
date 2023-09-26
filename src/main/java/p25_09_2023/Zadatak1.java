package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {

//    Napisati program koji:
//    Maksimizuje prozor
//    Ucitava stranicu https://cms.demo.katalon.com/
//    U delu za pretragu unosi tekst Flying Ninja.
//    Klikce na dugme za pretragu. Dugme sa lupom. Kada trazite element dohvatite element koji je po tagu button a ne span.
//            Ceka 5 sekuni
//    Zatvara pretrazivac


    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://cms.demo.katalon.com/");
        driver.findElement(By.className("search-field")).sendKeys("Flying Ninja");
        driver.findElement(By.className("search-submit")).click();
        Thread.sleep(5000);
        driver.quit();
    }
}
