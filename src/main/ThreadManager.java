/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import main.view.View;

/**
 *
 * @author Hopeful Llama
 */
public class ThreadManager {  
    public static void main(String args[]) {
        View view = new View();
        new Thread(view).start();
        GameController gc = new GameController(view);
        new Thread(gc).start();
    }
}
