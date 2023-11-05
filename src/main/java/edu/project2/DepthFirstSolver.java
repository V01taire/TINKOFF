package edu.project2;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DepthFirstSolver implements Solver {
    private static final int[] ROW_OFFSETS = {-1, 1, 0, 0};
    private static final int[] COL_OFFSETS = {0, 0, -1, 1};

    @Override
    @SuppressWarnings("MagicNumber")
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
        Stack<Coordinate> stack = new Stack<>();
        Map<Coordinate, Coordinate> parentMap = new HashMap<>();

        stack.push(start);
        visited[start.row()][start.col()] = true;

        while (!stack.isEmpty()) {
            Coordinate current = stack.pop();
            if (current.equals(end)) {
                return reconstructPath(parentMap, start, end);
            }

            for (int i = 0; i < 4; i++) {
                int newRow = current.row() + ROW_OFFSETS[i];
                int newCol = current.col() + COL_OFFSETS[i];
                if (isValidMove(maze, newRow, newCol) && !visited[newRow][newCol]) {
                    stack.push(new Coordinate(newRow, newCol));
                    visited[newRow][newCol] = true;
                    parentMap.put(new Coordinate(newRow, newCol), current);
                }

            }
        }

        return Collections.emptyList();
    }

    private boolean isValidMove(Maze maze, int row, int col) {
        return row >= 0
            && row < maze.getHeight()
            && col >= 0
            && col < maze.getWidth()
            && maze.getGrid()[row][col].type() == Cell.Type.PASSAGE;
    }

    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> parentMap, Coordinate start, Coordinate end) {
        LinkedList<Coordinate> path = new LinkedList<>();
        Coordinate current = end;
        while (!current.equals(start)) {
            path.addFirst(current);
            current = parentMap.get(current);
        }
        path.addFirst(start);
        return path;
    }
}
