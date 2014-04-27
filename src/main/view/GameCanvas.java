package main.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import main.GameController;
import main.Microbe;
import main.food.Food;
import main.territory.Territory;

public class GameCanvas extends JPanel implements MouseListener {

    public GameCanvas() {
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.setBackground(Color.gray);
        for (Territory t : GameController.getTerritories()){
            if(!t.isConquered()){
                g.setColor(Color.gray);
                g.fillRect(t.getxPos(), t.getyPos(), t.getWidth(), t.getHeight());
                g.setColor(Color.black);
                g.drawRect(t.getxPos(), t.getyPos(), t.getWidth(), t.getHeight());
            }
            else{
                g.setColor(Color.black);
                g.fillRect(t.getxPos(), t.getyPos(), t.getWidth(), t.getHeight());
            }
        }
        for (Microbe m : GameController.getMicrobes()) {
            g.setColor(m.getColour());
            g.drawOval(m.getXPos(), m.getYPos(), m.getXSize(), m.getYSize());
        }
        for (Food f : GameController.getFood()) {
            g.setColor(Color.white);
            g.fillOval(f.getX(), f.getY(), f.getSize(), f.getSize());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        GameController.mouseReleasedEvent(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
