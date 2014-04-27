package main;

import java.awt.Color;
import java.util.Random;
import main.experts.StatExpert;
import main.food.Food;

public class Microbe {

    private Color colour;
    private int xSize, ySize, xPos, yPos;
    private Food target;
    //<editor-fold defaultstate="collapsed" desc="Getter and Setters">

    public Color getColour() {
        return colour;
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public Food getTarget() {
        return target;
    }

    public void setTarget(Food target) {
        this.target = target;
    }
    //</editor-fold>

    public Microbe() {
        Random generator = new Random();
        xSize = 10;
        ySize = 5;
        int randomNumber = generator.nextInt(120);
        xPos = randomNumber + 120;
        randomNumber = generator.nextInt(120);
        yPos = randomNumber + 120;
        randomNumber = generator.nextInt(6);
        switch (randomNumber) {
            case 0:
                colour = Color.red;
                break;
            case 1:
                colour = Color.yellow;
                break;
            case 2:
                colour = Color.cyan;
                break;
            case 3:
                colour = Color.orange;
                break;
            case 4:
                colour = Color.magenta;
                break;
            case 5:
                colour = Color.green;
                break;
            default:
                colour = null;
                break;
        }
        StatExpert.getInstance().incMicrobeCount();
    }

    public Microbe(Color c, int x, int y) {
        xSize = 10;
        ySize = 5;
        xPos = x + 3;
        yPos = y + 3;
        colour = c;
        StatExpert.getInstance().incMicrobeCount();
    }

    public void move() {
        try {
            if (target == null) {
                Random generator = new Random();
                int yOffset = (generator.nextInt(3)) - 1;
                int xOffset = (generator.nextInt(3)) - 1;
                attemptToMove("X", xPos+xOffset, yPos);
                attemptToMove("Y", xPos, yPos+yOffset);
            } else {
                //Moving left
                if (target.getX() < xPos) {
                    attemptToMove("X", xPos-1, yPos);
                } else if (target.getX() > xPos) {
                    attemptToMove("X", xPos+1, yPos);
                }
                if (target.getY() < yPos) {
                    attemptToMove("Y", xPos, yPos-1);
                } else if (target.getY() > yPos) {
                    attemptToMove("Y", xPos, yPos+1);
                }

                if (target.getX() == xPos && target.getY() == yPos) {
                    this.eat();
                }
            }
        } catch (NullPointerException nfex) {
        }
    }

    private void attemptToMove(String axis, int x, int y) {
        if (GameController.findTerritory(x, y).isConquered()) {
            if ("X".equals(axis)) {
                xPos = x;
            }
            else if ("Y".equals(axis)){
                yPos = y;
            }
            else {
            }
        }
    }

    public void targetNearestFood() {
        double lowestDistance = 100.00;
        for (Food f : GameController.getFood()) {
            double distance = Math.abs((((f.getX() - xPos) + (f.getY() - yPos)) / 2));
            if (distance < lowestDistance) {
                lowestDistance = distance;
                target = f;
            }
        }
    }

    public void eat() {
        target.eaten();
        target = null;
        xSize = xSize + 2;
        ySize++;
    }

    public void mitosis() {
        xSize = 10;
        ySize = 5;
        Microbe baby = new Microbe(colour, xPos, yPos);
        GameController.createMicrobe(baby);
    }
}
