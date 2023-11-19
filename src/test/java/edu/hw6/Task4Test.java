package edu.hw6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import edu.hw6.Task4;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class Task4Test {

    @Test
    void testWriteToCheckedOutputStream() throws IOException {
        // Arrange
        String fileName = "testFile.txt";
        String textToWrite = "Hello, World!";

        // Act
        Task4.writeToCheckedOutputStream(fileName, textToWrite);

        // Assert
        assertFileContentMatches(fileName, textToWrite);
    }

    private void assertFileContentMatches(String fileName, String expectedContent) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String actualContent = reader.readLine();
            assertThat(actualContent).isEqualTo(expectedContent);
        } catch (IOException e) {
            fail("Failed to read file content: " + e.getMessage());
        }
    }

    @Test
    void testWriteToCheckedOutputStreamChecksum() throws IOException {
        // Arrange
        String fileName = "testFile.txt";
        String textToWrite = "Hello, World!";
        Checksum expectedChecksum = calculateChecksum(textToWrite);

        // Act
        Task4.writeToCheckedOutputStream(fileName, textToWrite);

        // Assert
        Checksum actualChecksum = calculateChecksumFromFile(fileName);
        assertThat(actualChecksum.getValue()).isEqualTo(expectedChecksum.getValue());
    }

    private Checksum calculateChecksum(String input) {
        byte[] bytes = input.getBytes();
        Checksum checksum = new CRC32();
        checksum.update(bytes, 0, bytes.length);
        return checksum;
    }

    private Checksum calculateChecksumFromFile(String fileName) throws IOException {
        try (
            FileInputStream fileInputStream = new FileInputStream(fileName);
            CheckedInputStream checkedInputStream = new CheckedInputStream(fileInputStream, new CRC32())
        ) {
            byte[] buffer = new byte[8192];
            while (checkedInputStream.read(buffer) != -1) {
                // Reading to calculate checksum
            }
            return checkedInputStream.getChecksum();
        }
    }
}

