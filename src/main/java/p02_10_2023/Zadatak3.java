package p02_10_2023;

import java.io.File;
import java.io.IOException;

public class Zadatak3 {

//Napisati program koji
//Kreirati folder downloads folder u projektu
//URL SLIKE https://cdn.britannica.com/29/150929-050-547070A1/lion-Kenya-Masai-Mara-National-Reserve.jpg
//Sliku sacuvajte u folderu downloads pod nazivom ljuti-lav.jpg
//Koristan link za skidanje fajlova u javi
//Azurirajte gitignore da ignorise downloads folder

    public static void main(String[] args) throws IOException {

        Helper.downloadUsingStream("https://cdn.britannica.com/29/150929-050-547070A1/lion-Kenya-Masai-Mara-National-Reserve.jpg", "downloads/angry-lion.jpg");
    }
}
