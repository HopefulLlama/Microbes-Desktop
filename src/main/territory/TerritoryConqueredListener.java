package main.territory;

import java.util.EventListener;

public interface TerritoryConqueredListener extends EventListener{
    public void territoryConquered(TerritoryConqueredEvent evt);    
}
