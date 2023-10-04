package d02_10_2023;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import p02_10_2023.Helper;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Zadatak4 {

//Napisati program koji:
//Ucitava stranicu https://itbootcamp.rs/
//Skrola do slajdera na dnu stranice (u kome su slike Java, Php, Selenium, …)
//Cita sve linkove slika iz slajdera
//Proverava url svake slike, i za sve slike koje imaju status veci i jednak od 200 a manji od 300, skida i smesta u folder itbootcamp_slider u okviru projekta
//Azurirajte gitignore da ignorise itbootcamp_slider folder


    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://itbootcamp.rs/");

        new Actions(driver).scrollToElement(driver.findElement(By.className("slider_bkgd"))).perform();

        List<WebElement> logos = driver.findElements(By.cssSelector("div.owl-item img"));

        for (int i = 0; i < logos.size(); i++) {
            URL imageLink = new URL(logos.get(i).getAttribute("src"));
            String URL = logos.get(i).getAttribute("src");
            HttpURLConnection http = (HttpURLConnection) imageLink.openConnection();
            int statusCode = http.getResponseCode();
            if (statusCode >= 200 & statusCode < 300) {
                Helper.downloadUsingStream(URL, "itbootcamp_slider/image" + i + ".png");
            }
        }
        driver.quit();
    }
}
