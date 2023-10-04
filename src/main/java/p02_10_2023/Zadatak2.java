package p02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Zadatak2 {

//Napisati program koji:
//Ucitava stranu https://blueimp.github.io/jQuery-File-Upload/
//Uploadujte sliku
//Ceka se da se pojavi slika u listi uploadovanih fajlova
//Koristite uslov da broj elemenata bude 1.
//Klik na Start dugme u okviru item-a koji se uploadovao
//Ceka se da se pojavi delete dugme pored itema
//Klik na delete dugme pored itema
//Ceka se da se element obrise
//Koristite da broj elemenata bude 0


    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        File uploadFile = new File("test_data/artplayer_00_03.png");

        driver.navigate().to("https://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.cssSelector("[type='file']")).sendKeys(uploadFile.getAbsolutePath());

        wait
                .withMessage("No files uploaded.")
                .until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tbody tr"), 1));

        driver.findElement(By.cssSelector("tbody tr td button.start")).click();

        wait
                .withMessage("File not uploaded.")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[data-type='DELETE']"))));

        driver.findElement(By.cssSelector("[data-type='DELETE']")).click();

        wait
                .withMessage("File uploaded.")
                .until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("tbody tr"), 0));
        driver.quit();
    }
}
