package com.company.lab9;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Pso {

    private HashMap<Integer, Particle> swarm = new HashMap<>();
    private Random rand = new Random();


    public Pso() {
        createSwarm();
    }

    private void createSwarm() {
        double x,y;
        double[] personalBest = new double[2];
        double[] globalBest = new double[2];
        double[] v = new double[2];
        for (int i = 0; i < 20; i++) {
            x = randomize(-100, 100);
            y = randomize(-100, 100);
            personalBest[0] = x;
            personalBest[1] = y;
            globalBest[0] = 1;
            globalBest[1] = 1;
            v[0] = randomize(-1,1);
            v[1] = randomize(-1,1);
            swarm.put(i,new Particle(func(x,y), x,y, personalBest, globalBest, v));
        }
        updateGlobalBest();



        swarm.values().stream().forEach(System.out::println);
    }

    private double[] findGlobalBest() {
        int size = swarm.size();
        double max = swarm.get(0).getRating();
        double[] best = new double[2];
        for (int i = 0; i < size; i++) {
            if(swarm.get(i).getRating() > max) {
                max = swarm.get(i).getRating();
                best[0] = swarm.get(i).getX();
                best[1] = swarm.get(i).getY();
            }
        }
        return best;
    }

    private void updateGlobalBest() {
        for (int i = 0; i < swarm.size(); i++) {
            swarm.get(i).setGlobalBest(findGlobalBest());
        }
    }

    private double randomize(double min, double max) {
        return rand.nextDouble() * (max - min) + min;
    }

    private double func(double x, double y) {
        return Math.pow(x - 3.14, 2) + Math.pow(y - 2.72, 2) + Math.sin(3*x + 1.41) + Math.sin((4*y - 1.76));
    }

//    private double[] vector() {
//
//    }
}
