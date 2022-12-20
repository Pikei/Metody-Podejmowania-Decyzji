package com.company.lab9;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Pso {

    private HashMap<Integer, Particle> swarm = new HashMap<>();
    private Random rand = new Random();
    private double globalBest;

    public Pso() {
        createSwarm();
    }

    private void createSwarm() {
        double x,y;
        double[] personalBest = new double[2];

        for (int i = 0; i < 20; i++) {
            x = randomize(-100, 100);
            y = randomize(-100, 100);
            personalBest[0] = x;
            personalBest[1] = y;

            swarm.put(i,new Particle(func(x,y), x,y, personalBest));
        }

        swarm.values().stream().forEach(System.out::println);
    }

    private double randomize(double min, double max) {
        return rand.nextDouble() * (max - min) + min;
    }

    private double func(double x, double y) {
        return Math.pow(x - 3.14, 2) + Math.pow(y - 2.72, 2) + Math.sin(3*x + 1.41) + Math.sin((4*y - 1.76));
    }
}
