package main.db.entities;

public class Specs {
    private int id;
    private int crew;
    private double len;
    private double wingsSpan;
    private double height;
    private int emptyWeight;
    private int maxWeight;
    private int weight;
    private int speed;
    private int range;
    private int ceiling;
    private int combatRange;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public double getLen() {
        return len;
    }

    public void setLen(double len) {
        this.len = len;
    }

    public double getWingsSpan() {
        return wingsSpan;
    }

    public void setWingsSpan(double wingsSpan) {
        this.wingsSpan = wingsSpan;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(int emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getCeiling() {
        return ceiling;
    }

    public void setCeiling(int ceiling) {
        this.ceiling = ceiling;
    }

    public int getCombatRange() {
        return combatRange;
    }

    public void setCombatRange(int combatRange) {
        this.combatRange = combatRange;
    }

    @Override
    public String toString() {
        return String.format(
                "Specs[id = %d, crew = %d, len = %f, wingsSpan = %f, height = %f, " +
                        "emptyWeight = %d, maxWeight = %d, weight = %d, speed = %d, " +
                        "range = %d, ceiling = %d, combatRange = %d]",
                id, crew, len, wingsSpan, height, emptyWeight, maxWeight, weight,
                speed, range, ceiling, combatRange
        );
    }
}
