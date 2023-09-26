package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Zadatak3 {

//    Napisati program koji:
//    Ucitava stranicu https://demoqa.com/text-box
//    Unosi informacije za 3 osobe koristeci petlju
//    Full Name
//    Email
//    Current Address
//    Permanent Address
//    Klik na submit
//    Ceka 2 sekunde
//    Osvezava stranicu
//    Zatvara pretrazivac

    public static void main(String[] args) throws InterruptedException {
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        ArrayList<String> names = new ArrayList<>();
        names.add("Milan Jovanovic");
        names.add("Vladimir Minic");
        names.add("Ana Laganin");

        ArrayList<String> emails = new ArrayList<>();
        emails.add("milan@milanjovanovic.com");
        emails.add("valdimir@vladimirminic.com");
        emails.add("ana@analaganin.com");

        ArrayList<String> currentAddress = new ArrayList<>();
        currentAddress.add("Negde u Nisu 1");
        currentAddress.add("Negde u Nisu 2");
        currentAddress.add("Moja trenutna adresa 33");

        ArrayList<String> permanentAddress = new ArrayList<>();
        permanentAddress.add("Ne znam gde 1");
        permanentAddress.add("Znam da nije iz Nisa 1");
        permanentAddress.add("Moja stalna adresa 23");



        driver.navigate().to("https://demoqa.com/text-box");
        for (int i = 0; i < names.size(); i++) {
            driver.findElement(By.id("userName")).clear();
            driver.findElement(By.id("userName")).sendKeys(names.get(i));
            driver.findElement(By.id("userEmail")).clear();
            driver.findElement(By.id("userEmail")).sendKeys(emails.get(i));
            driver.findElement(By.id("currentAddress")).clear();
            driver.findElement(By.id("currentAddress")).sendKeys(currentAddress.get(i));
            driver.findElement(By.id("permanentAddress")).clear();
            driver.findElement(By.id("permanentAddress")).sendKeys(permanentAddress.get(i));
            driver.findElement(By.id("submit")).click();
            Thread.sleep(2000);
            driver.navigate().refresh();
        }
        driver.quit();
    }
}
