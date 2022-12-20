package com.company.lab9;

public class Particle extends Pso{
    private double rating;
    private double x, y;
    private double[] personalBest;

    public Particle(double rating, double x, double y, double[] personalBest) {
        this.rating = rating;
        this.x = x;
        this.y = y;
        this.personalBest = personalBest;
    }
}
