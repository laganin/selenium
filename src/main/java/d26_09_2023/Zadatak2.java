package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Zadatak2 {

//    Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//    Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//    POMOC: Brisite elemente odozdo.
//            (ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://s.bootsnipp.com/iframe/Dq2X");
        List<WebElement> alerts = driver.findElements(By.xpath("//*[contains(@class, 'alert')]"));
        for (int i = 0; i < alerts.size(); i++) {
            int newSize = driver.findElements(By.xpath("//*[contains(@class, 'alert')]")).size();
            alerts.get(i).findElement(By.className("close")).click();
            List<WebElement> newAlerts = driver.findElements(By.xpath("//*[contains(@class, 'alert')]"));
            if (newSize - newAlerts.size()==1){
                System.out.println("Element removed");
            } else System.out.println("Element not removed");
        }
        driver.quit();


    }
}
