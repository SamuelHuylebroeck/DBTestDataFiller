package Models;

/**
 * Created by Samuel on 08/12/2017.
 */
public class Substance {

    public int name;

    public Substance(int name) {
        this.name = name;
    }

    public String getShortenedForm(){
        return "" + name/10 +"-"+ name%10;
    }

}
