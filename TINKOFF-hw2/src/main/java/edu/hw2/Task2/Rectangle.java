package edu.hw2.Task2;

public class Rectangle implements Shape {

    private int width;
    private int height;

    public Rectangle() {
        this(0, 0);
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle setHeight(int height) {
        if (width == height) {
            return new Square(height);
        }

        this.height = height;

        return this;
    }

    public Rectangle setWidth(int width) {
        if (height == width) {
            return new Square(width);
        }

        this.width = width;

        return this;
    }

    @Override
    public double area() {
        return width * height;
    }
}
