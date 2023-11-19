package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

@SuppressWarnings("MultipleStringLiterals")
public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    default AbstractFilter and(AbstractFilter filter) {
        return path -> accept(path) && filter.accept(path);
    }

    AbstractFilter REGULAR_FILE = Files::isRegularFile;

    AbstractFilter READABLE = Files::isReadable;

    static AbstractFilter largerThan(int size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    static AbstractFilter hasExtension(String fileExtension) {
        return path -> {
            Pattern pattern = Pattern.compile(fileExtension.replace("*", ".*") + "$");
            return pattern.matcher(path.getFileName().toString()).find();
        };
    }

    static AbstractFilter fileNameMatches(String fileRegex) {
        return path -> Pattern.compile(fileRegex).matcher(path.getFileName().toString()).find();
    }

    static AbstractFilter hasMagicNumber(int... bytes) {
        return path -> {
            try {
                byte[] fileBytes = Files.readAllBytes(path);
                if (fileBytes.length < bytes.length) {
                    return false;
                }
                for (int i = 0; i < bytes.length; i++) {
                    String expectedByte = String.format("%02x", bytes[i]);
                    String actualByte = String.format("%02x", fileBytes[i]);
                    if (!expectedByte.equals(actualByte)) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
