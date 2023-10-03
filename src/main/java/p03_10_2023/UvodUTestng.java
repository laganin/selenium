package p03_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import p28_09_2023.TestHelper;

import java.time.Duration;

public class UvodUTestng {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void beforMethod() {
        driver.navigate().to("https://google.com");
    }

    @Test
    public void  visitLoginPageFromNavBar() {

    }

    @Test
    public void googleTitleTest() {
        Assert.assertTrue(driver.getTitle().contains("Google"), "dskdsk");
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.google.com/",
                "IDE PORUKA");

//        TestHelper testHelper = new TestHelper(driver);
//        boolean elementExist =  testHelper.elementExists(By.name("sdkdskl"));
//        Assert.assertFalse(elementExist);
        wait
                .withMessage("")
                .until(ExpectedConditions.numberOfElementsToBe(By.name("edoiewoi"), 0));


        WebElement searchInput = driver.findElement(By.name("q"));

        searchInput.sendKeys("it bootcamp");
        searchInput.sendKeys(Keys.ENTER);


        wait
                .withMessage("After search title should include 'it bootcamp'")
                .until(ExpectedConditions.titleContains("it bootcampdfdfdf"));



    }
    @Test
    public void googleSearchTest() {
        driver
                .findElement(By.name("qjksdjksdkj"))
                .sendKeys("IT Bootcamp");
        driver.navigate().to("https://youtube.com");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
