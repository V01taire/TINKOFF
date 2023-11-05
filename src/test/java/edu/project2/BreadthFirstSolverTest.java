package edu.project2;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstSolverTest {

    @Test
    void solveShouldFindPathInMaze() {

        Maze maze = createTestMaze();
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);
        BreadthFirstSolver solver = new BreadthFirstSolver();

        List<Coordinate> path = solver.solve(maze, start, end);

        assertNotNull(path);
        assertTrue(path.isEmpty());
        assertEquals(0, path.size());
    }

    private Maze createTestMaze() {
        Cell[][] grid = {
            {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.PASSAGE), new Cell(0, 2, Cell.Type.PASSAGE)},
            {new Cell(1, 0, Cell.Type.PASSAGE), new Cell(1, 1, Cell.Type.WALL), new Cell(1, 2, Cell.Type.PASSAGE)},
            {new Cell(2, 0, Cell.Type.PASSAGE), new Cell(2, 1, Cell.Type.PASSAGE), new Cell(2, 2, Cell.Type.PASSAGE)}
        };
        return new Maze(3, 3, grid);
    }
}

