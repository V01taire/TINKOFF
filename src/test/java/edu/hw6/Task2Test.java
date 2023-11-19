package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @TempDir
    Path tempDir;

    private Path sourcePath;
    private Path destinationPath;

    @BeforeEach
    void setUp() throws IOException {
        sourcePath = Files.createFile(tempDir.resolve("source.txt"));
        Files.write(sourcePath, "Test content".getBytes());
        destinationPath = tempDir.resolve("destination.txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(sourcePath);
        Files.deleteIfExists(destinationPath);
    }

    @Test
    @DisplayName("Cloning a file creates a new file with the correct name")
    void cloneFile() throws IOException {
        Task2.cloneFile(sourcePath);

        assertThat(Files.exists(destinationPath)).isFalse();
    }


    @Test
    @DisplayName("Cloning a file with an existing copy creates a new copy with an incremented index")
    void cloneFileWithExistingCopies() throws IOException {
        Path existingCopy = tempDir.resolve("source - копия (2).txt");
        Files.copy(sourcePath, existingCopy);

        Task2.cloneFile(sourcePath);

        assertThat(Files.exists(destinationPath)).isFalse();
    }
}
