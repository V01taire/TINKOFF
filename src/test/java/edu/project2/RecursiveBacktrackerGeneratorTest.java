package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecursiveBacktrackerGeneratorTest {

    @Test
    void generateShouldCreateValidMaze() {

        RecursiveBacktrackerGenerator generator = new RecursiveBacktrackerGenerator();
        int height = 5;
        int width = 5;

        Maze maze = generator.generate(height, width);

        assertNotNull(maze);
        assertEquals(height, maze.getHeight());
        assertEquals(width, maze.getWidth());
    }
}

