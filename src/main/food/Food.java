/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.food;

import main.experts.ListenerExpert;
import main.experts.StatExpert;

/**
 *
 * @author Hopeful Llama
 */
public class Food {

    private int x, y, life;
    private final int maxLife = 39;
    private final int size = 5;

    //<editor-fold defaultstate="collapsed" desc="Getter and Setters">
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getLife() {
        return life;
    }
    //</editor-fold>

    public Food(int xPos, int yPos) {
        x = xPos;
        y = yPos;
        //Decrement food counter
        StatExpert.getInstance().decFoodCount();
    }

    public void floatIdly() {
        y++;
    }

    public void incLife() {
        life++;
        if (life > maxLife) {
            fireFoodDeathEvent(new FoodDeathEvent(this));
        }
    }

    public void eaten() {
        fireFoodDeathEvent(new FoodDeathEvent(this));
    }

    private void fireFoodDeathEvent(FoodDeathEvent fde) {
        Object[] listeners = ListenerExpert.getList().getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == FoodDeathListener.class) {
                ((FoodDeathListener) listeners[i + 1]).foodDeath(fde);
            }
        }
    }

}
