package main.territory;

import main.experts.ListenerExpert;

public class Territory {

    private int xPos, yPos, width, height, invadeReq, addMicrobeLimit, addFoodLimit, addFoodRate;
    private boolean conquered;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getInvadeReq() {
        return invadeReq;
    }

    public int getAddMicrobeLimit() {
        return addMicrobeLimit;
    }

    public int getAddFoodLimit() {
        return addFoodLimit;
    }

    public int getAddFoodRate() {
        return addFoodRate;
    }

    public boolean isConquered() {
        return conquered;
    }

    public void setConquered() {
        this.conquered = true;
        fireTerritoryConqueredEvent(new TerritoryConqueredEvent(this));
    }
    //</editor-fold>

    public Territory(int x, int y, int w, int h, int i, int m, int f, int fr) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        invadeReq = i;
        addMicrobeLimit = m;
        addFoodLimit = f;
        addFoodRate = fr;
    }

    public boolean contained(int x, int y) {
        if (x >= getxPos() && x <= getxPos() + getWidth() && y >= getyPos() && y <= getyPos() + getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean xContained(int x) {
        if (x >= getxPos() && x <= getxPos() + getWidth()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean yContained(int y) {
        if (y >= getyPos() && y <= getyPos() + getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    private static void fireTerritoryConqueredEvent(TerritoryConqueredEvent tce) {
        Object[] listeners = ListenerExpert.getList().getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == TerritoryConqueredListener.class) {
                ((TerritoryConqueredListener) listeners[i + 1]).territoryConquered(tce);
            }
        }
    }
}
