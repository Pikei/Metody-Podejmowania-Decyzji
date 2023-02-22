package com.company.egzamin;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Test {
    public Test() {
        start();
    }

    private final double[]x = new double[4];
    private double[]g = new double[x.length];

    private void start(){
        Arrays.fill(x,1);
        g = fPrim(x);
        IntStream.range(0, g.length).forEach(i -> g[i] *= -1);
        System.out.println("wartość funkcji wnosi: " + Arrays.stream(f(x)).sum());
//        System.out.print("\npunkty: \n");
//        Arrays.stream(x).forEach(System.out::println);
        System.out.println("\ngradient: ");
        Arrays.stream(g).forEach(System.out::println);
        System.out.println("\nwartość pochodnej wynosi: "+Arrays.stream(g).sum());
        steffen(x);
    }

    private double[] f(double[] x) {
        double[] sum = new double[x.length];
        IntStream.rangeClosed(1, x.length).forEach(i -> sum[i - 1] = (i + 1) * Math.pow(x[i - 1], i - 1));
        return sum;
    }

    private double[] fPrim(double[] x) {
        double[] sum = new double[x.length];
        IntStream.rangeClosed(1, x.length).forEach(i -> sum[i - 1] = ((i + 1) * (i - 1) * Math.pow(x[i - 1], (i - 1) - 1)));
        return sum;
    }

    private void steffen(double[] x) {
        double krok = 1;
        double[] temp = new double[x.length];
        double[] temp2 = new double[x.length];
        IntStream.range(0, temp.length).forEach(i -> temp[i] = x[i] + (krok * g[i]));
        System.out.println("\nnowe punkty bez optymalizacji kroku: ");
        Arrays.stream(temp).forEach(System.out::println);
        System.out.println("\nwartość funkcji z krokiem bez optymalizacji: " + Arrays.stream(f(temp)).sum());
        System.out.println();
        Arrays.stream(fPrim(temp)).forEach(System.out::println);
        System.out.println();
        System.out.println(Arrays.stream(fPrim(temp)).sum());
        double licznik = Math.pow(Arrays.stream(fPrim(temp)).sum(), 2);
        IntStream.range(0, temp.length).forEach(i -> temp2[i] = x[i] + ((krok+ Arrays.stream(temp).sum()) * g[i]));
        double mianownik = Arrays.stream(fPrim(temp2)).sum() + Arrays.stream(fPrim(temp)).sum();
        double nowyKrok = krok - licznik/mianownik;
        System.out.println();
        System.out.println("nowy krok: " + nowyKrok);
//
//        System.out.println("=======================================");
//        for (int i1 = 0; i1 < temp.length; i1++) {
//            temp[i1] = x[i1] + (nowyKrok * g[i1]);
//        }
//        System.out.println("\nnowe punkty bez optymalizacji kroku: ");
//        Arrays.stream(temp).forEach(System.out::println);
//        System.out.println("\nwartość funkcji z krokiem bez optymalizacji: " + Arrays.stream(f(temp)).sum());
//        System.out.println();
//        Arrays.stream(fPrim(temp)).forEach(System.out::println);
//        System.out.println();
//        System.out.println(Arrays.stream(fPrim(temp)).sum());
//        licznik = Math.pow(Arrays.stream(fPrim(temp)).sum(), 2);
//        for (int i = 0; i < temp.length; i++) {
//            temp2[i] = x[i] + ((nowyKrok + Arrays.stream(temp).sum()) * g[i]);
//        }
//        mianownik = Arrays.stream(fPrim(temp2)).sum() + Arrays.stream(fPrim(temp)).sum();
//        nowyKrok = krok - licznik/mianownik;
//        System.out.println();
//        System.out.println("nowy krok: " + nowyKrok);

        System.out.println("ostateczna wartość iksów: ");
        Arrays.setAll(x, i -> x[i] + nowyKrok * g[i]);
        Arrays.stream(x).forEach(System.out::println);

        System.out.print("\nwartość funkcji w tym punkcie: " + Arrays.stream(fPrim(x)).sum());


    }
}
