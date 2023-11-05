package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings({"MagicNumber", "LineLength"})
public class RandomizedPrimGenerator implements Generator {
    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j, Cell.Type.WALL);
            }
        }

        int startRow = 1;
        int startCol = 1;
        grid[startRow][startCol] = new Cell(startRow, startCol, Cell.Type.PASSAGE);

        List<Coordinate> activeCells = new ArrayList<>();
        activeCells.add(new Coordinate(startRow, startCol));

        while (!activeCells.isEmpty()) {
            int currentIndex = activeCells.size() - 1;
            Coordinate current = activeCells.get(currentIndex);
            int row = current.row();
            int col = current.col();

            List<Coordinate> neighbors = getNeighbors(row, col, grid.length, grid[0].length);
            List<Coordinate> unvisitedNeighbors = new ArrayList<>();

            for (Coordinate neighbor : neighbors) {
                int newRow = neighbor.row();
                int newCol = neighbor.col();
                if (grid[newRow][newCol].type() == Cell.Type.WALL) {
                    unvisitedNeighbors.add(neighbor);
                }
            }

            if (!unvisitedNeighbors.isEmpty()) {
                int randomNeighborIndex = random.nextInt(unvisitedNeighbors.size());
                Coordinate randomNeighbor = unvisitedNeighbors.get(randomNeighborIndex);
                int newRow = randomNeighbor.row();
                int newCol = randomNeighbor.col();
                grid[newRow][newCol] = new Cell(newRow, newCol, Cell.Type.PASSAGE);
                grid[(row + newRow) / 2][(col + newCol) / 2] = new Cell((row + newRow) / 2, (col + newCol) / 2, Cell.Type.PASSAGE);
                activeCells.add(randomNeighbor);
            } else {
                activeCells.remove(currentIndex);
            }
        }

        return new Maze(height, width, grid);
    }

    private List<Coordinate> getNeighbors(int row, int col, int height, int width) {
        List<Coordinate> neighbors = new ArrayList<>();
        int[] rowOffsets = {-2, 2, 0, 0};
        int[] colOffsets = {0, 0, -2, 2};
        for (int i = 0; i < 4; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];
            if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
                neighbors.add(new Coordinate(newRow, newCol));
            }
        }
        return neighbors;
    }
}
