package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;

@SuppressWarnings({"HideUtilityClassConstructor", "LineLength"})
public class Task4 {

    public static void writeToCheckedOutputStream(String fileName, String textToWrite) {
        byte[] bytes = textToWrite.getBytes();
        Checksum checksum = new CRC32();
        checksum.update(bytes, 0, bytes.length);

        try {
            Path path = Path.of(fileName);
            createFileIfNotExists(path);

            try (
                OutputStream fileOutputStream = new FileOutputStream(fileName);
                CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, checksum);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
                PrintWriter writer = new PrintWriter(outputStreamWriter)
            ) {
                writer.write(textToWrite);
            }

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static void createFileIfNotExists(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }
}
