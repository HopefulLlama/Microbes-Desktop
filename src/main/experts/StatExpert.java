package main.experts;

public class StatExpert {

    private static StatExpert singleton;
    private int microbeCount, microbeMax, foodCount, foodMax, foodRate, territoryCount, territoryMax;
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public int getMicrobeCount() {
        return microbeCount;
    }

    public void incMicrobeCount() {
        microbeCount++;
    }

    public void decMicrobeCount() {
        microbeCount--;
    }

    public int getMicrobeMax() {
        return microbeMax;
    }

    public void addToMicrobeMax(int aMicrobeMax) {
        microbeMax += aMicrobeMax;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void incFoodCount() {
        if (foodCount < foodMax) {
            foodCount++;
        }
    }

    public void decFoodCount() {
        foodCount--;
    }

    public int getFoodMax() {
        return foodMax;
    }

    public void addToFoodMax(int aFoodMax) {
        foodMax += aFoodMax;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void addToFoodRate(int aFoodRate) {
        foodRate += aFoodRate;
    }

    public int getTerritoryCount() {
        return territoryCount;
    }

    public void incTerritoryCount() {
        territoryCount++;
    }

    public void decTerritoryCount() {
        territoryCount--;
    }

    public int getTerritoryMax() {
        return territoryMax;
    }

    public void addToTerritoryMax(int aTerritoryMax) {
        territoryMax += aTerritoryMax;
    }
    //</editor-fold>

    private StatExpert() {
        microbeCount = 0;
        microbeMax = 5;
        foodCount = 5;
        foodMax = 10;
        foodRate = 4;
        territoryCount = 1;
        territoryMax = 9;
    }

    public static StatExpert getInstance() {
        if (singleton == null) {
            singleton = new StatExpert();
        }
        return singleton;
    }
}
