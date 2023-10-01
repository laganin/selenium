package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Zadatak7 {

//Napisati program koji:
//Ucitava stranicu https://tus.io/demo.html
//Hvata sve linkove sa stranice
//Skrola do svakog h3 elementa
//Od svakog h3 elementa cita text


    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://tus.io/demo");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        List<WebElement> listLinks = webDriver.findElements(By.xpath("//link"));
        List<WebElement> h3Elements = webDriver.findElements(By.xpath("//h3"));

        for (int i = 0; i < h3Elements.size(); i++) {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3["+(i+1)+"]")));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(1000);
        }

        for (WebElement h3 : h3Elements){
            System.out.println(h3.getText());
        }
        webDriver.quit();

    }
}
