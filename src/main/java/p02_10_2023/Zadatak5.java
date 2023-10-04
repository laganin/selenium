package p02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.net.URL;

public class Zadatak5 {

//Napisati program koji:
//Ucitava stranicu https://demoqa.com/broken
//Hvata oba linka sa stranice i
//Za svaki link proverava status da je veci ili jednak od 200 i manji od 400
//Koristan link za citanje status koda nekog url-a

    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://demoqa.com/broken");
        List<WebElement> links = driver.findElements(By.cssSelector("div.col-12 a "));
        for (int i = 0; i < links.size(); i++) {
            URL url = new URL(links.get(i).getAttribute("href"));
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            int statusCode = http.getResponseCode();
            if (statusCode >= 200 & statusCode < 400) {
                System.out.println("Valid link.");
            } else System.out.println("Link is broken.");
        }
        driver.quit();
    }
}
