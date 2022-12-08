package com.company.lab1;

import java.util.Scanner;

public class MetodaMonteCarlo {
    private final Scanner input = new Scanner(System.in).useDelimiter("\n");
    private int rangeFrom;
    private int rangeTo;
    private int x1;
    private int x2;
    private int x3;

    public MetodaMonteCarlo() {
        menu();
    }

    public void menu() {
        System.out.println("Enter range of numbers.");
        System.out.print("Range from: ");
        setRangeFrom(input.nextInt());
        System.out.print("Range to: ");
        setRangeTo(input.nextInt());
    }

    public int generate() {
        int min = getRangeFrom();
        int max = getRangeTo();
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return random_int;
    }

    public void drawNumber() {
        setX1(generate());
        setX2(generate());
        setX3(generate());
    }

    public int equation(int a, int b, int c) {
        return 3*(a*a)+a*c+b;
    }

    public void check() {
        int y1;
        int y2;
        int y3;
        y1 = getX1();
        y2 = getX2();
        y3 = getX3();
        drawNumber();
        System.out.print("Enter number of iterations: ");
        int n = input.nextInt();
        int temp;
        int temp2;
        for(int i = 0; i<n; i++) {
            temp = equation(y1, y2, y3);
            temp2 = equation(getX1(), getX2(), getX3());
            if(temp > temp2) {
                y1 = getX1();
                y2 = getX2();
                y3 = getX3();
            }
            System.out.println(temp);
            drawNumber();
        }
    }

    public int getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(int rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public int getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(int rangeTo) {
        this.rangeTo = rangeTo;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }
}