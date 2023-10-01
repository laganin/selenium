package d29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak3 {

//Napisati program koji:
//Pre nego sto krenete u automatizaciju prvo sve korake uradite rucno
//Implicitno cekanje za trazenje elemenata je maksimalno 10s
//Implicitno cekanje za ucitavanje stranice je 5s
//Ucitava stranicu https://docs.katalon.com/
//Maksimizuje prozor
//Od html elementa cita data-theme atribut.
//Proverava da li je sadrzaj u tom atributu light i ispisuje odgovarajuce poruke
//Klikce na dugme za zamenu tema
//Ponovo cita data-theme atribut html elementa i validira da u atributu stoji vrednost dark
//Izvrsava kombinaciju tastera CTRL + K. Koristan link  za keyboard actions…kako izvrsavati precice preko Actions objekta
//Ceka da se dijalog za pretragu pojavi
//Zatim od inputa za pretragu cita atribut type i proverava da je vrednost tog atributa search
//Zatvara pretrazivac

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://docs.katalon.com/");

        WebElement html = driver.findElement(By.xpath("html"));

        if (html.getAttribute("data-theme").equals("light")) {
            System.out.println("The theme used is light.");
        } else System.out.println("The theme used is dark.");

        driver.findElement(By.className("toggleButton_rCf9")).click();

        if (html.getAttribute("data-theme").equals("dark")) {
            System.out.println("The theme used is dark.");
        } else System.out.println("The theme used is light.");

        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .sendKeys("k")
                .keyUp(Keys.CONTROL)
                .perform();
        wait
                .withMessage("Modal not visible")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("DocSearch-Modal")));

        WebElement searchInput = driver.findElement(By.id("docsearch-input"));

        if (searchInput.getAttribute("type").equals("search")) {
            driver.quit();
        }
    }
}
