package p28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Zadatak1 {

//    Ucitati stranicu https://demoqa.com/modal-dialogs
//Kliknuti na dugme Large modal
//Proverite da li se prikazao dijalog i ispisite u konzoli odgovarajuce poruke


    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://demoqa.com/modal-dialogs");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.id("showLargeModal")).click();

//        WebElement popUp = null;
//        try {
//            popUp = driver.findElement(By.className("modal-content"));
//        } catch (Exception e) {
//
//        }
//
//        if (popUp!= null) {
//            System.out.println("Postoji element");
//        } else {
//            System.out.println("Ne postoji element.");
//        }

        boolean isDialogOpen = true;
        try {
            driver.findElement(By.className("modal-content"));
        } catch (Exception e) {
            isDialogOpen = false;
        }

        if (isDialogOpen) {
            System.out.println("Dijalog se otvorio.");
        } else {
            System.out.println("Dijalog nije otvorio.");
        }

        driver.quit();
    }
}
