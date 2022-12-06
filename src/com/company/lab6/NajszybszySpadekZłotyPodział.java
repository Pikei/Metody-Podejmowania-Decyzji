package com.company.lab6;

import java.util.Scanner;
import java.util.stream.IntStream;

public class NajszybszySpadekZłotyPodział {

    private double[] d = new double[3];
    private double[] x = new double[3];
    private double[] xNew = {1, 1, 1};
    private double krok;
    private Scanner input = new Scanner(System.in);

    public void naszybszySpadek() {
//        int count = 1;
        System.out.println("Podaj ilość iteracji: ");
        int range = input.nextInt();

        do {
            for (int j = 1; j <= range; j++) {
                System.arraycopy(xNew, 0, x, 0, x.length);
                d[0] = round(pochodnaX1(xNew[0], xNew[2]));
                d[1] = round(pochodnaX2(xNew[0], xNew[2]));
                d[2] = pochodnaX3();
                IntStream.range(0, d.length).forEach(i -> d[i] = d[i] * -1);
                krok = złotyPodział();
                IntStream.range(0, xNew.length).forEach(i -> xNew[i] = round(xNew[i] + d[i] * krok));
                if ((euklides() < 0.02) || j == range) {
                    print();
                    return;
                }
            }
//            for (int i = 0; i < x.length; i++) {
//                if(Math.abs(Math.abs(x[i])-Math.abs(xNew[i])) < 0.02) {
//                    count++;
//                } else count = 1;
//            }
//            if (count == x.length) break;
        } while (euklides() > 0.02);


    }

    private void print() {
        System.out.println("Funkcja wynosi: " + round(wielomian(xNew[0], xNew[1], xNew[2])) + "\nOptymalne współrzędne dla punktów to: ");
        for (double v : xNew) {
            System.out.println(v);
        }
    }


    private double złotyPodział() {
        double a = -10;
        double b = 10;
        double k = (Math.sqrt(5) - 1) / 2;
        double e = 0.06;
        double xl = b - k * (b - a);
        double xr = a + k * (b - a);

            do {
                if (wielomianDlaZłotegoPodziału(xl) < wielomianDlaZłotegoPodziału(xr)) {
                    b = xr;
                    xr = xl;
                    xl = b - k * (b - a);
                } else {
                    a = xl;
                    xl = xr;
                    xr = a + k * (b - a);
                }
            }
            while (b - a > e);
        return round((a + b) / 2);
    }

    private double wielomianDlaZłotegoPodziału(double k) {
        return Math.pow((x[0] + k * d[0]), 3) * Math.sin(x[1] + k * d[1] + 4 * (x[2] + k * d[2]));
    }

    private double wielomian(double x1, double x2, double x3) {
        return Math.pow(x1, 3) * Math.sin(x2) + 4 * x3;
    }

    private double pochodnaX1(double x1, double x2) {
        return 3 * Math.pow(x1, 2) * Math.sin(x2);
    }

    private double pochodnaX2(double x1, double x2) {
        return Math.pow(x1, 3) * Math.cos(x2);
    }

    private double pochodnaX3() {
        return 4;
    }

    private double round(double value) {
        double temp = Math.round(value * 100000);
        value = (temp / 100000);
        return value;
    }

    private double euklides() {
        return round(Math.sqrt(Math.pow(xNew[0] - x[0], 2) + Math.pow(xNew[1] - x[1], 2) + Math.pow(xNew[2] - x[2], 2)));
    }



//    public void start() {
//        xl = b - k * (b - a);
//        xr = a + k * (b - a);
//
//        do {
//            if (wielomian(xl) < wielomian(xr)) {
//                b = xr;
//                xr = xl;
//                xl = b - k * (b - a);
//            } else {
//                a = xl;
//                xl = xr;
//                xr = a + k * (b - a);
//            }
//        } while (b - a > e);
//
//        min = round((a + b) / 2);
//        JOptionPane.showMessageDialog(null, min);
//        result.setText(result.getText() + min);
//    }

}
