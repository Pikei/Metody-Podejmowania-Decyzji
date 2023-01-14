package com.company.lab9;

import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

public class Pso {

    private final HashMap<Integer, Particle> swarm = new HashMap<>();
    private final Random rand = new Random();

    public Pso() {
        createSwarm();
        do {
            IntStream.range(0, swarm.size()).forEach(i -> swarm.get(i).setGlobalBest(findGlobalBest()));
            IntStream.range(0, swarm.size()).forEach(i -> swarm.get(i).setV(velocity(i)));
            moveParticle();
        } while(!exit());
        print();
    }

    private void print() {
        IntStream.range(0, swarm.size()).forEach(i -> {
            System.out.println("---------------------------");
            System.out.println("Particle: " + (i+1));
            System.out.println("X: " + swarm.get(i).getX());
            System.out.println("Y: " + swarm.get(i).getY());
            System.out.println("Grade: " + swarm.get(i).getRating());
            System.out.println("V: [" + swarm.get(i).getV()[0] + ", " + swarm.get(i).getV()[1] + "]");
            System.out.println("Personal best: [" + swarm.get(i).getPersonalBest()[0] + ", " + swarm.get(i).getPersonalBest()[1] + "]");
            System.out.println("Global best: [" + swarm.get(i).getGlobalBest()[0] + ", " + swarm.get(i).getGlobalBest()[1] + "]");
            System.out.println("---------------------------");
        });
    }

    private void createSwarm() {
        double x, y;
        for (int i = 0; i < 50; i++) {
            x = randomize(-100, 100);
            y = randomize(-100, 100);
            double[] personalBest = {x, y};
            double[] globalBest = {1, 1};
            double[] v = {randomize(-1, 1), randomize(-1, 1)};
            swarm.put(i, new Particle(func(x, y), x, y, personalBest, globalBest, v));
        }
    }

    private double[] findGlobalBest() {
        int size = swarm.size();
        double max = swarm.get(0).getRating();
        double[] best = new double[2];
        for (int i = 0; i < size; i++) {
            if (swarm.get(i).getRating() < max) {
                max = swarm.get(i).getRating();
                best[0] = swarm.get(i).getX();
                best[1] = swarm.get(i).getY();
            }
        }
        return best;
    }

    private void moveParticle() {
        updatePosition();
        updatePersonalBest();
        updateGlobalBest();
    }

    private void updatePosition() {
        IntStream.range(0, swarm.size()).forEach(i -> swarm.get(i).setX(swarm.get(i).getX() + swarm.get(i).getV()[0]));
        IntStream.range(0, swarm.size()).forEach(i -> swarm.get(i).setY(swarm.get(i).getY() + swarm.get(i).getV()[1]));
        IntStream.range(0, swarm.size()).forEach(i -> swarm.get(i).setRating(func(swarm.get(i).getX(), swarm.get(i).getY())));
    }

    private void updatePersonalBest() {
        IntStream.range(0, swarm.size()).filter(i -> func(swarm.get(i).getPersonalBest()[0], swarm.get(i).getPersonalBest()[1]) > swarm.get(i).getRating()).forEach(i -> {
            double[] temp = new double[2];
            temp[0] = swarm.get(i).getX();
            temp[1] = swarm.get(i).getY();
            swarm.get(i).setPersonalBest(temp);
        });
    }

    private void updateGlobalBest() {
        for (int i = 0; i < swarm.size(); i++) {
            if (func(swarm.get(i).getGlobalBest()[0], swarm.get(i).getGlobalBest()[1]) > func(swarm.get(i).getPersonalBest()[0], swarm.get(i).getPersonalBest()[1])) {
                for (int j = 0; j < swarm.size(); j++) {
                    swarm.get(j).setGlobalBest(swarm.get(i).getPersonalBest());
                }
            }
        }
    }

    private double[] velocity(int i) {
        double[] v = new double[2];
        v[0] = xVelocity(i);
        v[1] = yVelocity(i);
        return v;
    }

    private double xVelocity(int i) {
        return (0.8 * swarm.get(i).getV()[0]) +
                (0.2 * Math.random() * (swarm.get(i).getPersonalBest()[0] - swarm.get(i).getX())) +
                (0.2 * Math.random() * (swarm.get(i).getGlobalBest()[0] - swarm.get(i).getX()));
    }

    private double yVelocity(int i) {
        return (0.8 * swarm.get(i).getV()[0]) +
                (0.2 * Math.random() * (swarm.get(i).getPersonalBest()[1] - swarm.get(i).getY())) +
                (0.2 * Math.random() * (swarm.get(i).getGlobalBest()[1] - swarm.get(i).getY()));
    }

    private boolean exit() {
        int counter = 0;
        for (int i = 0; i < swarm.size(); i++) {
            if (Math.abs(swarm.get(i).getX() - swarm.get(i).getGlobalBest()[0]) < 0.02 && Math.abs(swarm.get(i).getY() - swarm.get(i).getGlobalBest()[1]) < 0.02) {
                counter ++;
            } else {
                counter --;
            }
        }
        return counter == swarm.size();
    }

    private double randomize(double min, double max) {
        return rand.nextDouble() * (max - min) + min;
    }

    private double func(double x, double y) {
        return Math.pow(x - 3.14, 2) + Math.pow(y - 2.72, 2) + Math.sin(3 * x + 1.41) + Math.sin((4 * y - 1.76));
    }
}