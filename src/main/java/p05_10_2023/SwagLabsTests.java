package p05_10_2023;

import com.google.common.collect.Ordering;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SwagLabsTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseURL = "https://www.saucedemo.com";
    private String username = "standard_user";
    private String password = "secret_sauce";
    private String checkoutName = "Pera";
    private String checkoutLastName = "Peric";
    private String checkoutZip = "18000";

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
        driver.navigate().to(baseURL);
    }

    @Test(priority = 1, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenUsernameIsMissing() {
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(
                driver.findElement(By.className("error-message-container")).getText(),
                "Epic sadface: Username is required",
                "Username required message not visible");
    }

    @Test(priority = 2, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenPasswordsIsMissing() {

        driver.findElement(By.id("user-name")).sendKeys(username);

        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(
                driver.findElement(By.className("error-message-container")).getText(),
                "Epic sadface: Password is required",
                "Password required message not visible");
    }

    @Test(priority = 3, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenCredentialsAreWrong() {
        String password = "invalidpassword";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(
                driver.findElement(By.className("error-message-container")).getText(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message is not displayed when password is invalid.");
    }

    @Test(priority = 4, retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenUserIsLocked() {
        String username = "locked_out_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(
                driver.findElement(By.className("error-message-container")).getText(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Error message is not displayed when user has been locked out.");
    }

    @Test(priority = 5, retryAnalyzer = SwagLabsRetry.class)
    public void verifySuccessfulLogin() {

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("URL is incorrect")
                .until(ExpectedConditions.urlContains("/inventory.html"));

        driver.findElement(By.id("react-burger-menu-btn")).click();

        wait
                .withMessage("URL is incorrect")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.className("bm-item-list"))));

        wait
                .withMessage("Logout does not exist")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("logout_sidebar_link"))));

        driver.findElement(By.id("logout_sidebar_link")).click();

        wait
                .withMessage("Login form does not exist")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.className("login_wrapper-inner"))));
    }

    @Test(priority = 6, retryAnalyzer = SwagLabsRetry.class)
    public void addingProductsToCart() {

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        wait
                .withMessage("URL is incorrect")
                .until(ExpectedConditions.urlContains("/inventory.html"));

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        wait
                .withMessage("Remove button not visible")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("remove-sauce-labs-backpack"))));

        Assert.assertEquals(
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "1",
                "Cart items is not 1");
    }

    @Test(priority = 7, retryAnalyzer = SwagLabsRetry.class)
    public void viewingProductDetails() {

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        wait
                .withMessage("URL is incorrect")
                .until(ExpectedConditions.urlContains("/inventory.html"));

        driver.findElement(By.id("item_4_title_link")).click();

        Assert.assertTrue
                (driver.findElement(By.className("inventory_details_img_container")).isDisplayed(),
                        "Image not present");
        Assert.assertTrue
                (driver.findElement(By.className("inventory_details_name")).isDisplayed(),
                        "Item name not present");
        Assert.assertTrue
                (driver.findElement(By.className("inventory_details_desc")).isDisplayed(),
                        "Item description not present");
        Assert.assertTrue
                (driver.findElement(By.className("inventory_details_price")).isDisplayed(),
                        "Price not present");
        Assert.assertTrue
                (driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).isDisplayed(),
                        "Add to cart is not present");
    }

    @Test(priority = 8, retryAnalyzer = SwagLabsRetry.class)
    public void removingProductsFromCart() {

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        wait
                .withMessage("URL is incorrect")
                .until(ExpectedConditions.urlContains("/inventory.html"));

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        Assert.assertEquals(
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "1",
                "Cart items is not 1");

        driver.findElement(By.className("shopping_cart_link")).click();

        Assert.assertEquals(
                driver.findElement(By.className("inventory_item_name")).getText(),
                "Sauce Labs Backpack",
                "Sauce Labs Backpack not added");

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        wait
                .withMessage("Sauce Labs Backpack still in cart")
                .until
                        (ExpectedConditions.invisibilityOfElementWithText(By.className("inventory_item_name"),
                                "Sauce Labs Backpack"));
    }

    @Test(priority = 9, retryAnalyzer = SwagLabsRetry.class)
    public void productCheckout() {

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        wait
                .withMessage("URL is incorrect")
                .until(ExpectedConditions.urlContains("/inventory.html"));

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        Assert.assertEquals(
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "1",
                "Cart items is not 1");

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys(checkoutName);
        driver.findElement(By.id("last-name")).sendKeys(checkoutLastName);
        driver.findElement(By.id("postal-code")).sendKeys(checkoutZip);

        driver.findElement(By.id("continue")).click();

        Assert.assertEquals(
                driver.findElement(By.className("inventory_item_name")).getText(),
                "Sauce Labs Backpack",
                "Sauce Labs Backpack not in cart");

        Assert.assertEquals(
                driver.findElement(By.className("cart_quantity")).getText(),
                "1",
                "Quantity of Sauce Labs Backpack is not 1");

        Assert.assertEquals(
                driver.findElement(By.className("inventory_item_price")).getText(),
                "$29.99",
                "Price is not $29.99");

        Assert.assertEquals(
                driver.findElement(By.className("inventory_item_price")).getText(),
                "$29.99",
                "Price is not $29.99");

        Assert.assertEquals(
                driver.findElement(By.className("summary_total_label")).getText(),
                "Total: $32.39",
                "Price is not $32.39");

        driver.findElement(By.id("finish")).click();

        Assert.assertEquals(
                driver.findElement(By.className("complete-header")).getText(),
                "Thank you for your order!",
                "Thank you message not displayed");
    }

    @Test(priority = 10, retryAnalyzer = SwagLabsRetry.class)
    public void validateSocialLinksInFooter() throws IOException {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("URL is incorrect")
                .until(ExpectedConditions.urlContains("/inventory.html"));

        WebElement bottom = driver.findElement(By.cssSelector("footer.footer"));
        new Actions(driver).scrollToElement(bottom).perform();

        List<WebElement> social = driver.findElements(By.cssSelector("ul.social li a"));

        for (int i = 0; i < social.size(); i++) {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            URL socialLink = new URL(social.get(i).getAttribute("href"));
            String URL = social.get(i).getAttribute("href");
            HttpURLConnection http = (HttpURLConnection) socialLink.openConnection();
            int statusCode = http.getResponseCode();
            Assert.assertEquals(statusCode, 200, "Link " + URL + " does not have status code 200");
        }
    }

    @Test(priority = 11, retryAnalyzer = SwagLabsRetry.class)
    public void testDefaultNameSortAZ() {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("URL is incorrect")
                .until(ExpectedConditions.urlContains("/inventory.html"));

        String activeFilter = driver.findElement(By.className("active_option")).getText();
        Assert.assertEquals(activeFilter, "Name (A to Z)", "The default page sort is not A-Z");

        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        List<String> productNames = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productNames.add(products.get(i).getText());
        }
        Assert.assertTrue(Ordering.natural().isOrdered(productNames), "The default page sort is not A-Z");
    }

    @Test(priority = 12, retryAnalyzer = SwagLabsRetry.class)
    public void testInvertNamedSortZA() {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("URL is incorrect")
                .until(ExpectedConditions.urlContains("/inventory.html"));

        Select sort = new Select(driver.findElement(By.className("product_sort_container")));
        sort.selectByVisibleText("Name (Z to A)");

        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        List<String> productNames = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productNames.add(products.get(i).getText());
        }
        Assert.assertTrue(Ordering.natural().reverse().isOrdered(productNames), "The sort is not Z-A");

    }

    @Test(priority = 13, retryAnalyzer = SwagLabsRetry.class)
    public void testSortPriceLowHigh() {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("URL is incorrect")
                .until(ExpectedConditions.urlContains("/inventory.html"));

        Select sort = new Select(driver.findElement(By.className("product_sort_container")));
        sort.selectByValue("lohi");

        List<WebElement> products = driver.findElements(By.className("inventory_item_price"));
        List<Double> productPrices = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            double prices = Double.parseDouble(products.get(i).getText().replace("$", ""));
            productPrices.add(prices);
        }
        Assert.assertTrue(Ordering.natural().isOrdered(productPrices), "The sort is not price low to high");
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}