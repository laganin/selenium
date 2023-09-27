package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak5 {

//Napisati program koji ucitava stranicu http://cms.demo.katalon.com/my-account/, cekira Remember me checkbox.
//Posto ne radi sajt, probajte funkcionalnost checkbox-a na ovom sajtu https://demoqa.com/automation-practice-form
//Na kraju programa proverite da li je element cekiran. Izguglajte kako mozemo da proverimo da li je element cekiran.

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://demoqa.com/automation-practice-form");
        driver.findElement(By.cssSelector("#hobbiesWrapper div div")).click();
        boolean isChecked = driver.findElement(By.id("hobbies-checkbox-1")).isSelected();
        if (isChecked) {
            System.out.println("Checkbox is checked");
        } else System.out.println("Checkbox is not checked.");
        Thread.sleep(5000);
        driver.quit();
    }
}
