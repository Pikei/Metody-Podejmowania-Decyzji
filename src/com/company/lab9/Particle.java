package com.company.lab9;

public class Particle {
    private double rating;
    private double x, y;
    private double[] personalBest;
    private double[] globalBest;
    private double[] v;

    public Particle(double rating, double x, double y, double[] personalBest, double[] globalBest, double[] v) {
        this.rating = rating;
        this.x = x;
        this.y = y;
        this.personalBest = personalBest;
        this.globalBest = globalBest;
        this.v = v;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double[] getPersonalBest() {
        return personalBest;
    }

    public void setPersonalBest(double[] personalBest) {
        this.personalBest = personalBest;
    }

    public double[] getGlobalBest() {
        return globalBest;
    }

    public void setGlobalBest(double[] globalBest) {
        this.globalBest = globalBest;
    }

    public double[] getV() {
        return v;
    }

    public void setV(double[] v) {
        this.v = v;
    }
}
