package sample.Field;

import java.util.List;

/**
 * Created by Alexander on 25.12.2014.
 */
public class Country {

    //region Constructors
    public Country(){};

    public Country(String name, int populations, int defence, int waterPositionCoef,int airPositionCoef,int animalPositionCoef,int area,int exportVirusCoef,int infected,boolean hasInfected){
        this.name=name;
        this.populations=populations;
        this.defence=defence;
        this.waterPositionCoef=waterPositionCoef;
        this.airPositionCoef=airPositionCoef;
        this.animalPositionCoef=animalPositionCoef;
        this.area=area;
        this.exportVirusCoef=exportVirusCoef;
        this.infected=infected;
        this.hasInfected=hasInfected;
    }
    //endregion

    //region Getter-Setter
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public int getPopulations(){return populations;}
    public void setPopulations(int populations){this.populations=populations;}
    public int getDefence(){return defence;}
    public void setDefence(int defence){this.defence=defence;}
    public int getWaterPositionCoef(){return waterPositionCoef;}
    public void setWaterPositionCoef(int waterPositionCoef){this.waterPositionCoef=waterPositionCoef;}
    public int getAirPositionCoef(){return airPositionCoef;}
    public void setAirPositionCoef(int airPositionCoef){this.airPositionCoef=airPositionCoef;}
    public int getAnimalPositionCoef(){return animalPositionCoef;}
    public void setAnimalPositionCoef(int animalPositionCoef){this.animalPositionCoef=animalPositionCoef;}
    public int getArea(){return area;}
    public void setArea(int area){this.area=area;}
    public List<Country> getNeighbors(){return neighbors;}
    public void setNeighbors(List<Country> neighbors){this.neighbors=neighbors;}
    public int getExportVirusCoef(){return exportVirusCoef;}
    public void setExportVirusCoef(int exportVirusCoef){this.exportVirusCoef=exportVirusCoef;}
    public int getInfected(){return infected;}
    public void setInfected(int infected){this.infected=infected;}
    public boolean getHasInfected(){return hasInfected;}
    public void setHasInfected(boolean hasInfected){this.hasInfected=hasInfected;}
    //endregion

    private String name;
    private int populations;
    private int defence;
    private int waterPositionCoef;
    private int airPositionCoef;
    private int animalPositionCoef;
    private int area;
    private List<Country> neighbors;
    private int exportVirusCoef;
    private int infected;
    private boolean hasInfected;

    public void upgradeDefence(){
        defence+=1;
    }

    public void createCure(){
        //Отношение зараженных к общей численности
        if(infected*1.0/populations>0.3){
            recovery();
        }
    }

    public void infecting(){
        //2,3 и 8 - коэффициенты "серьезности" среды распространения
        infected+=animalPositionCoef*2+waterPositionCoef*3+airPositionCoef*8;
    }

    public void recovery(){
        //Количество выздоравливаемых за тик
        infected-=10*defence;
    }

    public void dying(){
        //Количество умирающих за тик
        //Если 70% населения заражено, то смертоностность увеличивается в 10 раз.
        infected-=5;
        populations-=5;
        if (infected*1.0/populations>0.7){
            infected-=50;
            populations-=50;
        }
    }

    public void draw(   ){

    }
}
