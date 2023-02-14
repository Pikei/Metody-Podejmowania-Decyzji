package com.company.egzamin;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Test {

    private final double[]g = new double[100];
    public Test() {
        algortym();
    }

    private double funkcja(double[] x){
        /*
        double result = 0;
        for (int i = 0; i < x.length; i++) {
            result += (i+1)*Math.pow(x[i], i-1);
        }
        return result;
         */
        return IntStream.range(0, x.length).mapToDouble(i -> (i + 1) * Math.pow(x[i], i - 1)).sum();
    }

    private double pochodna(double[] x){
        return IntStream.range(0, x.length).mapToDouble(i -> ((i + 1)*(i - 1)) * Math.pow(x[i], (i - 1)-1)).sum();
    }

//    private double[] gradient(double[] x, double[] g) {
//
//        return g;
//    }

    private void algortym(){
        double[]x = new double[100];
        double k = 1;
        Arrays.fill(x,1);
        System.out.println("wartość funkcji wynosi: " + funkcja(x));
        Arrays.setAll(g, i -> ((i + 1) * (i - 1)) * Math.pow(x[i], (i - 1) - 1));
//        Arrays.stream(g).forEach(System.out::println);
        double[] temp = new double[100];
        Arrays.setAll(temp, i-> x[i]+(k*g[i]));
        Arrays.stream(temp).forEach(System.out::println);

    }
}
