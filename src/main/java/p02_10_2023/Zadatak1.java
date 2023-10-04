package p02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Zadatak1 {

//Napisati program koji:
//Krairajte folder za fajlove u okviru projekta pod nazivom test_data
//U folder skinite i postavite proizvoljnu sliku
//Ucitava stranu https://tus.io/demo.html
//Skrola do dela za upload fajla
//Aploadujte sliku
//Cekajte da se pojava dugme za download fajla

//    DOPUNA ZA VEZBANJE
//    Dopuniti prvi zadatak tako da se skine uploadovan fajl i proveri da li je size skinutog i fajla sa sistema isti.



    public static void main(String[] args) throws IOException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        File uploadFile = new File("test_data/artplayer_00_03.png");

        driver.navigate().to("https://tus.io/demo.html");
        new Actions(driver).scrollToElement(driver.findElement(By.className("_root_gq6c0_1"))).perform();
        driver.findElement(By.id("P0-0")).sendKeys(uploadFile.getAbsolutePath());
        wait
                .withMessage("File not uploaded.")
                .until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.className("_root_gq6c0_1")), "The upload is complete!"));
        String imageURL = driver.findElement(By.className("_primary_gq6c0_58")).getAttribute("href");
        Helper.downloadUsingStream(imageURL, "downloads/test-image.jpg");
        File downloadFile = new File("downloads/test-image.jpg");
        if(uploadFile.length()==downloadFile.length()){
            System.out.println("Files are the same size");
        } else System.out.println("Files are not the same size.");
        driver.quit();
    }
}
