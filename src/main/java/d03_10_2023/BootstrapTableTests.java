package d03_10_2023;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;


//Kreirati BootstrapTableTests klasu koja ima:
//Base url: https://s.bootsnipp.com/iframe/K5yrx
//Test #1: Edit Row
//Podaci:
//First Name: ime polaznika
//Last Name: prezime polaznika
//Middle Name: srednje ime polanzika
//Koraci:
//Ucitati stranu /iframe/K5yrx
//Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//Klik na Edit dugme prvog reda
//Sacekati da dijalog za Editovanje bude vidljiv
//Popuniti formu podacima.
//Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, za to se koristi metoda clear. Koristan link
//Klik na Update dugme
//Sacekati da dijalog za Editovanje postane nevidljiv
//Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
//Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
//Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
//Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//Test #2: Delete Row
//Podaci:
//First Name: ime polaznika
//Last Name: prezime polaznika
//Middle Name: srednje ime polanzika
//Koraci:
//Ucitati stranu /iframe/K5yrx
//Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//Klik na Delete dugme prvog reda
//Sacekati da dijalog za brisanje bude vidljiv
//Klik na Delete dugme iz dijaloga
//Sacekati da dijalog za Editovanje postane nevidljiv
//Verifikovati da je broj redova u tabeli za jedan manji
//Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//Test #3: Take a Screenshot
//Koraci:
//Ucitati stranu  /iframe/K5yrx
//Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//Kreirati screenshot stranice.
//Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg. Na putanji: screenshots/slike.png

public class BootstrapTableTests {
    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String firstName = "Dragan";
        String lastName = "Torbica";
        String middleName = "Jove";
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.navigate().to(" https://s.bootsnipp.com/iframe/K5yrx");

        wait
                .withMessage("Title not as expected")
                .until(ExpectedConditions.titleContains("Table with Edit and Update Data - Bootsnipp.com"));
    }

    @Test
    public void editRow() {
        String firstName = "Dragan";
        String lastName = "Torbica";
        String middleName = "Jove";

        driver.findElement(By.cssSelector("tr:nth-child(1) button.update")).click();

        wait
                .withMessage("Modal not visible")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.className("modal-backdrop"))));

        driver.findElement(By.cssSelector("input#fn")).clear();
        driver.findElement(By.cssSelector("input#fn")).sendKeys(firstName);

        driver.findElement(By.cssSelector("input#ln")).clear();
        driver.findElement(By.cssSelector("input#ln")).sendKeys(lastName);

        driver.findElement(By.cssSelector("input#mn")).clear();
        driver.findElement(By.cssSelector("input#mn")).sendKeys(middleName);

        driver.findElement(By.cssSelector("button#up")).click();

        wait
                .withMessage("Modal visible")
                .until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("modal-backdrop"))));

        Assert.assertEquals(
                driver.findElement(By.cssSelector("td#f1")).getText(),
                firstName,
                "First name not as expected");

        Assert.assertEquals(
                driver.findElement(By.cssSelector("td#l1")).getText(),
                lastName,
                "Last name not as expected");

        Assert.assertEquals(
                driver.findElement(By.cssSelector("td#m1")).getText(),
                middleName,
                "Middle name not as expected");
    }

    @Test
    public void deleteRow() {
        String firstName = "Dragan";
        String lastName = "Torbica";
        String middleName = "Jove";

        List<WebElement> cells = driver.findElements(By.cssSelector("tbody tr td"));

        driver.findElement(By.cssSelector("tr:nth-child(1) button.delete")).click();

        wait
                .withMessage("Modal visible")
                .until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("modal-backdrop"))));

        driver.findElement(By.cssSelector("button#del")).click();

        wait
                .withMessage("Modal visible")
                .until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("modal-backdrop"))));

        wait
                .withMessage("Number of table rows has not changed.")
                .until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tbody tr td"), cells.size()-6));
    }

    @Test
    public void takeAScreenshot() throws IOException {
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(f, new File("screenshots/image.jpg"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
