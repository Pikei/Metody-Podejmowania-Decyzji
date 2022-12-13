package com.company.lab7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.stream.IntStream;

public class MetodaSteffensona extends Frame implements ActionListener {
    private final double[] g = new double[3];
    private final double[] x = new double[3];
    private final double[] xNew = new double[3];
    private double lambda, lambdaNew;

    public MetodaSteffensona() {
        super("Metoda steffensona");
        setSize(500, 300);
        setLayout(new FlowLayout());
        setBackground(Color.lightGray);
        add(new Label("Początkowe wartości:"));
        TextField x1 = new TextField(3);
        add(x1);
        x1.setText("1");
        TextField x2 = new TextField(3);
        add(x2);
        x2.setText("1");
        TextField x3 = new TextField(3);
        add(x3);
        x3.setText("1");

        x[0] = Double.parseDouble(x1.getText());
        x[1] = Double.parseDouble(x2.getText());
        x[2] = Double.parseDouble(x3.getText());
        System.arraycopy(x, 0, xNew, 0, xNew.length);
        lambda = 1;

        Button calculate = new Button("Wyznacz minimum");
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
        if ("Wyznacz minimum".equals(label)) {
            try {
                start();
            } catch (NumberFormatException ev) {
                JOptionPane.showMessageDialog(null, "Błędne dane! Wprowadź poprawne współrzędne początkowe.");
            }
        } else if ("Shutdown".equals(label)) {
            System.exit(0);
        }
    }

    public void start() {
        for (int i = 0; i < 1000; i++) {
            System.arraycopy(xNew, 0, x, 0, x.length);
            gradient();
            steffenson();
            IntStream.range(0, xNew.length).forEach(j -> xNew[j] = x[j] + g[j] * lambdaNew);
        }
        printResult();
    }

    private void gradient() {
        g[0] = pochodnax1(xNew[0]);
        g[1] = pochodnax2(xNew[1], xNew[2]);
        g[2] = pochodnax3(xNew[2]);
        IntStream.range(0, g.length).forEach(j -> g[j] = g[j] * -1);
    }

    private void steffenson() {
        for (int i = 0; i < 1000; i++) {
            lambdaNew = round(krokLambda(lambda));
            if (check() < 0.02) {
                break;
            } else {
                lambda = lambdaNew;
            }
        }
    }

    private void printResult() {
        String info = "Optymalna wartość funkcji wynosi: " + wielomian(xNew[0], xNew[1], xNew[2]) +
                "\nMinimum znajduje się w punkcie:" +
                "\nX = " + xNew[0] +
                "\nY = " + xNew[1] +
                "\nZ = " + xNew[2];
        JOptionPane.showMessageDialog(null, info);
    }


    private double krokLambda(double l) {
        return l - (Math.pow(Math.abs(pochodnaLambda(l)), 2)) / (pochodnaLambda(l + pochodnaLambda(l)) - pochodnaLambda(l));
    }

    private double pochodnaLambda(double k) {
        return 4 * g[2] * Math.pow((g[2] * k + xNew[2]), 3) +
                g[2] * Math.pow((g[1] * k + xNew[1]), 2) + 2 * g[0] *
                Math.pow((g[0] * k + xNew[0]), 3) + 2 * g[1] * (g[1] * k + xNew[1]) * (g[2] * k + xNew[2]);
    }

    private double wielomian(double x1, double x2, double x3) {
        return (Math.pow(x1, 4) / 2) + Math.pow(x2, 2) * x3 + Math.pow(x3, 4);
    }

    private double pochodnax1(double x1) {
        return 2 * Math.pow(x1, 3);
    }

    private double pochodnax2(double x2, double x3) {
        return 2 * x2 * x3;
    }

    private double pochodnax3(double x3) {
        return Math.pow(x3, 2) + 4 * Math.pow(x3, 3);
    }

    private double check() {
        return Math.abs(lambdaNew - lambda);
    }

    private double round(double value) {
        double temp = (int) (value * 1000000000);
        return (temp / 1000000000);
    }

    /*
    ############# STARY WZÓR NA POCHODNĄ DLA LAMBDY ##################
    return ((2 * g[0]) *
                (Math.pow(xNew[0] + g[0] * k, 3)) +
                (2 * xNew[1] * g[1] + 2 * Math.pow(g[1], 2) * k) *
                (Math.pow(xNew[2] + g[2] * k, 4)) +
                (4 * Math.pow(xNew[1], 2) * g[2] + 8 * xNew[1] * g[1] * g[2] * k + 4 * Math.pow(g[1], 2) * g[2] * Math.pow(k, 2)) *
                (Math.pow(xNew[2] + g[2] * k, 3)));
    #####################################################################
     */

}
