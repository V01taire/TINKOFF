package edu.project2;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleRendererTest {

    @Test
    void renderMazeShouldReturnValidString() {

        ConsoleRenderer renderer = new ConsoleRenderer();
        Maze maze = createTestMaze();

        String renderedMaze = renderer.render(maze);

        assertNotNull(renderedMaze);
    }

    @Test
    void renderMazeWithSolutionShouldReturnValidString() {

        ConsoleRenderer renderer = new ConsoleRenderer();
        Maze maze = createTestMaze();
        List<Coordinate> path = createTestPath();

        String renderedMazeWithSolution = renderer.render(maze, path);

        assertNotNull(renderedMazeWithSolution);
    }

    private Maze createTestMaze() {
        Cell[][] grid = {
            {new Cell(0, 0, Cell.Type.PASSAGE), new Cell(0, 1, Cell.Type.PASSAGE), new Cell(0, 2, Cell.Type.PASSAGE)},
            {new Cell(1, 0, Cell.Type.PASSAGE), new Cell(1, 1, Cell.Type.WALL), new Cell(1, 2, Cell.Type.PASSAGE)},
            {new Cell(2, 0, Cell.Type.PASSAGE), new Cell(2, 1, Cell.Type.PASSAGE), new Cell(2, 2, Cell.Type.PASSAGE)}
        };
        return new Maze(3, 3, grid);
    }

    private List<Coordinate> createTestPath() {

        return List.of(
            new Coordinate(0, 0),
            new Coordinate(0, 1),
            new Coordinate(1, 1),
            new Coordinate(2, 1),
            new Coordinate(2, 2)
        );
    }

}

