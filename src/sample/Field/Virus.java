package sample.Field;

import java.util.List;

/**
 * Created by Alexander on 25.12.2014.
 */
public class Virus {

    //region Constructors
    public Virus(){};

    public Virus(String name,int waterAttackInfectivity,int airAttackInfectivity,int animalAttackInfectivity,int lethality,int level){
        this.name=name;
        this.waterAttackInfectivity=waterAttackInfectivity;
        this.airAttackInfectivity=airAttackInfectivity;
        this.animalAttackInfectivity=animalAttackInfectivity;
        this.lethality=lethality;
        this.level=level;
    }
    //endregion

    //region Getter-Setter
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public int getWaterAttackInfectivity(){return waterAttackInfectivity;}
    public void setWaterAttackInfectivity(int waterAttackInfectivity){this.waterAttackInfectivity = waterAttackInfectivity;}
    public int getAirAttackInfectivity(){return airAttackInfectivity;}
    public void setAirAttackInfectivity(int airAttackInfectivity){this.airAttackInfectivity = airAttackInfectivity;}
    public int getAnimalAttackInfectivity(){return animalAttackInfectivity;}
    public void setAnimalAttackInfectivity(int animalAttackInfectivity){this.animalAttackInfectivity = animalAttackInfectivity;}
    public int getLevel(){return level;}
    public void setLevel(int level){this.level=level;}
    public int getLethality(){return lethality;}
    public void setLethality(int lethality){this.lethality = lethality;}
    public List<Country> getInfected(){return infected;}
    public void setInfected(List<Country> infected){this.infected=infected;}
    //endregion

    private String name;
    private int waterAttackInfectivity;
    private int airAttackInfectivity;
    private int animalAttackInfectivity;
    private int lethality;
    private int level;
    private List<Country> infected;

    public void lethalityUpgrade(){
        lethality=lethality*level;
    }

    public void infectivityUpgrade(){
        waterAttackInfectivity=waterAttackInfectivity*level;
        airAttackInfectivity=airAttackInfectivity*level;
        animalAttackInfectivity=animalAttackInfectivity*level;
    }

    public void infect(Country selectedCountry){
        if (!selectedCountry.getHasInfected()){
            selectedCountry.setHasInfected(true);
        }
    }

    public void export(){
        for (Country country: infected){
            for (Country neighbor: country.getNeighbors()){
                if ((Math.random()*10==1)&&(neighbor.getDefence()/(2*level)<1)){
                    infect(neighbor);
                }
                if(country.getExportVirusCoef()>14){
                    if(Math.random()*3==2){
                        infect(neighbor);
                    }
                }
            }
        }
    }
}
