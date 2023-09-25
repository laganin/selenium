package d22_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {

        ArrayList<String> URL = new ArrayList<>();

        URL.add("https://www.google.com/");
        URL.add("https://www.facebook.com/");
        URL.add("https://www.youtube.com/");
        URL.add("https://www.ebay.com/");
        URL.add("https://www.katalon.com/");

        WebDriverManager.chromedriver().setup();

        for (int i = 0; i < URL.size(); i++) {
            WebDriver driver = new ChromeDriver();
            driver.get(URL.get(i));
            Thread.sleep(1000);
            driver.quit();
        }
    }
}
