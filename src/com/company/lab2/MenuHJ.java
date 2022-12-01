package com.company.lab2;

import java.util.Arrays;
import java.util.Scanner;

public class MenuHJ {
    public void menu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wprowadź ile przejść chcesz wykonać: ");
        int ilosc = input.nextInt();
        System.out.println("Podaj wartość o jakie będą modyfikowane wartości wektora (krok): ");
        double krok = input.nextDouble();
        System.out.println("Czy chcesz wprowadzić własne współrzędne dla początkowego punktu? [T/N] ");
        boolean exit;
        double[] współrzędne = new double[5];
        do {
            switch (input.next()) {
                case "T" -> {
                    System.out.println("twoje dane: ");
                    for (int i = 0; i < współrzędne.length; i++) {
                        System.out.print("x" + (i+1) + " = ");
                        współrzędne[i] = input.nextDouble();
                    }
                    exit = true;
                }
                case "N" -> {
                    Arrays.fill(współrzędne, 1);
                    exit = true;
                }
                default -> {
                    System.out.println("Wybierz poprawną opcję.");
                    exit = false;
                }
            }
        } while (!exit);
        MetodaHookeaJeevesa metodaHookeaJeevesa = new MetodaHookeaJeevesa(współrzędne, krok);
        for (int i = 0; i < ilosc; i++) {
            metodaHookeaJeevesa.krokProbny();
            metodaHookeaJeevesa.krokRoboczy();
        }
        metodaHookeaJeevesa.wypiszX();
    }
}
