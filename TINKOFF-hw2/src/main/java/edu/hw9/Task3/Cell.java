package edu.hw9.Task3;

record Cell(int row, int col, Type type) {
    public enum Type { WALL, PASSAGE }
}
