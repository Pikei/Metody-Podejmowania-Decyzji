package com.company;

import com.company.lab1.MetodaMonteCarlo;
import com.company.lab2.MenuHJ;
import com.company.lab3.MetodaSpadkuWzględemWspółrzędnych;
import com.company.lab4.MetodaNajszybszegoSpadku;
import com.company.lab5.MetodaZłotegoPodziału;
import com.company.lab5.Wykres;

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
        System.out.println("5. Metoda złotego podziału");
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
            case 5 -> new MetodaZłotegoPodziału();
//            case 0 -> new Wykres();
        }
    }
}

//
//import java.awt.*;
//import javax.swing.*;
//import java.awt.geom.Line2D;
//import java.util.Random;
//
//class MyCanvas extends JComponent {
//int x,y;
//    public MyCanvas(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public void paint(Graphics g)
//    {
//        g.drawLine(x, 0, x, y);
//        g.drawLine(y,34,21,420);
//    }
//}
//
//public class Main {
//    public static void main(String[] a)
//    {
//        Random rand = new Random();
//        JFrame window = new JFrame();
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setBounds(300, 200, 1000, 700);
////        window.getContentPane().add(new MyCanvas(22, 1));
////        window.setVisible(true);
//        window.getContentPane().add(new MyCanvas(220, 10));
//
//        window.setVisible(true);
//    }
//}