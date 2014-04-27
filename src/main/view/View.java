package main.view;

import java.util.Timer;
import java.util.TimerTask;
import main.experts.ListenerExpert;
import main.experts.StatExpert;
import main.food.FoodCreationEvent;
import main.food.FoodCreationListener;

public class View extends javax.swing.JFrame implements Runnable, IFView {

    public View() {
        initComponents();
    }

    @Override
    public void run() {
        this.setVisible(true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                canvas.repaint();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 16);
        
        ListenerExpert.addFoodCreationListener(new FoodCreationListener() {

            @Override
            public void foodCreation(FoodCreationEvent fde) {
                updateFoodLabel();
            }
        });
    }
    
    @Override
    public void update(){
        updateTerritoriesLabel();
        updateMicrobesLabel();
        updateFoodLabel();
    }
    
    private void updateTerritoriesLabel() {
        lblTerritories.setText("Territories: " + StatExpert.getInstance().getTerritoryCount() + "/" + StatExpert.getInstance().getTerritoryMax());
    }

    private void updateMicrobesLabel() {
        lblMicrobes.setText("Microbes: " + StatExpert.getInstance().getMicrobeCount() + "/" + StatExpert.getInstance().getMicrobeMax());
    }

    private void updateFoodLabel() {
        lblFood.setText("Food: " + StatExpert.getInstance().getFoodCount() + "/" + StatExpert.getInstance().getFoodMax() + " (+" + StatExpert.getInstance().getFoodRate() + ")");
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas = new main.view.GameCanvas();
        pnlStats = new javax.swing.JPanel();
        lblTerritories = new javax.swing.JLabel();
        lblMicrobes = new javax.swing.JLabel();
        lblFood = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Microbes");
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(365, 410));
        setMinimumSize(new java.awt.Dimension(365, 410));
        setPreferredSize(new java.awt.Dimension(365, 410));
        setResizable(false);

        canvas.setBackground(new java.awt.Color(0, 0, 0));
        canvas.setMaximumSize(new java.awt.Dimension(360, 360));
        canvas.setMinimumSize(new java.awt.Dimension(360, 360));
        canvas.setPreferredSize(new java.awt.Dimension(360, 360));

        pnlStats.setMaximumSize(new java.awt.Dimension(350, 40));
        pnlStats.setMinimumSize(new java.awt.Dimension(350, 40));
        pnlStats.setPreferredSize(new java.awt.Dimension(350, 40));
        pnlStats.setLayout(new java.awt.GridLayout(1, 0));

        lblTerritories.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTerritories.setText("Territories: 1/9");
        pnlStats.add(lblTerritories);

        lblMicrobes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMicrobes.setText("Microbes: 0/20");
        pnlStats.add(lblMicrobes);

        lblFood.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFood.setText("Food: 20/20 (+1)");
        pnlStats.add(lblFood);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlStats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(canvas, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlStats, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.view.GameCanvas canvas;
    private javax.swing.JLabel lblFood;
    private javax.swing.JLabel lblMicrobes;
    private javax.swing.JLabel lblTerritories;
    private javax.swing.JPanel pnlStats;
    // End of variables declaration//GEN-END:variables
}
