package d25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Zadatak3 {

//Napisati program koji vrsi dodavanje 5 redova
//Maksimizirati prozor
//Ucitati stranicu https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php
//Dodati 5 redova sa istim podacima.Jedan red u jednoj iteraciji
//Klik na dugme Add New
//Unesite name,departmant i phone (uvek iste vrednost)
//Trazenje po name atributu
//Kliknite na zeleno Add dugme.
//PAZNJA: Pogledajte strukturu stranice i videcete da se u svakom redu poslednje kolone javljaju dugmici edit, add, delete ali zbog prirode reda neki dugmici se vide a neki ne.
//Morate da dohvatite uvek Add dugme iz poslednjeg reda tabele. Mozete koristeci index iz petlje, a mozete koristeci i last() fukncionalnost za xpath. Koristan link last mehnizma
//Cekanje od 0.5s
//Na kraju programa ugasite pretrazivac.


    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        ArrayList<String> names = new ArrayList<>();
        names.add("Milan Jovanovic");
        names.add("Vladimir Minic");
        names.add("Ana Laganin");
        names.add("Taylor Swift");
        names.add("Milan Paunovic");

        ArrayList<String> departments = new ArrayList<>();
        departments.add("DevOps");
        departments.add("QA");
        departments.add("Manager");
        departments.add("Entertainment");
        departments.add("IT");

        ArrayList<String> phones = new ArrayList<>();
        phones.add("+381659596123");
        phones.add("+38163222333");
        phones.add("+38166021023");
        phones.add("+4125557777");
        phones.add("+381636336001");

        driver.navigate().to("https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php");
        for (int i = 0; i < names.size(); i++) {
            driver.findElement(By.cssSelector("button.add-new")).click();
            driver.findElement(By.id("name")).sendKeys(names.get(i));
            driver.findElement(By.id("department")).sendKeys(departments.get(i));
            driver.findElement(By.id("phone")).sendKeys(phones.get(i));
            driver.findElement(By.cssSelector("tbody tr:last-child a.add")).click();
            Thread.sleep(500);
        }
        driver.quit();
    }
}
