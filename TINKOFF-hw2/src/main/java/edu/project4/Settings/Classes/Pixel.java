package edu.project4.Settings.Classes;

public class Pixel {

    private int r;
    private int g;
    private int b;
    private int hitCounter;
    private double normal;

    public Pixel() {
        hitCounter = 0;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getHitCounter() {
        return hitCounter;
    }

    public synchronized void incrementHitCounter() {
        hitCounter++;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }
}
