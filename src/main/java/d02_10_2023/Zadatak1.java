package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Zadatak1 {

//    Napisati program koji:
//Podesava:
//implicitno cekanje za trazenje elemenata od 10s
//implicitno cekanje za ucitavanje stranice od 10s
//eksplicitno cekanje podeseno na 10s
//Podaci:
//Potrebno je u projektu ukljuciti 4 slike:
//front.jpg
//left.jpg
//right.jpg
//back.jpg
//Koraci:
//Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
//Maksimizuje prozor
//Klik na edit ikonicu
//Klik na delete iz iskacuceg dijaloga
//Klik na Add Image dugme
//Sacekajte da se pojavi desni meni
//Uploadujte front.jpg sliku
//Sacekajte da je ispod uploada slike, broj slika 1.
//Klik na sliku
//Klik na Done dugme
//Sacekajte 2s
//Klik na Add Image dugme
//Sacekajte da se pojavi desni meni
//Uploadujte right.jpg sliku
//Sacekajte da je ispod uploada slike, broj slika 2.
//Klik na sliku
//Klik na Done dugme
//Sacekajte 2s
//Klik na Add Image dugme
//Sacekajte da se pojavi desni meni
//Uploadujte back.jpg sliku
//Sacekajte da je ispod uploada slike, broj slika 3.
//Klik na sliku
//Klik na Done dugme
//Sacekajte 2s
//Klik na Add Image dugme
//Sacekajte da se pojavi desni meni
//Uploadujte back.jpg sliku
//Sacekajte da je ispod uploada slike, broj slika 3.
//Klik na sliku
//Klik na Done dugme
//Sacekajte 2s
//Sacekajte da Next dugme bude klikljivo
//Klik na Next dugme
//Unesite tekst
//Klik na Next
//Klik na Preview
//Klik na Add to cart
//Sacekajte 5s
//Quit


    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        File front = new File("test_data/artplayer_00_03.png");
        File right = new File("test_data/artplayer_00_03.png");
        File back = new File("test_data/artplayer_00_03.png");
        File left = new File("test_data/artplayer_00_03.png");

        driver.navigate().to("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
        driver.findElement(By.className("edit-image")).click();
        driver.findElement(By.id("image-option-remove")).click();
        driver.findElement(By.className("edit-image")).click();
        wait
                .withMessage("Side menu not visible")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.className("sc-llcuoN"))));
        driver.findElement(By.id("imageUpload")).sendKeys(front.getAbsolutePath());
        wait
                .withMessage("Image not uploaded")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("image-option-0"))));
        driver.findElement(By.id("image-option-0")).click();
        driver.findElement(By.className("sc-beqWaB")).click();
        Thread.sleep(2000);

        driver.findElement(By.className("edit-image")).click();
        wait
                .withMessage("Side menu not visible")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.className("sc-llcuoN"))));
        driver.findElement(By.id("imageUpload")).sendKeys(right.getAbsolutePath());
        wait
                .withMessage("Image not uploaded")
                .until(ExpectedConditions.numberOfElementsToBe(By.className("sc-gLDzan"), 2));
        driver.findElement(By.id("image-option-1")).click();
        driver.findElement(By.className("sc-beqWaB")).click();
        Thread.sleep(2000);

        driver.findElement(By.className("edit-image")).click();
        wait
                .withMessage("Side menu not visible")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.className("sc-llcuoN"))));
        driver.findElement(By.id("imageUpload")).sendKeys(back.getAbsolutePath());
        wait
                .withMessage("Image not uploaded")
                .until(ExpectedConditions.numberOfElementsToBe(By.className("sc-gLDzan"), 3));
        driver.findElement(By.id("image-option-1")).click();
        driver.findElement(By.className("sc-beqWaB")).click();
        Thread.sleep(2000);

        driver.findElement(By.className("edit-image")).click();
        wait
                .withMessage("Side menu not visible")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.className("sc-llcuoN"))));
        driver.findElement(By.id("imageUpload")).sendKeys(left.getAbsolutePath());
        wait
                .withMessage("Image not uploaded")
                .until(ExpectedConditions.numberOfElementsToBe(By.className("sc-gLDzan"), 4));
        driver.findElement(By.id("image-option-1")).click();
        driver.findElement(By.className("sc-beqWaB")).click();
        Thread.sleep(2000);

        wait
                .withMessage("Button not clickable")
                .until(ExpectedConditions.elementToBeClickable(By.className("sc-gZsBWS")));
        driver.findElement(By.className("sc-ejDCVS")).click();
        driver.findElement(By.id("textareaID")).click();
        driver.findElement(By.id("textareaID")).sendKeys("text");
        driver.findElement(By.className("sc-ejDCVS")).click();
        driver.findElement(By.className("sc-ejDCVS")).click();
        driver.findElement(By.className("sc-ejDCVS")).click();
        driver.findElement(By.className("sc-ejDCVS")).click();
        Thread.sleep(5000);
        driver.quit();
    }
}
