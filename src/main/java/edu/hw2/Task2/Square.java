package edu.hw2.Task2;

public class Square extends Rectangle implements Shape {

    private int width;
    private int height;

    public Square() {
        this(0);
    }

    public Square(int side) {
        width = side;
        height = side;
    }

    public Rectangle setWidth(int width) {
        if (height == width) {
            this.width = width;
            return this;
        }

        return new Rectangle(width, height);
    }

    public Rectangle setHeight(int height) {
        if (width == height) {
            this.height = height;
            return this;
        }

        return new Rectangle(width, height);
    }

    @Override
    public double area() {
        return width * height;
    }
}
