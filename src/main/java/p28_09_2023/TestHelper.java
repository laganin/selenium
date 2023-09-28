package p28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

//Kreirati klasu TestHelper koja ima:
//privatan atribut driver
//kontukstor sa parametrom
//metodu elementExists koja vraca true ili false ako element postoji. Metoda kao parametar prima nacin trazenja By objekat. Metoda radi proveru preko TryCatch-a
//metodu elementExistsByList koja takodje vraca true ili false. Metoda kao parametar prima By objekat za trazenje.
//metodu setDefaultImplicitWait koja postavlja implicino cekanje na 10s.
//metodu setImplicitWait koja postavlja implicitno cekanje iz prosledjene vrednosti.
//
//U glavnom programu resiti prvi zadatak koriseci objekat klase TestHelper za proveru elemenata i za postavljanje implicitnog cekanja.

public class TestHelper {

    private WebDriver driver;

    public TestHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean elementExists(By object) {
        try {
            driver.findElement(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean elementExistsByList(By object) {
        return !driver.findElements(object).isEmpty();
    }

    public void setDefaultImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void setImplicitWait(int x) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(x));
    }
}
