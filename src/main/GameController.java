package main;

import java.util.*;
import javax.swing.JOptionPane;
import main.experts.ListenerExpert;
import main.experts.StatExpert;
import main.food.*;
import main.territory.Territory;
import main.territory.TerritoryConqueredEvent;
import main.territory.TerritoryConqueredListener;
import main.view.IFView;
import main.view.View;

public class GameController implements Runnable {

    private static ArrayList<Microbe> microbes = new ArrayList<Microbe>();
    private static ArrayList<Food> food = new ArrayList<Food>();
    private static ArrayList<Territory> territories = new ArrayList<Territory>();
    private static IFView view;

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public static ArrayList<Microbe> getMicrobes() {
        return microbes;
    }

    public static ArrayList<Food> getFood() {
        return food;
    }

    public static ArrayList<Territory> getTerritories() {
        return territories;
    }
    //</editor-fold>

    public GameController(View v) {
        for (int i = 0; i < 2; i++) {
            createMicrobe();
        }
        //Initialize the territories
        Territory t = new Territory(0, 0, 120, 120, 7, 5, 4, 6);
        territories.add(t);
        t = new Territory(120, 0, 120, 120, 3, 2, 1, 1);
        territories.add(t);
        t = new Territory(240, 0, 120, 120, 6, 4, 4, 4);
        territories.add(t);
        t = new Territory(0, 120, 120, 120, 1, 1, 1, 1);
        territories.add(t);
        t = new Territory(120, 120, 120, 120, 0, 0, 0, 0);
        t.setConquered();
        territories.add(t);
        t = new Territory(120, 240, 120, 120, 2, 1, 2, 1);
        territories.add(t);
        t = new Territory(0, 240, 120, 120, 9, 8, 5, 3);
        territories.add(t);
        t = new Territory(240, 120, 120, 120, 3, 2, 1, 1);
        territories.add(t);
        t = new Territory(240, 240, 120, 120, 10, 4, 6, 7);
        territories.add(t);
        view = v;

        //List of things to do on food creation
        ListenerExpert.addFoodCreationListener(new FoodCreationListener() {

            @Override
            public void foodCreation(FoodCreationEvent fde) {
                //Inform microbes to recalibrate
                for (Microbe m : microbes) {
                    m.targetNearestFood();
                }
            }
        });

        //List of things to do on food death
        ListenerExpert.addFoodDeathListener(new FoodDeathListener() {

            @Override
            public void foodDeath(FoodDeathEvent fde) {
                Food temp = (Food) fde.getSource();
                food.remove(temp);
                for (Microbe m : microbes) {

                    if (temp == m.getTarget()) {
                        m.setTarget(null);
                        m.targetNearestFood();
                    }
                }
            }
        });
        
        //List of things to do on territory conquered
        ListenerExpert.addTerritoryConqueredListener(new TerritoryConqueredListener() {
            @Override
            public void territoryConquered(TerritoryConqueredEvent tce){
                Territory temp = (Territory) tce.getSource();
                StatExpert.getInstance().addToMicrobeMax(temp.getAddMicrobeLimit());
                StatExpert.getInstance().addToFoodMax(temp.getAddFoodLimit());
                StatExpert.getInstance().addToFoodRate(temp.getAddFoodRate());
                StatExpert.getInstance().incTerritoryCount();
            }
        });

    }

    public static void createMicrobe() {
        if (StatExpert.getInstance().getMicrobeCount() < StatExpert.getInstance().getMicrobeMax()) {
            Microbe m = new Microbe();
            getMicrobes().add(m);
        }
    }

    public static void createMicrobe(Microbe m) {
        if (StatExpert.getInstance().getMicrobeCount() < StatExpert.getInstance().getMicrobeMax()) {
            getMicrobes().add(m);
        }
    }

    public static void createFood(int x, int y) {
        Food f = new Food(x, y);
        getFood().add(f);
        fireFoodCreationEvent(new FoodCreationEvent(f));
    }
    
    public static Territory findTerritory(int x, int y){
        Territory temp = null;
        for (Territory t : territories){
            if (t.contained(x, y)){
                temp = t;
            }
        }
        return temp;
    }

    private static void fireFoodCreationEvent(FoodCreationEvent fce) {
        Object[] listeners = ListenerExpert.getList().getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == FoodCreationListener.class) {
                ((FoodCreationListener) listeners[i + 1]).foodCreation(fce);
            }
        }
    }

    public static void mouseReleasedEvent(int x, int y) {
        for (Territory t : territories) {
            if (t.contained(x, y)) {
                if (t.isConquered()) {
                    if (StatExpert.getInstance().getFoodCount() > 0) {
                        Random generator = new Random();
                        int yOffset = (generator.nextInt(20)) - 10;
                        int xOffset = (generator.nextInt(20)) - 10;
                        int xPos = x - xOffset;
                        int yPos = y - yOffset;
                        GameController.createFood(xPos, yPos);
                    }
                }
                else {                    
                    int responseValue = JOptionPane.showConfirmDialog(null, "This land is available for conquering.\nWe estimate a loss of "+t.getInvadeReq()+" microbes in the process.\nDo you want to invade?", "Invasion!", JOptionPane.YES_NO_OPTION);
                    if (responseValue == JOptionPane.YES_OPTION){
                        if (StatExpert.getInstance().getMicrobeCount() > t.getInvadeReq())
                        {
                            for(int i = 0; i < t.getInvadeReq(); i++){
                                getMicrobes().remove(0);
                                StatExpert.getInstance().decMicrobeCount();
                            }
                            t.setConquered();
                            JOptionPane.showMessageDialog(null, "The attack was succesful! You have conquered the new territory!", "Invasion!", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "You do not have enough Microbes. The invasion has been aborted", "Invasion!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void run() {

        Timer timer = new Timer();

        //Check if microbes need move or mitosis
        TimerTask checkMicrobes = new TimerTask() {

            @Override
            public void run() {
                try {
                    for (Microbe m : microbes) {
                        m.move();
                        if (m.getXSize() > 20) {
                            m.mitosis();
                        }
                    }
                    view.update();

                } catch (ConcurrentModificationException cmex) {
                }
            }
        };

        //Check if food need to do anything
        TimerTask checkFood = new TimerTask() {

            @Override
            public void run() {
                try {
                    for (Food f : food) {
                        f.floatIdly();
                        f.incLife();
                    }
                } catch (ConcurrentModificationException cmex) {
                }
            }
        };
        //Increment the food counter at specific intervals
        TimerTask incrementFoodCounter = new TimerTask() {

            @Override
            public void run() {
                try {
                    StatExpert.getInstance().incFoodCount();
                } catch (ConcurrentModificationException cmex) {
                }
            }
        };
        //Run scheduled tasks
        timer.schedule(checkMicrobes, 0, 72);
        timer.schedule(checkFood, 0, 126);
        timer.schedule(incrementFoodCounter, 0, (60000 / StatExpert.getInstance().getFoodRate()));
    }
}