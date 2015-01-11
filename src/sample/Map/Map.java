package sample.Map;

import sample.Field.Country;
import sample.Field.Virus;

import java.util.List;

/**
 * Created by Alexander on 25.12.2014.
 */
public class Map {

    //region Constructors
    public Map(){};

    public Map(List<Country> countries, Virus virus,int points){
        this.countries=countries;
        this.virus=virus;
        this.points=points;
    }
    //endregion

    private List<Country> countries;
    private Virus virus;
    private Statistics stat;
    private int points;

    //region Getter-Setter
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public List<Country> getCountries(){return countries;}
    public void setCountries(List<Country> countries){this.countries=countries;}
    public Virus getVirus(){return virus;}
    public void setVirus(Virus virus){this.virus=virus;}

    public Statistics getStat() {
        return stat;
    }

    public void setStat(Statistics stat) {
        this.stat = stat;
    }
    //endregion
}
