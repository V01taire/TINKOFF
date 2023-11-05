package edu.project2;

record Cell(int row, int col, Type type) {
    public enum Type { WALL, PASSAGE }
}
