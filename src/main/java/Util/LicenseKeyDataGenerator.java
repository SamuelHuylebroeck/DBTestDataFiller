package Util;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;

public class LicenseKeyDataGenerator {



    public static String DESTPATH = "./target/output/data/license/";
    public static String[] emails = {"Jan@mannenmetbaarden.com","Piet@mannenmetbaarden.com","Joris@mannenmetbaarden.com","Corneel@mannenmetbaarden.com"};
    public static String[] standard_eula_signed={""};
    public static String[] countries ={""};
    public static String[] address={""};
    public static String[] reseller ={""};



    public static void main(String[] args) throws IOException {
        generateCustomerCSV();
    }



    public static void generateCustomerCSV() throws IOException {
        new File(DESTPATH).mkdirs();
        String dest = DESTPATH + "customer.csv";
        FileWriter fw = new FileWriter(dest);
        //Write header
        fw.write("id,email,password,company,custom_agreement,standard_eula_signed,country,address,reseller\n");
        //Write data
        for (int i = 1; i < 51; i++) {
            String line = ""+i;
            String email = getRandomEntry(emails);
            String password = createPassword(email);
            line +=","+ email;
            line +=","+password;
            /*
            line += "0";
            line +=","+getRandomEntry(countries);
            line +=","+getRandomEntry(address);
            line +=","+getRandomEntry(address);
            line+="\n";
            */
            fw.write(line);
        }
        fw.close();
    }

    public static String createPassword(String email){

        final String password = "alibaba";
        final String hashedPassword = DigestUtils.shaHex(password).substring(0, 10);
        return hashedPassword;
    }

    private static String getRandomEntry(String[] list){
        int i =(int)  Math.round((list.length-1) * Math.random());
        return list[i];
    }

    private static String getRandomChoiceWeighted(String[] options, float firstChoiceWeightPercent){
        throw new NotImplementedException();
    }


}
