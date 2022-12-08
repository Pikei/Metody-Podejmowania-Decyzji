package com.company.lab7;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MetodaSteffensona {
    private double[] gradient, x, xNew = new double[3];
    private double lambda, lambdaNew;

    public void start() {
        Arrays.fill(x, 1);
        Arrays.fill(xNew, 1);
        lambda = 1;
        lambdaNew = 1;

        gradient[0] = pochodnax1(xNew[0], xNew[1], xNew[2]);
        gradient[1] = pochodnax1(xNew[0], xNew[1], xNew[2]);
        gradient[2] = pochodnax1(xNew[0], xNew[1], xNew[2]);
        IntStream.range(0, gradient.length).forEach(i -> gradient[i] = gradient[i] * -1);
        lambdaNew = krokLambda();

    }


    private double krokLambda () {
        return lambda - (Math.pow(Math.abs(pochodnaLambda(lambda)), 2)) / (pochodnaLambda(lambda+pochodnaLambda(lambda)) - pochodnaLambda(lambda));
    }

    private double pochodnaLambda(double k) {
        return 2 * gradient[0] * Math.pow(xNew[0] + gradient[0]*k , 3) +
                (2 * xNew[1]*gradient[1] + Math.pow(gradient[1], 2) * k) *
                Math.pow(xNew[2] + gradient[2], 4) +
                (4*Math.pow(xNew[1],2)+gradient[2] + 8*xNew[1]*gradient[1]*gradient[2]*k + 4 * Math.pow(xNew[1],2)*gradient[2] * Math.pow(k,2)) *
                Math.pow(xNew[2] + gradient[2] * k, 3);
    }

    private double wielomian(double x1, double x2, double x3) {
        return (Math.pow(x1,4)/2) + Math.pow(x2,2)*x3 + Math.pow(x3,4);
    }

    private double pochodnax1(double x1, double x2, double x3){
        return 2 * Math.pow(x1,3);
    }

    private double pochodnax2(double x1, double x2, double x3){
        return 2 * x2 * x3;
    }

    private double pochodnax3(double x1, double x2, double x3){
        return Math.pow(x3,2) + 4 * Math.pow(x3,3);
    }


}
