package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task3.AbstractFilter.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AbstractFilterTest {

    private static final Path TEST_DIRECTORY = Paths.get("test_directory");

    @Test
    @DisplayName("Test filtering regular files")
    void testRegularFileFilter() throws IOException {
        createTestFiles();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIRECTORY, REGULAR_FILE)) {
            assertThat(entries).containsExactlyInAnyOrder(
                TEST_DIRECTORY.resolve("file1.txt"),
                TEST_DIRECTORY.resolve("file2.png")
            );
        }
    }

    @Test
    @DisplayName("Test filtering readable files")
    void testReadableFileFilter() throws IOException {
        createTestFiles();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIRECTORY, READABLE)) {
            assertThat(entries).containsExactlyInAnyOrder(
                TEST_DIRECTORY.resolve("file1.txt"),
                TEST_DIRECTORY.resolve("file2.png")
            );
        }
    }

    @Test
    @DisplayName("Test filtering files larger than a specified size")
    void testLargerThanFilter() throws IOException {
        createTestFiles();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIRECTORY, largerThan(1))) {
            assertThat(entries).containsExactlyInAnyOrder(
                TEST_DIRECTORY.resolve("file1.txt"),
                TEST_DIRECTORY.resolve("file2.png")
            );
        }
    }

    @Test
    @DisplayName("Test filtering files by extension")
    void testHasExtensionFilter() throws IOException {
        createTestFiles();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIRECTORY, hasExtension("*.txt"))) {
            assertThat(entries).containsExactly(TEST_DIRECTORY.resolve("file1.txt"));
        }
    }

    @Test
    @DisplayName("Test filtering files by name using regex")
    void testFileNameMatchesFilter() throws IOException {
        createTestFiles();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIRECTORY, fileNameMatches("file\\d\\.txt"))) {
            assertThat(entries).containsExactly(TEST_DIRECTORY.resolve("file1.txt"));
        }
    }

    private static void createTestFiles() throws IOException {
        Files.createDirectories(TEST_DIRECTORY);

        List<String> file1Content = List.of("Hello, World!");
        Files.write(TEST_DIRECTORY.resolve("file1.txt"), file1Content);

        List<String> file2Content = List.of("PNG Image");
        Files.write(TEST_DIRECTORY.resolve("file2.png"), file2Content);
    }
}

