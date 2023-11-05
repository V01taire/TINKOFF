package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

@SuppressWarnings("MagicNumber")
public class RecursiveBacktrackerGenerator implements Generator {
    private final Random random = new Random();
    private static final int WALL_THICKNESS = 2;

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j, Cell.Type.WALL);
            }
        }

        Stack<Coordinate> stack = new Stack<>();
        Coordinate start = new Coordinate(1, 1);
        grid[start.row()][start.col()] = new Cell(start.row(), start.col(), Cell.Type.PASSAGE);
        stack.push(start);

        while (!stack.isEmpty()) {
            Coordinate current = stack.peek();
            List<Coordinate> neighbors = getUnvisitedNeighbors(current, grid);
            if (!neighbors.isEmpty()) {
                Coordinate randomNeighbor = neighbors.get(random.nextInt(neighbors.size()));
                int row = randomNeighbor.row();
                int col = randomNeighbor.col();
                grid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
                int wallRow = current.row() + (row - current.row()) / WALL_THICKNESS;
                int wallCol = current.col() + (col - current.col()) / WALL_THICKNESS;
                grid[wallRow][wallCol] = new Cell(wallRow, wallCol, Cell.Type.PASSAGE);
                stack.push(randomNeighbor);
            } else {
                stack.pop();
            }
        }
        return new Maze(height, width, grid);
    }

    private boolean isValidCell(int row, int col, int height, int width) {
        return row >= 1
            && row < height - 1
            && col >= 1
            && col < width - 1;
    }

    private List<Coordinate> getUnvisitedNeighbors(Coordinate cell, Cell[][] grid) {
        List<Coordinate> neighbors = new ArrayList<>();
        int[] rowOffsets = {-2, 2, 0, 0};
        int[] colOffsets = {0, 0, -2, 2};
        for (int i = 0; i < 4; i++) {
            int newRow = cell.row() + rowOffsets[i];
            int newCol = cell.col() + colOffsets[i];
            if (isValidCell(newRow, newCol, grid.length, grid[0].length)
                && grid[newRow][newCol].type() == Cell.Type.WALL) {
                neighbors.add(new Coordinate(newRow, newCol));
            }
        }
        return neighbors;
    }
}
