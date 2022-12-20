package com.company.lab8;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class AlgorytmGenetyczny {
    private final HashMap<Integer, double[]> population = new HashMap<>(100);
    private final HashMap<Integer, double[]> parents = new HashMap<>(100);
    private final double[] rating = new double[100];
    private final int[] challengers = new int[3];
    private final Random rand = new Random();
    private final double[] strongestIndividual;
    private double[] newStrongestIndividual;
    private int index;

    public AlgorytmGenetyczny() {
        createPopulation();
        strongestIndividual = population.get(tabIndex(rating));
        if (func(strongestIndividual) <= 1.2 && func(strongestIndividual) >= 0.8) {
            System.out.println(Arrays.toString(strongestIndividual));
            return;
        }
        start();
    }

    private void start() {
//        printPopulation();
        for (int x = 0; x < 100; x++) {
            for (int i = 0; i < 100; i++) {
                tournament();
                addNewParent(i);
            }
            for (int i = 0; i < 100; i += 2) {
                if (lotto() <= 0.4) {
                    tenTego(i, i + 1);
                }
            }
            IntStream.range(0, 100).filter(i -> lotto() <= 0.02).forEachOrdered(i -> {
                mutate(i);
                rating[i] = func(parents.get(i));
            });
            population.putAll(parents);
            newStrongestIndividual = population.get(tabIndex(rating));

            if (func(newStrongestIndividual) < func(strongestIndividual)) {
                population.put(getKey(), strongestIndividual);
            }
            if (func(strongestIndividual) <= 1.2 && func(strongestIndividual) >= 0.8) {
                System.out.println(Arrays.toString(strongestIndividual));
                return;
            }
            printParents();
        }
        System.out.println(Arrays.toString(strongestIndividual));
        System.out.println(func(strongestIndividual));

    }

    private void createPopulation() {
        double[] temp;
        for (int i = 0; i < 100; i++) {
            temp = generateRandom(-100, 100);
            rating[i] = func(temp);
            population.put(i, temp);
        }
    }

    private void tournament() {
        IntStream.range(0, 3).forEach(i -> challengers[i] = rand.nextInt(100));
        Arrays.sort(challengers);
    }

    private void addNewParent(int indexOfParent) {
        double max = findNewParent();
        IntStream.range(0, challengers.length).filter(i -> rating[challengers[i]] == max).findFirst().ifPresent(i -> index = challengers[i]);
        parents.put(indexOfParent, population.get(index));
    }

    private double findNewParent() {
        double[] temp = {rating[challengers[0]], rating[challengers[1]], rating[challengers[2]]};
        return Arrays.stream(temp).max().getAsDouble();
    }

    private void printPopulation() {
        for (int i = 0; i < 100; i++) {
            System.out.print("inteks: " + i + " tablica: [");
            for (int j = 0; j < population.get(i).length; j++) {
                System.out.print(population.get(i)[j] + " ");
            }
            System.out.println("] ocena: " + rating[i]);
        }
    }

    private void printChallengers(int index) {
        System.out.print("indeks: " + index + " tablica: [");
        for (int j = 0; j < population.get(index).length; j++) {
            System.out.print(population.get(index)[j] + " ");
        }
        System.out.println("] ocena: " + rating[index]);
    }

    private void printParents() {
        IntStream.range(0, parents.size()).mapToObj(i -> "index: " + i + " tab: " + Arrays.toString(parents.get(i))).forEach(System.out::println);
    }


    private double[] generateRandom(double min, double max) {
        double[] temp = new double[4];
        Arrays.setAll(temp, i -> ((ThreadLocalRandom.current().nextDouble() * (max - min)) + min));
        return temp;
    }

    private double func(double[] x) {
        return ((Math.sin(x[0]) * Math.cos(x[1])) / (Math.pow(x[2], 2) * Math.pow(x[3], 2) + 1)) * -1;
    }

    private double lotto() {
        return rand.nextDouble(1);
    }

    private void tenTego(int index1, int index2) {
        double[] temp1 = {parents.get(index1)[0], parents.get(index1)[1], parents.get(index2)[2], parents.get(index2)[3]};
        double[] temp2 = {parents.get(index2)[0], parents.get(index2)[1], parents.get(index1)[2], parents.get(index1)[3]};
        parents.remove(index1);
        parents.remove(index2);
        parents.put(index1, temp1);
        parents.put(index2, temp2);
    }

    private void mutate(int index) {
        for (int i = 0; i < population.get(index).length; i++) {
            population.get(index)[i] = population.get(index)[i] + (Math.random() * (0.02 - (-0.02)) + (-0.02));
        }
    }

    private int tabIndex(double[] x) {
        double max = Arrays.stream(x).max().getAsDouble();
        return IntStream.range(0, x.length).filter(i -> x[i] == max).findFirst().orElse(-1);
    }

    private int getKey() {
        return IntStream.range(0, 100).filter(i -> population.get(i) == newStrongestIndividual).findFirst().orElse(-1);
    }
}