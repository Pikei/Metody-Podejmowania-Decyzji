package com.company.lab6;

import java.util.stream.IntStream;

public class NajszybszySpadekZłotyPodział {

    private double[] d = new double[3];
    private double[] x = {4, 8, 4};
    private double[] xNew = {4, 8, 4};
    private double krok;

    public void naszybszySpadek() {
        do {
            d[0] = pochodnaX1(xNew[0], xNew[2]);
            d[1] = pochodnaX2(xNew[0], xNew[2]);
            d[2] = pochodnaX3();
            IntStream.range(0, d.length).forEach(i -> d[i] = round(d[i] * -1));
            krok = złotyPodział();
            System.arraycopy(xNew, 0, x, 0, x.length);
        } while (euklides() >= 0.02);
        IntStream.range(0, x.length).forEach(i -> x[i] = xNew[i]);
        System.out.println(wielomian(x[0], x[1], x[2]));

        for (int i = 0; i < xNew.length; i++) {
            System.out.println(xNew[i]);
        }
    }


    private double złotyPodział() {
        double a = -10;
        double b = 10;
        double k = (Math.sqrt(5) - 1) / 2;
        double e = 0.06;
        double xl = b - k * (b - a);
        double xr = a + k * (b - a);

        for (double v : d) {
            do {
                if (wielomianDlaZłotegoPodziału(xl, k, v) < wielomianDlaZłotegoPodziału(xr, k, v)) {
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
        }
        return round((a + b) / 2);
    }

    private double wielomianDlaZłotegoPodziału(double x, double k, double d) {
        return x + k * d;
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
        double temp = Math.round(value * 1000);
        value = (temp / 1000);
        return value;
    }

    private double euklides() {
        return (Math.sqrt(Math.pow(xNew[0] - x[0], 2) + Math.pow(xNew[1] - x[1], 2) + Math.pow(xNew[2] - x[2], 2)));
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
