package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {

    private static final Logger LOGGER = LogManager.getLogger(Task2.class);

    public static void cloneFile(Path sourcePath) throws IOException {
        String fileName = sourcePath.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        String baseName = (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);

        Pattern pattern = Pattern.compile("(\\s-\\sкопия\\s\\(\\d+\\))");

        int maxIndex = 0;

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourcePath.getParent())) {
            for (Path path : directoryStream) {
                if (Files.isRegularFile(path)) {
                    Matcher matcher = pattern.matcher(path.getFileName().toString());
                    if (matcher.matches()) {
                        int index = Integer.parseInt(matcher.group(1).replaceAll("[^0-9]", ""));
                        maxIndex = Math.max(maxIndex, index);
                    }
                }
            }
        }

        String newFileName = baseName + " - копия (" + (maxIndex + 1) + ")" + extension;

        Path destinationPath = sourcePath.resolveSibling(newFileName);

        Files.copy(sourcePath, destinationPath);

        LOGGER.info("File cloned successfully. New file: {}", destinationPath);
    }
}

