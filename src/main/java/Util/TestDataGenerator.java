package Util;

import Models.Medicine;
import Models.Substance;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TestDataGenerator {


    public static String[] usernames = {"Jan", "Piet", "Joris", "Corneel", "Seven Symphonious Cords", "Godwin Corelli", "Ajax", "Happy Cherubim", "Sesus Novia"};
    public static String[] streets = {"Poopdeck Lane", "Aft Avenue", "Forecastle street", "Keel", " Temple District", "Shrine to Konky", "Glories Most High", "Godsigh of Noonsday Triumph"};
    public static String[] housenumbers = {"1", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024"};
    public static String[] postcodes = {"4242", "6789", "1248", "1632", "6432", "1684", "2111"};
    public static String[] cities = {"Salty Barnacle", "HMS Unsinkable II", "Haul", "Glimmerbay", "Wanderlust", "Ash", "Gloomlight", "Karava", "Walking Devil Tower", "Hearthsbalm"};
    public static String[] emails = {"Jan@mannenmetbaarden.com", "Piet@mannenmetbaarden.com", "Joris@mannenmetbaarden.com", "Corneel@mannenmetbaarden.com", "AllConqueringCollossus@ILoveMyWife.com"};
    public static String[] companyNames = {"Aperture Science", "Umbrella Corporation", "Czerka Corporation", "Weyland-Utani", "Virtucon"};
    public static String[] telephoneNumbers = {"0123456789", "987654321", "12481632", "32168421", "149162536"};
    public static String DEFAULTDESTPATH = "./target/output/data/test/";


    public static void main(String[] args) throws IOException {
        int defaultNr = 50;
        generateApotheker(DEFAULTDESTPATH, defaultNr);
        generateKlant(DEFAULTDESTPATH, defaultNr);
        generateMedicinesAndSubstances(DEFAULTDESTPATH);
        generaterAfhalingen(DEFAULTDESTPATH, defaultNr);
        generateGebruik(DEFAULTDESTPATH,defaultNr,defaultNr,Medicine.getDefaultMedicineList().size(),defaultNr);
        generateBestellingen(DEFAULTDESTPATH,defaultNr,defaultNr,defaultNr,defaultNr);
        generateBestellingenInhoud(DEFAULTDESTPATH,defaultNr,Medicine.getDefaultMedicineList().size(),defaultNr);
    }

    public static void generateTestData(String folder, int nrOfEntriesPerTable) throws IOException {
        generateApotheker(folder, nrOfEntriesPerTable);
        generateKlant(folder, nrOfEntriesPerTable);
        generateMedicinesAndSubstances(folder);
        generaterAfhalingen(folder, nrOfEntriesPerTable);
        generateGebruik(folder,nrOfEntriesPerTable,nrOfEntriesPerTable,Medicine.getDefaultMedicineList().size(),nrOfEntriesPerTable);
        generateBestellingen(folder,nrOfEntriesPerTable,nrOfEntriesPerTable,nrOfEntriesPerTable,nrOfEntriesPerTable);
        generateBestellingenInhoud(folder,nrOfEntriesPerTable,Medicine.getDefaultMedicineList().size(),nrOfEntriesPerTable);
    }


    public static void generateApotheker(String destPath, int nrOfEntriesPerTable) throws IOException {
        new File(destPath).mkdirs();
        String dest = destPath + "apotheker.csv";
        FileWriter fw = new FileWriter(dest);
        //Write header
        fw.write("ApothekerID,Naam,Straat,Huisnummer,Postcode,Gemeente\n");
        //Write data
        for (int i = 1; i < nrOfEntriesPerTable + 1; i++) {
            String line = "" + i;
            line += "," + getRandomEntry(usernames);
            line += "," + getRandomEntry(streets);
            line += "," + getRandomEntry(housenumbers);
            line += "," + getRandomEntry(postcodes);
            line += "," + getRandomEntry(cities);
            line += "\n";
            fw.write(line);
        }
        fw.close();
    }

    public static void generateKlant(String destPath, int nrOfEntriesPerTable) throws IOException {
        new File(destPath).mkdirs();
        String dest = destPath + "klant.csv";
        FileWriter fw = new FileWriter(dest);
        //Write header
        fw.write("KlantID,Naam,Voornaam,Straat,Huisnummer,Postcode,Gemeente,Geboortedatum,Geslacht,Email,Wachtwoord\n");
        //Write data
        for (int i = 1; i < nrOfEntriesPerTable + 1; i++) {
            String line = "" + i;
            line += "," + getRandomEntry(usernames);
            line += "," + getRandomEntry(usernames);
            line += "," + getRandomEntry(streets);
            line += "," + getRandomEntry(housenumbers);
            line += "," + getRandomEntry(postcodes);
            line += "," + getRandomEntry(cities);
            line += "," + getRandomBirthdate();
            line += "," + getRandomFlag();
            String email = getRandomEntry(emails);
            line += "," + email;
            line += "," + calculatePassword(email);
            line += "\n";
            fw.write(line);
        }
        fw.close();
    }

    public static void generateMedicinesAndSubstances(String destPath) throws IOException {
        new File(destPath).mkdirs();
        String destMedicine = destPath + "medicijnen.csv";
        FileWriter fwMedicine = new FileWriter(destMedicine);
        //Write headers
        fwMedicine.write("MedicijnID,Naam,Afkorting,Omschrijving,Prijs\n");
        String destStoffen = destPath + "stofnaam.csv";
        FileWriter fwStoffen = new FileWriter(destStoffen);
        //Write headers
        fwStoffen.write("StofID,MedicijnID,Naam,Afkorting\n");
        List<Medicine> medicineList = Medicine.getDefaultMedicineList();
        int nrOfSubstancesProcessed = 1;
        for (int i = 1; i < medicineList.size() + 1; i++) {
            Medicine med = medicineList.get(i - 1);
            //Create medicine line
            String medicineline = "" + i;
            medicineline += "," + med.name;
            medicineline += "," + med.getShortenedForm();
            medicineline += "," + med.description;
            medicineline += "," + med.price;
            medicineline += "\n";
            fwMedicine.write(medicineline);
            //Create substance lines
            for (int j = 1; j < med.substances.size() + 1; j++) {
                Substance sub = med.substances.get(j - 1);
                String substanceLine = "" + nrOfSubstancesProcessed;
                substanceLine += "," + i;
                substanceLine += "," + sub.name;
                substanceLine += "," + sub.getShortenedForm();
                substanceLine += "\n";
                fwStoffen.write(substanceLine);
                nrOfSubstancesProcessed++;
            }
        }
        fwMedicine.close();
        fwStoffen.close();
    }

    public static void generaterAfhalingen(String destPath, int nrOfEntriesPerTable) throws IOException {
        new File(destPath).mkdirs();
        String dest = destPath + "afhaling.csv";
        FileWriter fw = new FileWriter(dest);
        //Write header
        fw.write("AfhaalID,Bedrijfsnaam,Telefoon\n");
        //Write data
        for (int i = 1; i < nrOfEntriesPerTable + 1; i++) {
            String line = "" + i;
            line += "," + getRandomEntry(companyNames);
            line += "," + getRandomEntry(telephoneNumbers);
            line += "\n";
            fw.write(line);
        }
        fw.close();
    }

    public static void generateGebruik(String destPath, int nrOfEntriesPerTable, int nrOfClients, int nrOfMedicines, int nrOfDoses) throws IOException {
        new File(destPath).mkdirs();
        String dest = destPath + "gebruik.csv";
        FileWriter fw = new FileWriter(dest);
        //Write header
        fw.write("GebruikID,KlantID,MedicijnID,AantalPerDag,AantalOchtend,AantalMiddag,AantalAvond,VoorMaaltijd,NaMaaltijd,TijdensMaaltijd\n");
        //Write data
        for (int i = 1; i < nrOfEntriesPerTable + 1; i++) {
            String line = "" + i;
            line += "," + getRandomNumber(nrOfClients);
            line += "," + getRandomNumber(nrOfMedicines);
            line += "," + getRandomNumber(nrOfDoses);
            line += "," + getRandomNumber(nrOfDoses);
            line += "," + getRandomNumber(nrOfDoses);
            line += "," + getRandomNumber(nrOfDoses);
            line += "," + getRandomFlag();
            line += "," + getRandomFlag();
            line += "," + getRandomFlag();
            line += "\n";
            fw.write(line);
        }
        fw.close();
    }

    public static void generateBestellingen(String destPath, int nrOfEntriesPerTable, int nrOfClients, int nrOfChemists, int nrOfTakeAways) throws IOException {
        new File(destPath).mkdirs();
        String dest = destPath + "bestellingen.csv";
        FileWriter fw = new FileWriter(dest);
        //Write header
        fw.write("BestelID,KlantID,ApothekerID,AfhaalID\n");
        //Write data
        for (int i = 1; i < nrOfEntriesPerTable + 1; i++) {
            String line = "" + i;
            line += "," + getRandomNumber(nrOfClients);
            line += "," + getRandomNumber(nrOfChemists);
            line += "," + getRandomNumber(nrOfTakeAways);
            line += "\n";
            fw.write(line);
        }
        fw.close();
    }
    public static void generateBestellingenInhoud(String destPath, int nrOfEntriesPerTable,int nrOfMedicines,int maxOrderAmount ) throws IOException {
        new File(destPath).mkdirs();
        String dest = destPath + "bestellinginhoud.csv";
        FileWriter fw = new FileWriter(dest);
        //Write header
        fw.write("BestelID,MedicijnID,Aantal\n");
        //Write data
        for (int i = 1; i < nrOfEntriesPerTable + 1; i++) {
            String line = "" + i;
            line += "," + getRandomNumber(nrOfMedicines);
            line += "," + getRandomNumber(maxOrderAmount);
            line += "\n";
            fw.write(line);
        }
        fw.close();
    }

    private static String getRandomEntry(String[] list) {
        int i = (int) Math.round((list.length - 1) * Math.random());
        return list[i];
    }

    /**
     * Generate random integer in [1,upperbound[ and return as string
     *
     * @param upperbound
     * @return
     */
    private static String getRandomNumber(int upperbound) {
        int res = (int) Math.round((upperbound) * Math.random())+1;
        res = Math.min(res,upperbound);
        res =Math.max(res, 1);
        return ""+res;
    }

    private static String getRandomFlag() {
        return "" + Math.round(Math.random());
    }

    private static String calculatePassword(String email) {
        return DigestUtils.md5Hex("alibaba");
    }

    private static String getRandomBirthdate() {
        //Fairly rolled with dice, totally not used my own birthday
        return "1993-02-15";
    }
}
