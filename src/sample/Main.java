package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Field.Country;
import sample.Field.Virus;
import sample.Map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("GAME");
        javafx.scene.Group root=new javafx.scene.Group();
        Canvas canvas = new Canvas(600,300);
        final GraphicsContext gc=canvas.getGraphicsContext2D();

        //region Init Countries
        final Country Russia=new Country("Russia",140,7,6,13,10,100,11,0,false);
        final Country USA=new Country("USA",321,16,8,12,15,70,8,0,false);
        final Country Germany=new Country("Germany",80,15,5,9,4,50,14,0,false);
        final Country Sweden=new Country("Sweden",10,18,10,7,3,20,2,0,false);
        final Country Mexico = new Country("Mexico",120,2,9,17,19,40,18,0,false);

        //region Neighbors
        List<Country> neighborsRussia=new ArrayList<Country>();
        neighborsRussia.add(Germany);
        neighborsRussia.add(Sweden);
        neighborsRussia.add(USA);
        neighborsRussia.add(Mexico);

        List<Country> neighborsUSA=new ArrayList<Country>();
        neighborsUSA.add(Russia);
        neighborsUSA.add(Mexico);

        List<Country> neighborsGermany=new ArrayList<Country>();
        neighborsGermany.add(Russia);
        neighborsGermany.add(Sweden);

        List<Country> neighborsSweden=new ArrayList<Country>();
        neighborsSweden.add(Russia);
        neighborsSweden.add(Germany);

        List<Country> neighborsMexico=new ArrayList<Country>();
        neighborsMexico.add(USA);
        neighborsMexico.add(Russia);
        //endregion
        Russia.setNeighbors(neighborsRussia);
        USA.setNeighbors(neighborsUSA);
        Germany.setNeighbors(neighborsGermany);
        Sweden.setNeighbors(neighborsSweden);
        Mexico.setNeighbors(neighborsMexico);
        //endregion

        //region Init Virus
        final Virus ebola=new Virus("Ebola",8,16,15,7,1);
        List<Country> infected=new ArrayList<Country>();
        ebola.setInfected(infected);
        //endregion

        //region Init Map
        List<Country> countries=new ArrayList<Country>();
        countries.add(Russia);
        countries.add(USA);
        countries.add(Germany);
        countries.add(Sweden);
        countries.add(Mexico);

        final Map map=new sample.Map.Map(countries,ebola,4);
        //endregion

        //region TimerTasks
        TimerTask infective=new TimerTask() {
            @Override
            public void run() {
                Russia.setInfected(Russia.getInfected()+2*ebola.getAirAttackInfectivity());
            }
        };

        TimerTask heal=new TimerTask() {
            @Override
            public void run() {
                Russia.setInfected(Russia.getInfected()-Russia.getDefence());
            }
        };

        TimerTask infectMexico=new TimerTask() {
            @Override
            public void run() {
                Russia.getNeighbors().get(3).setInfected(Mexico.getInfected()+2*ebola.getAirAttackInfectivity());
            }
        };
        TimerTask healMexico=new TimerTask() {
            @Override
            public void run() {
                Mexico.setInfected(Mexico.getInfected()-Mexico.getDefence());
            }
        };

        TimerTask infectUSA=new TimerTask() {
            @Override
            public void run() {
                Russia.getNeighbors().get(0).setInfected(USA.getInfected()+2*ebola.getAirAttackInfectivity());
            }
        };
        TimerTask healUSA=new TimerTask() {
            @Override
            public void run() {
                USA.setInfected(USA.getInfected()-USA.getDefence());
            }
        };

        TimerTask infectGermany=new TimerTask() {
            @Override
            public void run() {
                Russia.getNeighbors().get(1).setInfected(Germany.getInfected()+2*ebola.getAirAttackInfectivity());
            }
        };
        TimerTask healGermany=new TimerTask() {
            @Override
            public void run() {
                Germany.setInfected(Germany.getInfected() - Germany.getDefence());
            }
        };

        TimerTask infectSweden=new TimerTask() {
            @Override
            public void run() {
                Russia.getNeighbors().get(2).setInfected(Sweden.getInfected()+2*ebola.getAirAttackInfectivity());
            }
        };
        TimerTask healSweden=new TimerTask() {
            @Override
            public void run() {
                Sweden.setInfected(Sweden.getInfected() - Sweden.getDefence());
            }
        };

        TimerTask addPoints=new TimerTask() {
            @Override
            public void run() {
                map.setPoints(map.getPoints()+1*ebola.getLethality()+1*ebola.getAirAttackInfectivity());
            }
        };
        //endregion

        final Timer gameTimer=new Timer();
        gameTimer.schedule(addPoints,0L,60L*1000/12);//add point every 5 sec depents on virus level
        gameTimer.schedule(infective,0L,60L*1000/24);//infect rus every 2.5sec depents on infected before
        gameTimer.schedule(heal,0L,60L*1000/12);//heal rus every 5sec depends on level defence
        gameTimer.schedule(infectGermany,30L,60L*1000/24);
        gameTimer.schedule(infectMexico,10L,60L*1000/24);
        gameTimer.schedule(infectSweden,25L,60L*1000/24);
        gameTimer.schedule(infectUSA,50L,60L*1000/24);
        gameTimer.schedule(healGermany,30L,60L*1000/12);
        gameTimer.schedule(healMexico,10L,60L*1000/5);
        gameTimer.schedule(healSweden,25L,60L*1000/20);
        gameTimer.schedule(healUSA,50L,60L*1000/15);

        //TODO every 1sec update Stats on main screen
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //drawStat(gc,map);
            }
        },0L,60L*1000/60);

        //CHECK END GAME every 10SEC
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean flag=checkEndGame(map);
                if (flag){
                    gameTimer.cancel();
                    gc.fillText("Status: YOU WIN",10,10);
                } else gc.fillText("Status: Killing people",10,10);
            }
        },0L,60L*1000/6);
        drawCountry(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    //TODO COMPLETE
    private void drawCountry(GraphicsContext gc){

        gc.setFill(Color.BLUE);
        gc.setLineWidth(5);
        gc.fillRoundRect(250,110,100,80,10,10);

        gc.setFill(Color.YELLOW);
        gc.fillRoundRect(175,110,75,75,10,10);

        gc.setFill(Color.BROWN);
        gc.fillRoundRect(225, 75, 35, 35, 10, 10);

        gc.setFill(Color.GOLD);
        gc.fillRoundRect(350, 75, 90, 80, 10, 10);

        gc.setFill(Color.GREEN);
        gc.fillRoundRect(350, 155, 70, 70, 10, 10);


        gc.setFill(Color.BLACK);
        gc.fillText("Points:",10,50);

        gc.fillText("Stats:",10,100);
        gc.fillText("Killed:",20,110);
        gc.fillText("Infected:",20,120);
        gc.fillText("Infected,%:",20,130);

    }

    //TODO error. should fix
    public void drawStat(GraphicsContext gc,Map map){
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(map.getStat().getDeadPeople()),30,110);
        gc.fillText(String.valueOf(map.getStat().getInfectedPeople()),30,120);
        gc.fillText(String.valueOf(map.getStat().getPercentInfectedHealthPeople()),30,130);
    }


    public boolean checkEndGame(Map map){
        int infected=0;
        int allPopulation=0;
        for (int i=0;i<map.getCountries().size();i++){
            infected+=map.getCountries().get(i).getInfected();
            allPopulation+=map.getCountries().get(i).getPopulations();
        }
        if (infected==allPopulation){
            return true;
        } else return false;
    }

    //TODO COMPLETE
    //TODO MOVE TO MAIN METHOD
    /*
    public void OnClickStart(){

        //region Init Countries
        final Country Russia=new Country("Russia",140,7,6,13,10,100,11,0,false);
        Country USA=new Country("USA",321,16,8,12,15,70,8,0,false);
        Country Germany=new Country("Germany",80,15,5,9,4,50,14,0,false);
        Country Sweden=new Country("Sweden",10,18,10,7,3,20,2,0,false);
        Country Mexico = new Country("Mexico",120,2,9,17,19,40,18,0,false);

        //region Neighbors
        List<Country> neighborsRussia=new ArrayList<Country>();
        neighborsRussia.add(Germany);
        neighborsRussia.add(Sweden);
        neighborsRussia.add(USA);
        neighborsRussia.add(Mexico);

        List<Country> neighborsUSA=new ArrayList<Country>();
        neighborsUSA.add(Russia);
        neighborsUSA.add(Mexico);

        List<Country> neighborsGermany=new ArrayList<Country>();
        neighborsGermany.add(Russia);
        neighborsGermany.add(Sweden);

        List<Country> neighborsSweden=new ArrayList<Country>();
        neighborsSweden.add(Russia);
        neighborsSweden.add(Germany);

        List<Country> neighborsMexico=new ArrayList<Country>();
        neighborsMexico.add(USA);
        neighborsMexico.add(Russia);
        //endregion
        Russia.setNeighbors(neighborsRussia);
        USA.setNeighbors(neighborsUSA);
        Germany.setNeighbors(neighborsGermany);
        Sweden.setNeighbors(neighborsSweden);
        Mexico.setNeighbors(neighborsMexico);
        //endregion

        //region Init Virus
        final Virus ebola=new Virus("Ebola",8,16,15,7,1);
        List<Country> infected=new ArrayList<Country>();
        ebola.setInfected(infected);
        //endregion

        //region Init Map
        List<Country> countries=new ArrayList<Country>();
        countries.add(Russia);
        countries.add(USA);
        countries.add(Germany);
        countries.add(Sweden);
        countries.add(Mexico);

        final Map map=new sample.Map.Map(countries,ebola,4);
        //endregion

        //region TimerTasks
        TimerTask infective=new TimerTask() {
            @Override
            public void run() {
                Russia.setInfected(Russia.getInfected()+2*ebola.getAirAttackInfectivity());
            }
        };

        TimerTask heal=new TimerTask() {
            @Override
            public void run() {
                Russia.setInfected(Russia.getInfected()-Russia.getDefence());
            }
        };

        TimerTask addPoints=new TimerTask() {
            @Override
            public void run() {
                map.setPoints(map.getPoints()+1*ebola.getLethality()+1*ebola.getAirAttackInfectivity());
            }
        };
        //endregion

        Timer gameTimer=new Timer();
        gameTimer.schedule(addPoints,0L,60L*1000/12);//Добавляются очки каждые 5 секунд
        gameTimer.schedule(infective,0L,60L*1000/24);//Заражаются каждые 2.5 секунды
        gameTimer.schedule(heal,0L,60L*1000/12);//Лечатся каждые 5 секунд

    }*/

}
