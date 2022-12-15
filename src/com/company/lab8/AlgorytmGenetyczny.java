package com.company.lab8;


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class AlgorytmGenetyczny {
    //    ArrayList<Rodzice> rodzice1 =new ArrayList<>();
//    HashMap<Integer, ArrayList<>> populacja2 = new HashMap<>(100);
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

//        for (int i = 0; i < 100; i++) {

        for (int j = 0; j < 2; j++) {

            vDolce();

            addNewParent();

        }
            System.out.println();
            printParents();
            System.out.println();
            if (lotto() <= 0.4) {
                tenTego(0, 1);
                System.out.println();
                printParents();
            }
//        }
    }

    private void createPopulation() {
        double[] temp;
        for (int i = 0; i < 100; i++) {
            temp = generateRandom(-100, 100);
            ocena[i] = func(temp);
            populacja.put(i, temp);
        }
    }

    private void vDolce() {
        IntStream.range(0, 3).forEach(i -> battleRoyal[i] = rand.nextInt(100));
        Arrays.sort(battleRoyal);
        for (int j : battleRoyal) {
            printChallengers(j);
        }
    }

    private void addNewParent() {
        double max = findNewParent();
        IntStream.range(0, battleRoyal.length).filter(i -> ocena[battleRoyal[i]] == max).findFirst().ifPresent(i -> index = battleRoyal[i]);
        rodzice.add(populacja.get(index));
    }

    private double findNewParent() {
        double[] temp = {ocena[battleRoyal[0]], ocena[battleRoyal[1]], ocena[battleRoyal[2]]};
        return Arrays.stream(temp).max().getAsDouble();
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

    private void printParents() {
        rodzice.stream().map(Arrays::toString).forEach(System.out::println);
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
        double[] temp1 = {rodzice.get(index1)[0], rodzice.get(index1)[1], rodzice.get(index2)[2], rodzice.get(index2)[3]};
        double[] temp2 = {rodzice.get(index2)[0], rodzice.get(index2)[1], rodzice.get(index1)[2], rodzice.get(index1)[3]};

        rodzice.retainAll(rodzice);
        rodzice.add(index1, temp1);
        rodzice.add(index2, temp2);
    }

    private void mutate() {

    }


}
