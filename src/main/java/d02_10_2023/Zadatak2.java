package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak2 {

//Napisati program koji:
//Ucitava stranu https://itbootcamp.rs/
//Misem prelazi preko Vesti iz navigacionog menija
//Ceka da se prikaze padajuci meni za Vesti
//Misem prelazi preko Kursevi iz navigacionog menija
//Ceka da se prikaze padajuci meni za Kursevi
//Misem prelazi preko Prijava i pravilnik iz navigacionog menija
//Ceka da se prikaze padajuci meni za Prijava i pravilnik
//Koristan link. Mouse over preko seleniuma https://www.selenium.dev/documentation/webdriver/actions_api/mouse/#move-to-element

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://itbootcamp.rs/");

        WebElement news = driver.findElement(By.cssSelector("#menu-main-menu > li:nth-child(2)"));
        new Actions(driver)
                .moveToElement(news)
                .perform();
        wait
                .withMessage("Dropdown menu not visible")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-item-9740"))));

        WebElement courses = driver.findElement(By.cssSelector("#menu-main-menu > li:nth-child(3)"));
        new Actions(driver)
                .moveToElement(courses)
                .perform();

        wait
                .withMessage("Dropdown menu not visible")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-item-5713"))));

        WebElement enroll = driver.findElement(By.cssSelector("#menu-main-menu > li:nth-child(4)"));
        new Actions(driver)
                .moveToElement(enroll)
                .perform();

        wait
                .withMessage("Dropdown menu not visible")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu-item-9232"))));

        driver.quit();
    }
}
