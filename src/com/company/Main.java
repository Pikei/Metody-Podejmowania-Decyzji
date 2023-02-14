package com.company;

import com.company.egzamin.Test;
import com.company.lab1.MetodaMonteCarlo;
import com.company.lab10.DifferentialEvolution;
import com.company.lab2.MenuHJ;
import com.company.lab3.MetodaSpadkuWzględemWspółrzędnych;
import com.company.lab4.MetodaNajszybszegoSpadku;
import com.company.lab5.MetodaZłotegoPodziału;
import com.company.lab6.NajszybszySpadekZłotyPodział;
import com.company.lab7.MetodaSteffensona;
import com.company.lab8.AlgorytmGenetyczny;
import com.company.lab9.Pso;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        menuOptions();
        menuChoice();
    }

    private static void menuOptions() {
        System.out.println("Wybierz którą metodę optymalizacji chcesz uruchomić");
        System.out.println("1. Metoda Monte Carlo");
        System.out.println("2. Metoda Hooke'a-Jeevsa");
        System.out.println("3. Metoda spadku względem współrzędnych");
        System.out.println("4. Metoda najszybszego spadku");
        System.out.println("5. Metoda złotego podziału");
        System.out.println("6. Metoda najszybszego spadku z optymalizacją kroku metodą złotego podziału");
        System.out.println("7. Metoda najszybszego spadku z optymalizacją kroku metodą steffensona");
        System.out.println("8. Algorytm genetyczny");
        System.out.println("9. Algorytm PSO");
        System.out.println("10. Algorytm różnicowy");
        System.out.println("0. TEST");
        System.out.print("Twój wybór: ");
    }

    private static void menuChoice() {
        Scanner input = new Scanner(System.in);
        switch (input.nextInt()) {
            case 0 -> new Test();
            case 1 -> new MetodaMonteCarlo();
            case 2 -> new MenuHJ();
            case 3 -> new MetodaSpadkuWzględemWspółrzędnych();
            case 4 -> new MetodaNajszybszegoSpadku();
            case 5 -> new MetodaZłotegoPodziału();
            case 6 -> new NajszybszySpadekZłotyPodział();
            case 7 -> new MetodaSteffensona();
            case 8 -> new AlgorytmGenetyczny();
            case 9 -> new Pso();
            case 10 -> new DifferentialEvolution(100);
        }
    }
}