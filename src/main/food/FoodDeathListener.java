package main.food;

import java.util.EventListener;

public interface FoodDeathListener extends EventListener {
    public void foodDeath(FoodDeathEvent evt);
}
