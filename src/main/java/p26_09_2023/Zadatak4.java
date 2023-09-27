package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class Zadatak4 {

//Zadatak (dok ne stignemo do ovog zadatka izguglajte kako da selektujete vrednost u select elementu)
//
//Napisati program koji ucitava stranicu https://www.ebay.com/ i bira kategoriju “Crafts”


    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://www.ebay.com/");
        Select category = new Select(driver.findElement(By.id("gh-cat")));
        category.selectByValue("14339");
        Thread.sleep(2000);
        driver.quit();
    }
}
