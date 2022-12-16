package com.company.lab8;


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class AlgorytmGenetyczny {
    private final HashMap<Integer, double[]> populacja = new HashMap<>(100);
    private final HashMap<Integer, double[]> rodzice = new HashMap<>(100);
    private final double[] ocena = new double[100];
    private final int[] battleRoyal = new int[3];
    private final Random rand = new Random();
    int index;
    private final double[] monkey;
    private double[] monkey2;

    public AlgorytmGenetyczny() {
        createPopulation();
        monkey = populacja.get(tabIndex(ocena));
        if (func(monkey) <= 1.2 && func(monkey) >= 0.8) {
            System.out.println(Arrays.toString(monkey));
            return;
        }
        start();
    }

    private void start() {
//        printPopulation();
        for (int x = 0; x < 100; x++) {
            for (int i = 0; i < 100; i++) {
                vDolce();
                addNewParent(i);
            }
            for (int i = 0; i < 100; i += 2) {
                if (lotto() <= 0.4) {
                    tenTego(i, i + 1);
                }
            }
            IntStream.range(0, 100).filter(i -> lotto() <= 0.02).forEachOrdered(i -> {
                mutate(i);
                ocena[i] = func(rodzice.get(i));
            });
            populacja.putAll(rodzice);
            monkey2 = populacja.get(tabIndex(ocena));

            if (func(monkey2) < func(monkey)) {
                populacja.put(getKey(), monkey);
            }
            if (func(monkey) <= 1.2 && func(monkey) >= 0.8) {
                System.out.println(Arrays.toString(monkey));
                return;
            }
            printParents();
        }
        System.out.println(Arrays.toString(monkey));
        System.out.println(func(monkey));

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
//        for (int j : battleRoyal) {
//            printChallengers(j);
//        }
    }

    private void addNewParent(int indexOfParent) {
        double max = findNewParent();
        IntStream.range(0, battleRoyal.length).filter(i -> ocena[battleRoyal[i]] == max).findFirst().ifPresent(i -> index = battleRoyal[i]);
        rodzice.put(indexOfParent, populacja.get(index));
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
        IntStream.range(0, rodzice.size()).mapToObj(i -> "index: " + i + " tab: " + Arrays.toString(rodzice.get(i))).forEach(System.out::println);
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
        rodzice.remove(index1);
        rodzice.remove(index2);
        rodzice.put(index1, temp1);
        rodzice.put(index2, temp2);
    }

    private void mutate(int index) {
        for (int i = 0; i < populacja.get(index).length; i++) {
            populacja.get(index)[i] = populacja.get(index)[i] + (Math.random() * (0.02 - (-0.02)) + (-0.02));
        }
    }

    private int tabIndex(double[] x) {
        double max = Arrays.stream(x).max().getAsDouble();
        return IntStream.range(0, x.length).filter(i -> x[i] == max).findFirst().orElse(-1);
    }

    private int getKey() {
        return IntStream.range(0, 100).filter(i -> populacja.get(i) == monkey2).findFirst().orElse(-1);
    }
}