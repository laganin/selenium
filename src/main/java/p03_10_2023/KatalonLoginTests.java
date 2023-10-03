package p03_10_2023;

//Kreirati klasu KatalonLoginTests za testove
//Base url: https://cms.demo.katalon.com
//Test #1: Visit login page from Nav bar
//Koraci:
//Ucitati home stranicu
//Kliknuti na My account link
//Verifikovati da je naslov stranice My account – Katalon Shop
//Verifikovati da se u url-u stranice javlja /my-account
//Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//Test #2: Check input types
//        Koraci:
//        Ucitati /my-account stranicu
//        Verifikovati da  polje za unos email-a za atribu type ima vrednost text
//        Verifikovati da polje za unos lozinke za atribut type ima vrednost password
//        Verifikovati da checkbox Remember me za atribut type ima vrednost checkbox
//        Verifikovati da je Remember me checkbox decekiran. Koristan link kako naci informaciu da li je checkbox cekiran ili ne.
//        Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//Test #3: Display error when credentials are wrong
//        Podaci:
//        email: invalidemail@gmail.com
//password: invalid123
//        Koraci:
//        Ucitati home stranicu
//        Kliknuti na My account link
//        Unesite email
//        Unesite password
//        Kliknite na login dugme
//        Verifikovati da postoji element koji prikazuje gresku
//        Verifikovati da je prikazana greska ERROR: Invalid email address
//        Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//        Verifikovati da smo idalje na login stranici posle greske, tako sto proveravamo da se url-u javlja /my-account
//
//Test #4: Successful login with valid credentials
//        Podaci:
//        username: customer
//        password: crz7mrb.KNG3yxv1fbn
//        Koraci:
//        Ucitati home stranicu
//        Kliknuti na My account link
//        Unesite email
//        Unesite password
//        Kliknite na login dugme
//        Verifikovati da se pojavljuje link za logout na stranici


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class KatalonLoginTests {

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
    public void beforeMethod() {
        driver.navigate().to("https://cms.demo.katalon.com");
        driver.manage().deleteAllCookies();
    }

    @Test
    public void visitLoginPageFromNavBar() {
        driver.findElement(By.cssSelector("ul.nav-menu li:nth-child(3)")).click();
        wait
                .withMessage("Title not as expected")
                .until(ExpectedConditions.titleContains("My account – Katalon Shop"));
//        Assert.assertTrue(driver.getTitle().contains("My account – Katalon Shop"), "Title not as expected");
        Assert.assertTrue(driver.getCurrentUrl().contains("/my-account"), "URL not as expected");
    }

    @Test
    public void checkInputTypes() {
        driver.findElement(By.cssSelector("ul.nav-menu li:nth-child(3)")).click();
        wait
                .withMessage("Title not as expected")
                .until(ExpectedConditions.titleContains("My account – Katalon Shop"));

        String typeText = driver.findElement(By.id("username")).getAttribute("type");
        Assert.assertTrue(typeText.contains("text"), "Attribute type is not text");

        String typePassword = driver.findElement(By.id("password")).getAttribute("type");
        Assert.assertTrue(typePassword.contains("password"), "Attribute type is not password");

        String typeCheckbox = driver.findElement(By.id("rememberme")).getAttribute("type");
        Assert.assertTrue(typeCheckbox.contains("checkbox"), "Attribute type is not checkbox");

        WebElement checkbox = driver.findElement(By.id("rememberme"));
        Assert.assertTrue(!checkbox.isSelected(), "Checkbox is selected");
    }

    @Test
    public void displayErrorWhenCredentialsAreWrong() {
        driver.findElement(By.cssSelector("ul.nav-menu li:nth-child(3)")).click();
        Assert.assertTrue(driver.getTitle().contains("My account – Katalon Shop"), "Title not as expected");
        String email = "invalidemail@gmail.com";
        String password = "invalid123";
        driver.findElement(By.id("username")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[name='login']")).click();
        wait
                .withMessage("Element not visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("woocommerce-error")));
        String errorMessage = driver.findElement(By.className("woocommerce-error")).getText();
        Assert.assertTrue(errorMessage.contains("ERROR: Invalid email address"), "No error message appeared.");
        Assert.assertTrue(driver.getCurrentUrl().contains("/my-account"), "URL not as expected");
    }

    @Test
    public void successfulLoginWithValidCredentials() {
        driver.findElement(By.cssSelector("ul.nav-menu li:nth-child(3)")).click();
        Assert.assertTrue(driver.getTitle().contains("My account – Katalon Shop"), "Title not as expected");
        String username = "customer";
        String password = "crz7mrb.KNG3yxv1fbn";
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[name='login']")).click();
        wait
                .withMessage("Logout not visible.")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.className("woocommerce-MyAccount-navigation-link--customer-logout"))));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
