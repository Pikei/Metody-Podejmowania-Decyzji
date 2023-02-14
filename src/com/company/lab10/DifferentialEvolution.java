package com.company.lab10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

public class DifferentialEvolution {

    private HashMap<Integer, Gene> genotype = new HashMap<>();
    private double[] challengers = new double[3];
    private final int populationNumber;
    Random rand = new Random();

    public DifferentialEvolution(int populationNumber) {
        this.populationNumber = populationNumber;
    }

    private void createPopulation() {
        for (int i = 0; i < populationNumber; i++) {
            double x = generateRandom();
            double y = generateRandom();
            double grade = func(x, y);
            genotype.put(i, new Gene(x, y, grade));
        }
    }

    private void tournament(int index) {
        double best;
        do {
        challengers[0] = genotype.get(rand.nextInt(genotype.size())).getGrade();
        challengers[1] = genotype.get(rand.nextInt(genotype.size())).getGrade();
        challengers[2] = genotype.get(rand.nextInt(genotype.size())).getGrade();
        best = Arrays.stream(challengers).min().getAsDouble();
        } while (genotype.get(index).getGrade() == best);

        double bestScore = best;
        IntStream.range(0,genotype.size()).filter(i -> genotype.get(i).getGrade() == bestScore).findFirst();


        for (int i = 0; i < genotype.size(); i++) {
            if (best == genotype.get(i).getGrade()) {
                double x = genotype.get(i).getX();
            }
        }

//        int x = genotype.get();
//
//        for (int i = 0; i < genotype.size(); i++) {
//
//        }
//
//        IntStream.range(0, challengers.length).filter(j -> rating[challengers[j]] == max).findFirst().ifPresent(i -> index = challengers[i]);
//        IntStream.range(0,genotype.size()).filter(j -> genotype.get(j).getGrade() == best).findFirst().ifPresent();
    }


    private double generateRandom() {
        return rand.nextDouble() * ((double) 100 - (double) -100) + (double) -100;
    }

    private double func(double x, double y) {
        return Math.pow(x - 3.14, 2) + Math.pow(y - 2.72, 2) + Math.sin(3 * x + 1.41) + Math.sin((4 * y - 1.73));
    }
}
