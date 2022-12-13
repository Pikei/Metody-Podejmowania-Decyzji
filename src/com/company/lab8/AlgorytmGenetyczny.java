package com.company.lab8;


import java.util.Arrays;
import java.util.Random;

public class AlgorytmGenetyczny {
    private double[] x = new double[4];
    private double[][] rodzice = new double[100][4];
    private Random rand;

    private void start() {
        for (int i = 0; i < rodzice.length; i++) {
            Arrays.setAll(x, j -> generateRandom());
            rodzice[i] = x;

        }
    }


    private double generateRandom(){
        return (Math.random() * (-100 - 100)) -100;
    }

    private double func(double x1, double x2, double x3, double x4) {
        return ((Math.sin(x1) * Math.cos(x2)) / (Math.pow(x3,2) * Math.pow(x4,2) + 1)) * -1;
    }
}
