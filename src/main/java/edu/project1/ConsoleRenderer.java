package edu.project2;

import java.util.Arrays;
import java.util.List;

public class ConsoleRenderer implements Renderer {
    private static final String WALL_CELL = "\u001B[43m\u001B[33m  \u001B[0m";
    private static final String EMPTY_CELL = "  ";
    private static final String PATH_CELL = "\u001B[48;2;128;0;128m\u001B[37m**\u001B[0m";

    @Override
    @SuppressWarnings("MagicNumber")
    public String render(Maze maze) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int k = 0; k < 4; k++) {
                result.append(" ");
            }
            for (int j = 0; j < maze.getWidth(); j++) {
                if (maze.getGrid()[i][j].type() == Cell.Type.WALL) {
                    result.append(WALL_CELL);
                } else {
                    result.append(EMPTY_CELL);
                }
            }
            result.append("    \n");
        }
        return result.toString();
    }

    @Override
    @SuppressWarnings("LineLength")
    public String render(Maze maze, List<Coordinate> path) {
        char[][] grid = new char[maze.getHeight()][maze.getWidth()];
        for (int i = 0; i < maze.getHeight(); i++) {
            Arrays.fill(grid[i], ' ');
        }
        for (Coordinate coordinate : path) {
            grid[coordinate.row()][coordinate.col()] = '*';
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            StringBuilder rowStringBuilder = new StringBuilder();
            rowStringBuilder.append("    ");
            for (int j = 0; j < maze.getWidth(); j++) {
                char cell = grid[i][j];
                if (cell == '*') {
                    rowStringBuilder.append(PATH_CELL);
                } else if (maze.getGrid()[i][j].type() == Cell.Type.WALL) {
                    rowStringBuilder.append(WALL_CELL);
                } else {
                    rowStringBuilder.append(EMPTY_CELL);
                }
            }
            rowStringBuilder.append("\n");
            result.append(rowStringBuilder);
        }
        return result.toString();
    }
}


