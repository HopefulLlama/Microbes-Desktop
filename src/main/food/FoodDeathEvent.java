package main.food;

import java.util.EventObject;

public class FoodDeathEvent extends EventObject {
    public FoodDeathEvent(Object source){
        super(source);
    }        
}
