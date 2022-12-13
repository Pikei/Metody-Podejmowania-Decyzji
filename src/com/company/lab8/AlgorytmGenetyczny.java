package com.company.lab8;


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class AlgorytmGenetyczny {
    private final HashMap<Integer, double[]> populacja = new HashMap<>(100);
    private final ArrayList<double[]> rodzice = new ArrayList<>(50);
    private final double[] ocena = new double[100];
    private final int[] battleRoyal = new int[3];
    private final Random rand = new Random();
    int index;

    public AlgorytmGenetyczny() {
        createPopulation();
        start();
    }

    private void start() {
//        printPopulation();
        for (int x = 0; x < 2; x++) {

            for (int i = 0; i < 3; i++) {
                battleRoyal[i] = rand.nextInt(100);
            }
            Arrays.sort(battleRoyal);
            for (int j : battleRoyal) {
                printChallengers(j);
            }
            System.out.println(findNewParent(battleRoyal));
            rodzice.add(populacja.get(findNewParent(battleRoyal)));
            System.out.println();
            System.out.println();
//        for (int i = 0; i < rodzice.size(); i++) {
//            rodzice.add(populacja.get(findNewParent(battleRoyal)));
//        }

        }
        System.out.println();
        System.out.println();
        for (int i = 0; i < rodzice.size(); i++) {
            System.out.println(Arrays.toString(rodzice.get(i)));
        }

    }

    private void createPopulation() {
        double[] temp;
        for (int i = 0; i < 100; i++) {
            temp = generateRandom(-100, 100);
            ocena[i] = func(temp);
            populacja.put(i, temp);
        }
    }

    private int findNewParent(int []indeksRodzica) {
        double max = ocena[indeksRodzica[0]];
        int index = indeksRodzica[0];
        for (int i = 0; i < 3; i++) {
            if(ocena[indeksRodzica[i]] > max) {
                max = ocena[indeksRodzica[i]];
                index = indeksRodzica[i];
            }
        }
        return index;
//
//
//        double []temp = new double[3];
//        temp[0] = ocena[indeksRodzica[0]];
//        temp[1] = ocena[indeksRodzica[1]];
//        temp[2] = ocena[indeksRodzica[2]];
//
//        for (int i = 0; i < temp.length; i++) {
//            if (max < temp[i]) {
//                max = temp[i];
//            }
//        }
//
//
//
//
//        for (int j : battleRoyal) {
//            if (ocena[j] > max) {
//                max = ocena[j];
//                indeksRodzica = j;
//            }
//
//        }
//        return indeksRodzica;
    }

    private void printPopulation() {
        for (int i = 0; i < 100; i++) {
            System.out.print("inteks: " + i + " tablica: [");
            for (int j = 0; j < populacja.get(i).length; j++) {
                System.out.print(populacja.get(i)[j] + " ");
            }
            System.out.println("] ocena: " + ocena[i]);
        }
    }

    private void printChallengers(int index) {
        System.out.print("indeks: " + index + " tablica: [");
        for (int j = 0; j < populacja.get(index).length; j++) {
            System.out.print(populacja.get(index)[j] + " ");
        }
        System.out.println("] ocena: " + ocena[index]);
    }


    private double[] generateRandom(double min, double max) {
        double[] temp = new double[4];
        Arrays.setAll(temp, i -> ((ThreadLocalRandom.current().nextDouble() * (max - min)) + min));
        return temp;
    }

    private double func(double[] x) {
        return ((Math.sin(x[0]) * Math.cos(x[1])) / (Math.pow(x[2], 2) * Math.pow(x[3], 2) + 1)) * -1;
    }

}
