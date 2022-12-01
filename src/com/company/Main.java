package com.company;

import com.company.lab1.MetodaMonteCarlo;
import com.company.lab2.MenuHJ;
import com.company.lab3.MetodaSpadkuWzględemWspółrzędnych;
import com.company.lab4.MetodaNajszybszegoSpadku;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MetodaMonteCarlo monteCarlo = new MetodaMonteCarlo();
        MenuHJ hookeJeeves = new MenuHJ();
        MetodaSpadkuWzględemWspółrzędnych metodaSpadkuWzględemWspółrzędnych = new MetodaSpadkuWzględemWspółrzędnych();

        System.out.println("wybierz którą metodę optymalizacji chcesz uruchomić");
        System.out.println("1. Metoda Monte Carlo");
        System.out.println("2. Metoda Hooke'a-Jeevsa");
        System.out.println("3. Metoda spadku względem współrzędnych");
        System.out.println("4. Metoda najszybszego spadku");
        System.out.print("Twój wybór: ");
        Scanner input = new Scanner(System.in);
        switch (input.nextInt()) {
            case 1 -> {
                monteCarlo.menu();
                monteCarlo.check();
            }
            case 2 -> hookeJeeves.menu();
            case 3 -> metodaSpadkuWzględemWspółrzędnych.start();
            case 4 -> new MetodaNajszybszegoSpadku();
        }
    }
}
