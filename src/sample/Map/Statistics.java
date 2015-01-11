package sample.Map;

/**
 * Created by Alexander on 09.01.2015.
 */
public class Statistics {

    private boolean selected;

    private int virusLevelLethality;
    private int virusLevelInfectivity;

    private int infectedPeople;
    private int deadPeople;
    private double percentInfectedHealthPeople;

    private int infectedCountries;
    private int healthCountries;
    private double percentInfectedHealthCountries;

    //region Getter-Setter
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public int getVirusLevelLethality(){return virusLevelLethality;}
    public void setVirusLevelLethality(int virusLevelLethality){this.virusLevelLethality=virusLevelLethality;}

    public int getVirusLevelInfectivity(){return virusLevelInfectivity;}
    public void setVirusLevelInfectivity(int virusLevelInfectivity){this.virusLevelInfectivity=virusLevelInfectivity;}



    public int getInfectedPeople(){return infectedPeople;}
    public void setInfectedPeople(int infectedPeople){this.infectedPeople=infectedPeople;}

    public int getDeadPeople(){return deadPeople;}
    public void setDeadPeople(int deadPeople){this.deadPeople=deadPeople;}

    public double getPercentInfectedHealthPeople(){return percentInfectedHealthPeople;}
    public void setPercentInfectedHealthPeople(double percentInfectedHealthPeople){this.percentInfectedHealthPeople=percentInfectedHealthPeople;}



    public int getInfectedCountries(){return infectedCountries;}
    public void setInfectedCountries(int infectedCountries){this.infectedCountries=infectedCountries;}
    public int getHealthCountries() {
        return healthCountries;
    }

    public void setHealthCountries(int healthCountries) {
        this.healthCountries = healthCountries;
    }

    public double getPercentInfectedHealthCountries() {
        return percentInfectedHealthCountries;
    }

    public void setPercentInfectedHealthCountries(double percentInfectedHealthCountries) {
        this.percentInfectedHealthCountries = percentInfectedHealthCountries;
    }

    //endregion
}
