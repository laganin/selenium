package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Zadatak5 {

//Napisati program koji:
//Ucitava stranicu https://blueimp.github.io/jQuery-File-Upload/
//Uploaduje sve cetiri slike odjenom (slike iz prvog zadatka)
//Ceka da se prikazu 4 item-a a upload
//Klik na upload
//Ceka da se upload zavrsi


    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        File uploadFile = new File("test_data/artplayer_00_03.png");

        driver.navigate().to("https://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.cssSelector("[type='file']")).sendKeys(uploadFile.getAbsolutePath());
        driver.findElement(By.cssSelector("[type='file']")).sendKeys(uploadFile.getAbsolutePath());
        driver.findElement(By.cssSelector("[type='file']")).sendKeys(uploadFile.getAbsolutePath());
        driver.findElement(By.cssSelector("[type='file']")).sendKeys(uploadFile.getAbsolutePath());

        wait
                .withMessage("No files uploaded.")
                .until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tbody tr"), 4));
        driver.findElement(By.cssSelector("[type='submit']")).click();
        wait
                .withMessage("No files uploaded.")
                .until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tbody [type='checkbox']"), 4));
        driver.quit();
    }
}
