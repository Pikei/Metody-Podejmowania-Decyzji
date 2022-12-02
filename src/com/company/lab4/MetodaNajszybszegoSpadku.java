package com.company.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.stream.IntStream;

public class MetodaNajszybszegoSpadku extends Frame implements ActionListener {
    private final double[] s = new double[4];
    private final double[] sNew = new double[4];
    private final double[] g = new double[4];
    private final double k = 0.6;
    private TextField x1, x2, x3, x4;
    private Button calculate;
    public MetodaNajszybszegoSpadku() {
        super("Metoda najszybszego spadku");
        setSize(500, 300);
        setLayout(new FlowLayout());
        setBackground(Color.lightGray);
        add(new Label("Początkowe wartości:"));
        x1 = new TextField(5);
        add(x1);
        x1.setText("1");
        x2 = new TextField(5);
        add(x2);
        x2.setText("1");
        x3 = new TextField(5);
        add(x3);
        x3.setText("1");
        x4 = new TextField(5);
        add(x4);
        x4.setText("1");
        calculate = new Button("Wyznacz optymalne współrzędne");
        calculate.addActionListener(this);
        add(calculate);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        if (label.equals("Wyznacz optymalne współrzędne")) {
            try {
                s[0] = (Double.parseDouble(x1.getText()));
                s[1] = (Double.parseDouble(x2.getText()));
                s[2] = (Double.parseDouble(x3.getText()));
                s[3] = (Double.parseDouble(x4.getText()));
                System.arraycopy(s, 0, sNew, 0, s.length);
                start();
            } catch (NumberFormatException ev) {
                JOptionPane.showMessageDialog(null, "Błędne dane! Wprowadź poprawne współrzędne początkowe.");
            }
        } else if (label.equals("Shutdown")) {
            System.exit(0);
        }
    }

    public void start() {
        for (int i = 0; i < 1000; i++) {
            spadek();
            double e = 0.02;
            if (euklides() < e) {
                break;
            } else {
                IntStream.range(0, s.length).forEach(x -> s[x] = sNew[x]);
            }
        }
        printInfo();
    }

    private double euklides() {
        return (Math.sqrt(Math.pow(sNew[0] - s[0], 2) + Math.pow(sNew[1] - s[1], 2) + Math.pow(sNew[2] - s[2], 2) + Math.pow(sNew[3] - s[3], 2)));
    }

    private void printInfo() {
        String info = "Współrzędne optymalne to:\n" +
                "x1 = " + s[0] + "\n" +
                "x2 = " + s[1] + "\n" +
                "x3 = " + s[2] + "\n" +
                "x4 = " + s[3] + "\n";
        JOptionPane.showMessageDialog(null, info);
    }

    private void spadek() {
        g[0] = pochodnaX1(sNew[0], sNew[1], sNew[2], sNew[3]);
        g[1] = pochodnaX2(sNew[0], sNew[1], sNew[2], sNew[3]);
        g[2] = pochodnaX3(sNew[0], sNew[1], sNew[2], sNew[3]);
        g[3] = pochodnaX4(sNew[0], sNew[1], sNew[2], sNew[3]);
        IntStream.range(0, g.length).forEach(i -> g[i] = round(g[i] * -1));
        IntStream.range(0, sNew.length).forEach(i -> sNew[i] = round(s[i] + k * g[i]));
    }

    //    metoda zaokrąglająca wyniki do 9 miejsca po przecinku
    private double round(double value) {
        double temp = Math.round(value * 1000000000);
        value = (temp / 1000000000);
        return value;
    }

    private double wielomian(double x1, double x2, double x3, double x4) {
        return Math.pow(x1, 4) * x2 + Math.pow(x3, 3) + Math.pow(x4, 2) * x1;
    }

    private double pochodnaX1(double x1, double x2, double x3, double x4) {
        return 4 * Math.pow(x1, 3) * x2 + Math.pow(x4, 2);
    }

    private double pochodnaX2(double x1, double x2, double x3, double x4) {
        return Math.pow(x1, 4);
    }

    private double pochodnaX3(double x1, double x2, double x3, double x4) {
        return 3 * Math.pow(x3, 2);
    }

    private double pochodnaX4(double x1, double x2, double x3, double x4) {
        return 2 * x4 * x1;
    }
}