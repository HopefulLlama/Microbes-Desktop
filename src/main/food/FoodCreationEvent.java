package main.food;

import java.util.EventObject;

public class FoodCreationEvent extends EventObject {
    public FoodCreationEvent(Object source){
        super(source);
    }        
}

