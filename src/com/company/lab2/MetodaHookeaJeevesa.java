package com.company.lab2;

import java.util.stream.IntStream;

public class MetodaHookeaJeevesa {
    private double[] x0;
    private double[] x;
    private double k;
    private double f1, f2;
    private double temp;

    public MetodaHookeaJeevesa(double[] x0, double k) {
        this.x0 = x0;
        this.x = x0;
        this.k = k;
    }

    public void krokProbny() {
        double temporary;
        for (int i = 0; i < x.length; i++) {
            temporary = k;
            temp = x[i];
            double e = 0.02;
            while (!krok(i, temp, k) && !krok(i, temp, -1 * k)) {
                k = k / 2;
                if (k < e) {
                    x[i] = temp;
                    break;
                }
            }
            k = temporary;
        }
    }

    private double wielomian(double x1, double x2, double x3, double x4, double x5) {
        return (Math.pow(x1, 5) * x5 / 2) - (4 * Math.sin(x4)) + Math.sqrt(x2) * (x5 / Math.log(x3) - Math.cos(x4 + x1));
    }

    private boolean przypisanie(double f1, double f2, int i, double k) {
        if (f2 < f1) {
            x[i] = temp + k;
            return true;
        }
        return false;
    }

    private boolean krok(int i, double temp, double k) {
        f1 = wielomian(x[0], x[1], x[2], x[3], x[4]);
        x[i] = temp + k;
        f2 = wielomian(x[0], x[1], x[2], x[3], x[4]);
        x[i] = temp;
        return przypisanie(f1, f2, i, k);
    }

    public void wypiszX() {
        System.out.println("optymalne wartości X:");
        for (int i = 0; i < x.length; i++) {
            System.out.println("X" + (i + 1) + " = " + x[i]);
        }
    }

    public void krokRoboczy() {
        IntStream.range(0, x.length).forEach(i -> x0[i] = x[i]);
        double v = 1.25;
        double e = 0.02;
        while (v > e) {
            IntStream.range(0, x.length).forEach(i -> x[i] = x0[i]);
            for (int j = 0; j < x.length; j++) {
                x[j] = v * x0[j];
            }
            f1 = wielomian(x0[0], x0[1], x0[2], x0[3], x0[4]);
            f2 = wielomian(x[0], x[1], x[2], x[3], x[4]);
            if (!(f2 < f1)) {
                v = v / 2;
            } else return;
        }
        if (v < e) {
            IntStream.range(0, x.length).forEach(i -> x[i] = x0[i]);
            return;
        }
    }
}