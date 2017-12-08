package Util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class TestDataGenerator {


    public static String[] usernames = {"Jan","Piet","Joris","Corneel","Seven Symphonious Cords","Godwin Corelli","Ajax","Happy Cherubim","Sesus Novia" };
    public static String[] streets = {"Poopdeck Lane", "Aft Avenue", "Forecastle street", "Keel"," Temple District", "Shrine to Konky", "Glories Most High", "Godsigh of Noonsday Triumph"};

    public static String[] housenumbers = {"1","2","4","8","16","32","64","128","256","512","1024"};
    public static String[] postcodes = {"4242", "6789","1248","1632","6432","1684","2111"};
    public static String[] cities = {"Salty Barnacle", "HMS Unsinkable II", "Haul","Glimmerbay","Wanderlust","Ash","Gloomlight","Karava","Walking Devil Tower","Hearthsbalm"};
    public static String[] sex = {"0","1"};
    public static String[] emails = {"Jan@mannenmetbaarden.com","Piet@mannenmetbaarden.com","Joris@mannenmetbaarden.com","Corneel@mannenmetbaarden.com","AllConqueringCollossus@ILoveMyWife.com"};

    public static String DESTPATH = "./target/output/data/test/";


    public static void main(String[] args) throws IOException {
        generateApotheker();
        generateKlant();
    }

    public static void generateApotheker() throws IOException {
        new File(DESTPATH).mkdirs();
        String dest = DESTPATH + "apotheker.csv";
        FileWriter fw = new FileWriter(dest);
        //Write header
        fw.write("ApothekerID,Naam,Straat,Huisnummer,Postcode,Gemeente\n");
        //Write data
        for (int i = 1; i < 51; i++) {
            String line = ""+i;
            line +=","+ getRandomEntry(usernames);
            line +=","+getRandomEntry(streets);
            line +=","+getRandomEntry(housenumbers);
            line +=","+getRandomEntry(postcodes);
            line +=","+getRandomEntry(cities);
            line+="\n";
            fw.write(line);
        }
        fw.close();
    }

    public static void generateKlant() throws IOException {
        new File(DESTPATH).mkdirs();
        String dest = DESTPATH + "klant.csv";
        FileWriter fw = new FileWriter(dest);
        //Write header
        fw.write("KlantID,Naam,Voornaam,Straat,Huisnummer,Postcode,Gemeente,Geboortedatum,Geslacht,Email,Wachtwoord\n");
        //Write data
        for (int i = 1; i < 51; i++) {
            String line = ""+i;
            line +=","+ getRandomEntry(usernames);
            line +=","+ getRandomEntry(usernames);
            line +=","+getRandomEntry(streets);
            line +=","+getRandomEntry(housenumbers);
            line +=","+getRandomEntry(postcodes);
            line +=","+getRandomEntry(cities);
            line +=","+ getRandomBirthdate();
            line +=","+getRandomEntry(sex);
            String email =getRandomEntry(emails);
            line +=","+email;
            line +=","+calculatePassword(email);
            line+="\n";
            fw.write(line);
        }
        fw.close();
    }
    private static String getRandomEntry(String[] list){
        int i =(int)  Math.round((list.length-1) * Math.random());
        return list[i];
    }

    private static String calculatePassword(String email){
        return DigestUtils.md5Hex("alibaba");
    }

    private static String getRandomBirthdate(){
        //Fairly rolled with dice
        return "1993-02-15";
    }
}
