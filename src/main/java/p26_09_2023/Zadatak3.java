package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadatak3 {

//Napisti program koji:
//Ucitava stranicu https://s.bootsnipp.com/iframe/z80en
//Hvata sve elemente iz tabele i stampa tekst svakog elementa. Kako da od nekog elementa procitamo tekst imate na sledecem linku
//Ceka 5s
//Zatvara pretrazivac
//Stampa treba da bude kao u primeru:
//John	Doe	john@example.com
//Mary	Moe	mary@example.com
//July	Dooley	july@example.com
//
//HINT: bice vam lakse da radite ulancano trazenje. findElement().findELement()...


    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://s.bootsnipp.com/iframe/z80en");

        List<WebElement> headings = driver.findElements(By.cssSelector("#lorem th"));
        List<WebElement> content = driver.findElements(By.cssSelector("#lorem td"));
        int count = 0;
        for (int i = 0; i < content.size(); i++) {
            System.out.print(content.get(i).getText());
            System.out.print(" ");
            count++;
            if (count % headings.size() == 0) {
                System.out.println();
            }
        }
        Thread.sleep(5000);
        driver.quit();
    }
}
