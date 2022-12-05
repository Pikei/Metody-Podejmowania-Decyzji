package com.company.lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MetodaZłotegoPodziału extends Frame implements ActionListener {
    private double a = -100;
    private double b = 100;
    private double xl, xr, min;
    private double e = 0.02;
    private double k = (Math.sqrt(5) - 1) / 2;
    private TextField x1, x2;
    private Button calculate;

    public MetodaZłotegoPodziału() {
        super("Metoda najszybszego spadku");
        setSize(500, 300);
        setLayout(new FlowLayout());
        setBackground(Color.lightGray);
        add(new Label("Początkowe wartości:"));
        x1 = new TextField(3);
        add(x1);
        x1.setText("-100");
        x2 = new TextField(3);
        add(x2);
        x2.setText("100");

        calculate = new Button("Wyznacz minimum");
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
        if (label.equals("Wyznacz minimum")) {
            try {
                start();
            } catch (NumberFormatException ev) {
                JOptionPane.showMessageDialog(null, "Błędne dane! Wprowadź poprawne współrzędne początkowe.");
            }
        } else if (label.equals("Shutdown")) {
            System.exit(0);
        }
    }

    public void start() {
        xl = b - k * (b - a);
        xr = a + k * (b - a);

        do {
            if (wielomian(xl) < wielomian(xr)) {
                b = xr;
                xr = xl;
                xl = b - k * (b - a);
            } else {
                a = xl;
                xl = xr;
                xr = a + k * (b - a);
            }
        } while (b - a > e);

        min = round((a + b) / 2);
        JOptionPane.showMessageDialog(null, min);
    }

    public double wielomian(double x) {
        return Math.abs(2 * Math.pow(x - 2.5, 3));
    }

    private double round(double value) {
        double temp = Math.round(value * 100);
        value = (temp / 100);
        return value;
    }

    // rysowanie grafu jeszcze jest w planach :)
}