package p29_09_2023;

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
import java.util.List;

public class Zadatak3 {

//Napisati program implementira search test case za task-ove:
//Ucitati stranicu https://s.bootsnipp.com/iframe/8dqr
//Klik na filter dugme iz Tasks tabele
//Ceka da polje za input bude vidljivo. (Postaviti odgovarajuce poruke u slucaju greske)
//Za pretragu unosi tekst za koji nema rezultata pretrage npr: dsdsdsds
//Ceka da se pojavi No results found red i proverava ispisanu poruku da li je tekst “No results found”
//Za pretragu unosi sledeci tekst mi
//Validira da red No results found  vise ne postoji
//Validira rezultate pretrage 🔥
//Pravila pretrage:
//Red ce biti u rezultatu ukoliko bar jedna kolona tog reda sadrzi termin pretrage.
//Pretraga nije case sensitive, sto znaci da radi i za velika i mala slova.
//Ispisuje odgovarajuce poruke
//Klik na filter dugme
//Ceka da polje za pretragu postane nevidljivo. (Postaviti odgovarajuce poruke u slucaju greske)

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://s.bootsnipp.com/iframe/8dqr");
        driver.findElement(By.cssSelector("div.panel-success  span.filter")).click();
        wait
                .withMessage("Field not visible.")
                .until(ExpectedConditions.attributeContains(By.cssSelector("body > div > div > div:nth-child(2) > div > div.panel-body"), "style", "display: block;"));

        driver.findElement(By.id("task-table-filter")).sendKeys("dsdsdsds");

        wait
                .withMessage("Element not visible.")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("tr.filterTable_no_results"))));
        if (driver.findElement(By.cssSelector("tr.filterTable_no_results td")).getText().equals("No results found")) {
            System.out.println("All is well.");
        }

        driver.findElement(By.id("task-table-filter")).click();
        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .keyDown(Keys.BACK_SPACE)
                .perform();

        driver.findElement(By.id("task-table-filter")).sendKeys("mi");
        Thread.sleep(2000);

        wait
                .withMessage("Element visible.")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("tr.filterTable_no_results")));

        List<WebElement> content = driver.findElements(By.cssSelector("[style='display: table-row;']"));
        for (int i = 0; i < content.size(); i++) {
            if (content.get(i).getText().toLowerCase().contains("mi".toLowerCase())) {
                System.out.println("Results are valid");
            }
        }

        driver.findElement(By.cssSelector("div.panel-success  span.filter")).click();
        wait
                .withMessage("Field is visible.")
                .until(ExpectedConditions.attributeContains(By.cssSelector("body > div > div > div:nth-child(2) > div > div.panel-body"), "style", "display: none;"));

        driver.quit();
    }
}
