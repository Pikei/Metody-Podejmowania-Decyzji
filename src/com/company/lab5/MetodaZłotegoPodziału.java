package com.company.lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MetodaZłotegoPodziału extends Frame implements ActionListener {
    private double a, b;
    private double xl, xr, min;
    private double e = 0.02;
    private double k = (Math.sqrt(5) - 1) / 2;
    private int[] tabXargs, tabXvals;
    private TextField x1, x2;
    private Button calculate, plot;
    private Label result = new Label("Optymalne wartości: ");


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

        a = Double.parseDouble(x1.getText());
        b = Double.parseDouble(x2.getText());

        tabXargs = tablicaArgumentów((int)a, (int)b);
        tabXvals = tablicaWartości((int)a, (int)b);

        calculate = new Button("Wyznacz minimum");
        calculate.addActionListener(this);
        add(calculate);
        add(result);

        plot = new Button("Wykres");
        plot.addActionListener(this);
        add(plot);

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
        switch (label) {
            case "Wyznacz minimum":
                try {
                    start();
                } catch (NumberFormatException ev) {
                    JOptionPane.showMessageDialog(null, "Błędne dane! Wprowadź poprawne współrzędne początkowe.");
                }
                break;
            case "Wykres":
                try {
                    wykres();
                } catch (NumberFormatException ev) {
                    JOptionPane.showMessageDialog(null, "Błędne dane! Wprowadź poprawne współrzędne początkowe.");
                }
                break;
            case "Shutdown":
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
//        JOptionPane.showMessageDialog(null, min);
        result.setText(result.getText() + min);
    }

    public double wielomian(double x) {
        return Math.abs(2 * Math.pow(x - 2.5, 3));
//        return x*x;
    }

    private double round(double value) {
        double temp = Math.round(value * 100);
        value = (temp / 100);
        return value;
    }

    private int[] tablicaArgumentów(int a, int b) {
        ArrayList<Integer> tabTemp = new ArrayList<>();
        int end = (Math.abs(a)+Math.abs(b));
        for (int i = 0; i<end; i++) {
            tabTemp.add(a);
            a++;
        }
        return tabTemp.stream().mapToInt(i -> i).toArray();
    }
    private int[] tablicaWartości(int a, int b) {
        ArrayList<Integer> tabTemp = new ArrayList<>();
        int end = (Math.abs(a)+Math.abs(b));
        for (int i = 0; i<end; i++) {
            tabTemp.add((int) wielomian(tabXargs[i]));
        }
        return tabTemp.stream().mapToInt(i -> i).toArray();
    }

    public void wykres() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(300, 200, 1000, 700);
        for (int i = 0; i<tabXargs.length; i++) {
            window.getContentPane().add(new Wykres(tabXargs[i], (int) round(wielomian(tabXargs[i])), tabXargs[i+1],(int) round(wielomian(tabXargs[i+1]))));
            window.setVisible(true);
        }
    }
}