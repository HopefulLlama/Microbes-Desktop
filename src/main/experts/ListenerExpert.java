/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.experts;

import javax.swing.event.EventListenerList;
import main.food.FoodCreationListener;
import main.food.FoodDeathListener;
import main.territory.TerritoryConqueredListener;

public class ListenerExpert {

    private static EventListenerList list = new EventListenerList();

    public static EventListenerList getList() {
        return list;
    }

    //<editor-fold defaultstate="collapsed" desc="FoodDeath Stuff">
    public static void addFoodDeathListener(FoodDeathListener fdl) {
        getList().add(FoodDeathListener.class, fdl);
    }

    public static void removeFoodDeathListener(FoodDeathListener fdl) {
        getList().remove(FoodDeathListener.class, fdl);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="FoodCreation Stuff">

    public static void addFoodCreationListener(FoodCreationListener fcl) {
        getList().add(FoodCreationListener.class, fcl);
    }

    public static void removeFoodDeathListener(FoodCreationListener fcl) {
        getList().remove(FoodCreationListener.class, fcl);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="TerritoryConquered Stuff">

    public static void addTerritoryConqueredListener(TerritoryConqueredListener tcl) {
        getList().add(TerritoryConqueredListener.class, tcl);
    }

    public static void removeTerritoryConqueredListener(TerritoryConqueredListener tcl) {
        getList().remove(TerritoryConqueredListener.class, tcl);
    }
    //</editor-fold>
}
