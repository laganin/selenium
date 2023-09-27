package d25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak4 {

//Maksimizirati prozor
//Ucitati stranicu https://artplayer.org/
//U fokusu je player sa desne strane
//Ceka 3-4s
//Klik na play dugme
//Klik na na zvucnik za mute
//Ceka 3s
//Klik na screenshot
//Klik na PIP mode
//Ceka 1s
//Klik na Exit PIP mode
//Klik na WebFullscreen
//Klik na Exit WebFullscreen
//Cekanje od 5s
//Zatvorite pretrazivac


    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://artplayer.org/");
        Thread.sleep(4000);
        driver.findElement(By.className("art-control-playAndPause")).click();
        driver.findElement(By.cssSelector("div.art-control-volume")).click();
        Thread.sleep(3000);
        driver.findElement(By.className("art-control-screenshot")).click();
        driver.findElement(By.className("art-control-pip")).click();
        Thread.sleep(1000);
        driver.findElement(By.className("art-control-pip")).click();
        driver.findElement(By.className("art-control-fullscreenWeb")).click();
        driver.findElement(By.className("art-control-fullscreenWeb")).click();
        Thread.sleep(5000);
        driver.quit();
    }
}
