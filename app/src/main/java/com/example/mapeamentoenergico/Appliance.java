package com.example.mapeamentoenergico;

public class Appliance {
    private String name;
    private double consumption, timeAveraage, energicSpent;

    public Appliance(String name, double consumption, double timeAveraage, double energicSpent) {
        this.name = name;
        this.consumption = consumption;
        this.timeAveraage = timeAveraage;
        this.energicSpent = energicSpent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getTimeAveraage() {
        return timeAveraage;
    }

    public void setTimeAveraage(double timeAveraage) {
        this.timeAveraage = timeAveraage;
    }

    public double getEnergicSpent() {
        return energicSpent;
    }

    public void setEnergicSpent(double energicSpent) {
        this.energicSpent = energicSpent;
    }
}
