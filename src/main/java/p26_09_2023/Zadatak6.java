package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak6 {

//Ucitati stranicu https://google.com
//Maksimizovati prozor
//Prostavite dimenzije prozora na 700px i visinu na 700px

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(700,700));

        driver.navigate().to("https://google.com ");
        driver.quit();
    }
}
