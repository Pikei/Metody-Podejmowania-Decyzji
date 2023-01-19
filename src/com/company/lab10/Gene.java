package com.company.lab10;

public class Gene {
    private double x, y, grade;

    public Gene(double x, double y, double grade) {
        this.x = x;
        this.y = y;
        this.grade = grade;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
