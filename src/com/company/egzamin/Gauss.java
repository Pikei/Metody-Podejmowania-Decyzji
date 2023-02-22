package com.company.egzamin;

import java.util.Arrays;

public class Gauss {
    public Gauss() {
        start();
    }

    private double f(double[] x){
        return 3 * Math.pow(x[0], 4) + 5 * Math.pow(x[1], 3);
    }

    private double fPrim(double[] x){
        return 12 * Math.pow(x[0], 3) + 10 * Math.pow(x[1], 2);
    }

    private double pochodnaX1(double x){
        return 12 * Math.pow(x, 3);
    }

    private double pochodnaX2(double x){
        return 10 * Math.pow(x, 2);
    }

    private void start() {
        double[] x = new double[2];
        Arrays.fill(x,1);
        double krok = 2;
        printStart(x, krok);
        System.out.println("=========== P1 ================");
        double nowyKrok = krok - (Math.pow(pochodnaX1(x[0]+krok), 2) / (pochodnaX1(krok + pochodnaX1(x[0]+krok))-pochodnaX1(x[0]+krok)));
        System.out.println("nowy krok dla x1 wynisi: " + nowyKrok);
        x[0] += nowyKrok;
        System.out.println("punkt P1: ");
        Arrays.stream(x).forEach(System.out::println);

        System.out.println("=========== P2 ================");
        nowyKrok = krok - (Math.pow(pochodnaX2(x[1]+krok), 2) / (pochodnaX2(krok + pochodnaX2(x[1]+krok))-pochodnaX2(x[1]+krok)));
        System.out.println("nowy krok dla x2 wynisi: " + nowyKrok);
        x[1] += nowyKrok;
        System.out.println("punkt P2: ");
        Arrays.stream(x).forEach(System.out::println);

    }

    private void printStart(double[]x, double krok) {
        System.out.println("=======================================");
        System.out.println("punkt startowy: ");
        Arrays.stream(x).forEach(System.out::println);
        System.out.println("krok startowy = " + krok);
        System.out.println("wartość funkcji w tym punkcie = " + f(x));
    }
}
