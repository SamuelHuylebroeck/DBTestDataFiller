package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samuel on 08/12/2017.
 */
public class Medicine {

    public String id;
    public String name;
    public String description;
    public List<Substance> substances;
    public String price;

    public Medicine(String id, String name, String description, String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        substances = new ArrayList<>();
    }

    public void addSubstance(Substance sub){
        substances.add(sub);
    }

    public String getShortenedForm(){
        return name.substring(0,2).toUpperCase();
    }

    public static List<Medicine> getDefaultMedicineList(){

        List<Medicine> result = new ArrayList<>();
        //Create the substances
        Substance moondrop = new Substance(4);
        Substance ghostflower = new Substance (8);
        Substance peach = new Substance(16);
        Substance brightMorning = new Substance(32);
        Substance Panacea = new Substance(64);
        //Create some medicins
        Medicine moonGhost = new Medicine("1","Moon Ghost","Sleepy time","999");
        moonGhost.addSubstance(moondrop);
        moonGhost.addSubstance(ghostflower);
        result.add(moonGhost);

        Medicine longlivity = new Medicine("2","Longlivity","to live eternal","1999");
        longlivity.addSubstance(peach);
        result.add(longlivity);

        Medicine rainbow = new Medicine("3","Rainbow","Everything","12345");
        rainbow.addSubstance(moondrop);
        rainbow.addSubstance(brightMorning);
        rainbow.addSubstance(ghostflower);
        rainbow.addSubstance(peach);
        rainbow.addSubstance(Panacea);
        result.add(rainbow);

        Medicine tripleCure = new Medicine("4","Triple Threat","Three ingredients","5");
        tripleCure.addSubstance(moondrop);
        tripleCure.addSubstance(brightMorning);
        tripleCure.addSubstance(Panacea);
        result.add(tripleCure);
        return result;
    }

}
