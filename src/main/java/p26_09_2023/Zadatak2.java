package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadatak2 {

//Napisti program koji:
//Ucitava stranicu https://s.bootsnipp.com/iframe/z80en
//Hvata sve elemente prve kolone i stampa tekst svakog elementa. Kako da od nekog elementa procitamo tekst imate na sledecem linku
//Ceka 1s
//Hvata sve elemente prvog reda i stampa tekst svakog elementa
//Ceka 5s
//Zatvara pretrazivac

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://s.bootsnipp.com/iframe/z80en");

        System.out.println(driver.findElement(By.cssSelector("#lorem th:first-child")).getText());
        List<WebElement> firstColumn = driver.findElements(By.cssSelector("#lorem td:first-child"));
        for (int i = 0; i < firstColumn.size(); i++) {
            System.out.println(firstColumn.get(i).getText());
        }

        Thread.sleep(1000);

        List<WebElement> firstRow = driver.findElements(By.cssSelector(" #lorem tr:first-child th"));
        for (int i = 0; i < firstRow.size(); i++) {
            System.out.println(firstRow.get(i).getText());
        }

        Thread.sleep(5000);
        driver.quit();
    }
}
