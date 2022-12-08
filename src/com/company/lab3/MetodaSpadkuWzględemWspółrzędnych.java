package com.company.lab3;

import java.util.Arrays;

public class MetodaSpadkuWzględemWspółrzędnych {
    private double[] x0;
    private double[] x;
    private double f1, f2;
    private double temp;
    private double k = 0.25;

    public MetodaSpadkuWzględemWspółrzędnych() {
        start();
    }

    public void start() {
        setX(new double[5]);
        setX0(new double[5]);
        Arrays.fill(x, 1);
        Arrays.fill(x0, 1);
        int lim = 0;
        while (lim != x.length) {
//            System.out.println("wielomian " + wielomian(x[0], x[1], x[2], x[3], x[4]));
            lim = krok();
        }
        wypiszX();
    }

    private int krok() {
        int lim = 0;
        for (int i = 0; i < x.length; i++) {
            temp = x[i];
            if(!(sprawdzenie(i, temp, k)) && !(sprawdzenie(i, temp, -k))) {
                x[i] = temp;
                lim ++;
            }
        }
        return lim;
    }

    private boolean przypisanie(double f1, double f2, int i, double k) {
        if (f2 < f1) {
            x[i] = temp + k;
            return true;
        }
        return false;
    }

    private boolean sprawdzenie(int i, double temp, double k) {
        f1 = wielomian(x[0], x[1], x[2], x[3], x[4]);
        x[i] = temp + k;
        f2 = wielomian(x[0], x[1], x[2], x[3], x[4]);
        x[i] = temp;
        return przypisanie(f1, f2, i, k);
    }

    private void wypiszX() {
        System.out.println("optymalne wartości X:");
        for (int i = 0; i < x.length; i++) {
            System.out.println("X" + (i + 1) + " = " + x[i]);
        }
    }

    private double wielomian(double x1, double x2, double x3, double x4, double x5) {
        return (Math.pow(x1, 5) * x5 / 2) - (4 * Math.sin(x4)) + Math.sqrt(x2) * (x5 / Math.log(x3) - Math.cos(x4 + x1));
    }

    public void setX0(double[] x0) {
        this.x0 = x0;
    }

    public void setX(double[] x) {
        this.x = x;
    }
}