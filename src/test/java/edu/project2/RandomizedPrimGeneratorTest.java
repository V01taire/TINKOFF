package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RandomizedPrimGeneratorTest {

    @Test
    void generateShouldCreateValidMaze() {

        RandomizedPrimGenerator generator = new RandomizedPrimGenerator();
        int height = 5;
        int width = 5;

        Maze maze = generator.generate(height, width);

        assertNotNull(maze);
        assertEquals(height, maze.getHeight());
        assertEquals(width, maze.getWidth());
    }
}
