package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Zadatak6 {

//Napisati program koji:
//Ucitava stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//Vrsi klik na Primary dugme, Secondary, Sucess, Danger
//Ceka da broj toasts-a bude 4
//Ispisuje poruku, toasts su prikazani
//Ceka da broj toasts-a bude 0
//Ispisuje poruku, toasts su se izgubili


    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");

        List<WebElement> buttons = new ArrayList<>();
        buttons.add(driver.findElement(By.id("basic-primary-trigger")));
        buttons.add(driver.findElement(By.id("basic-secondary-trigger")));
        buttons.add(driver.findElement(By.id("basic-success-trigger")));
        buttons.add(driver.findElement(By.id("basic-danger-trigger")));

        List<WebElement> toasts = new ArrayList<>();

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click();
            toasts.add(driver.findElement(By.xpath("//div[contains(@class, 'toast fade')]")));

        }
        wait
                .withMessage("Not all 4 toasts have appeared")
                .until(ExpectedConditions.visibilityOfAllElements(toasts));
        System.out.println("All 4 toasts have appeared");

        wait
                .withMessage("Toasts still visible")
                .until(ExpectedConditions.invisibilityOfAllElements(toasts));
        System.out.println("All 4 toasts have disappeared");

        driver.quit();
    }
}
