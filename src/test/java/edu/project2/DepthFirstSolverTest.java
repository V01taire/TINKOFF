package edu.project2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class DepthFirstSolverTest {

    @Test
    void testValidPath() {
        Cell[][] grid = {
            {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.PASSAGE)},
            {new Cell(1, 0, Cell.Type.PASSAGE), new Cell(1, 1, Cell.Type.PASSAGE)}
        };
        Maze maze = new Maze(2, 2, grid);
        DepthFirstSolver solver = new DepthFirstSolver();
        List<Coordinate> path = solver.solve(maze, new Coordinate(0, 0), new Coordinate(1, 1));
        assertEquals(List.of(new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(1, 1)), path);
    }

    @Test
    void testInvalidEndPoint() {
        Cell[][] grid = {
            {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.PASSAGE)},
            {new Cell(1, 0, Cell.Type.PASSAGE), new Cell(1, 1, Cell.Type.PASSAGE)}
        };
        Maze maze = new Maze(2, 2, grid);
        DepthFirstSolver solver = new DepthFirstSolver();
        List<Coordinate> path = solver.solve(maze, new Coordinate(0, 0), new Coordinate(1, 2));
        assertEquals(List.of(), path);
    }

    @Test
    void testNoPathFound() {
        Cell[][] grid = {
            {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.PASSAGE)},
            {new Cell(1, 0, Cell.Type.PASSAGE), new Cell(1, 1, Cell.Type.PASSAGE)}
        };
        Maze maze = new Maze(2, 2, grid);
        DepthFirstSolver solver = new DepthFirstSolver();
        List<Coordinate> path = solver.solve(maze, new Coordinate(0, 0), new Coordinate(1, 1));
        assertEquals(List.of(), new ArrayList<>());
    }
}

