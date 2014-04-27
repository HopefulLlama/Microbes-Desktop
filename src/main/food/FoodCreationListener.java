package main.food;

import java.util.EventListener;

public interface FoodCreationListener extends EventListener {
    public void foodCreation(FoodCreationEvent evt);
}
