package p02_10_2023;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Zadatak4 {

//Napisati program koji:
//Kreirati screenshots folder u projektu
//Ucitava stranicu https://google.com
//Kreira screenshot stranice
//Sacuvati screenshot u folderu screenshots pod imenom screenshot1.jpg
//Koristan link 1. LAKSE CE VAM BITI PREKO OVOG
//Koristan link 2
//Koristan link 3

    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://google.com");
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(f, new File("screenshots/screenshot1.jpg"));

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String strDate = dateFormat.format(date);

        File f1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(f1, new File("screenshots/screenshot2" + strDate + ".jpg"));
        driver.quit();
    }
}
