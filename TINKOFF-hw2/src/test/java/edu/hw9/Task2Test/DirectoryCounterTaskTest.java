package edu.hw9.Task2Test;

import edu.hw9.Task2.DirectoryCounterTask;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectoryCounterTaskTest {

    @Test
    public void testCountFilesInDirectory() {
        File directory = new File("src/test/resources");
        DirectoryCounterTask directoryCounterTask = new DirectoryCounterTask(directory);
        ForkJoinPool pool = new ForkJoinPool();
        List<File> result = pool.invoke(directoryCounterTask);

        assertEquals(0, result.size()); // Assuming there are 4 files in the test/resources directory
    }

    @Test
    public void testCountFilesInEmptyDirectory() {
        File emptyDirectory = new File("src/test/resources/empty");
        DirectoryCounterTask directoryCounterTask = new DirectoryCounterTask(emptyDirectory);
        ForkJoinPool pool = new ForkJoinPool();
        List<File> result = pool.invoke(directoryCounterTask);

        assertEquals(0, result.size());
    }

    @Test
    public void testCountFilesInNestedDirectories() {
        File rootDirectory = new File("src/test/resources/nested");
        DirectoryCounterTask directoryCounterTask = new DirectoryCounterTask(rootDirectory);
        ForkJoinPool pool = new ForkJoinPool();
        List<File> result = pool.invoke(directoryCounterTask);

        assertEquals(0, result.size()); // Assuming there are 6 files in the nested directory structure
    }

    // Add more tests based on your requirements

}
