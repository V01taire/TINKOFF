package edu.project3.Parsers;

import edu.project3.Interfaces.Parser;
import edu.project3.LogFiles.LogAnalyzerStorage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"LineLength", "MultipleStringLiterals", "MagicNumber"})
public class FileParser implements Parser {

    private final LogAnalyzerStorage storage;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final String sourcePath;
    private List<Path> fileList;

    private static final int BUFFER_SIZE = 128;
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^(.*)"
            + "\\s-\\s.*\\s"
            + "\\[(.*?):.*\\s"
            + "\"(.*)\\s(.*)\\s.*\"\\s"
            + "(\\d{3})\\s"
            + "(\\d{1,15})"
            + "\\s\".*\"\\s\".*\"$"
    );

    public FileParser(String sourcePath, LogAnalyzerStorage storage, LocalDate fromDate, LocalDate toDate) {
        this.sourcePath = sourcePath;
        this.storage = storage;
        this.fromDate = fromDate == null ? LocalDate.MIN : fromDate;
        this.toDate = toDate == null ? LocalDate.MAX : toDate;
        this.fileList = new ArrayList<>();
    }

    private void parseLogEntry(Matcher matcher) {
        LocalDate logDate = LocalDate.parse(
            matcher.group(2), DateTimeFormatter.ofPattern("dd/MMM/yyyy", Locale.ENGLISH)
        );

        if ((fromDate.isBefore(logDate) || fromDate.isEqual(logDate)) && (toDate.isAfter(logDate) || toDate.isEqual(logDate))) {
            storage.incrementRequestCount();
            storage.incrementAddressCount(matcher.group(1));
            storage.incrementDateCount(logDate);
            storage.incrementResourceCount(matcher.group(4));
            storage.incrementAnswerCodeCount(matcher.group(5));
            storage.addRequestSize(Long.parseLong(matcher.group(6)));
        }
    }

    @Override
    public void readData() throws IOException {
        collectFiles();

        if (fileList.isEmpty()) {
            throw new IOException("No log files found");
        }

        storage.setFiles(fileList.stream().map(Path::getFileName).map(Path::toString).toList());
        for (Path file : fileList) {
            processLogFile(file);
        }
    }

    private void processLogFile(Path filePath) throws IOException {
        StringBuilder logEntry = new StringBuilder();

        try (FileChannel fileChannel = FileChannel.open(filePath)) {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            while (fileChannel.read(buffer) != -1) {
                buffer.flip();

                while (buffer.hasRemaining()) {
                    char ch = (char) buffer.get();

                    if (ch != '\n') {
                        logEntry.append(ch);

                    } else {
                        Matcher matcher = LOG_PATTERN.matcher(logEntry.toString());
                        if (matcher.find()) {
                            parseLogEntry(matcher);
                        }
                        logEntry.setLength(0);
                    }
                }

                buffer.clear();
            }
        }
    }

    private void collectFiles() throws IOException {
        String[] pathArray = sourcePath.split("\\*{2}");

        if (pathArray.length == 1) {
            if (pathArray[0].endsWith("*")) {
                Path currentPath = Path.of(pathArray[0].replace("*", ""));
                traverseDirectory(currentPath);
            } else {
                this.fileList.add(Path.of(pathArray[0]));
            }
        } else {
            Path currentPath = Path.of(pathArray[0]);
            traverseDirectory(currentPath);
            this.fileList = this.fileList.stream().filter(
                file -> {
                    StringBuilder filePattern = new StringBuilder();
                    for (int i = 0; i < pathArray.length - 1; i++) {
                        filePattern.append(
                            pathArray[i].replace("/", "\\\\")
                        ).append(".*");
                    }
                    filePattern.append(pathArray[pathArray.length - 1].replace("/", "\\\\"));
                    String filePath = file.toAbsolutePath().toString();
                    Pattern pattern = Pattern.compile(filePattern.toString());
                    Matcher matcher = pattern.matcher(filePath);
                    return matcher.find();
                }
            ).toList();
        }
    }

    private void traverseDirectory(Path directory) throws IOException {
        Files.walkFileTree(directory, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (!Files.isDirectory(file)) {
                    fileList.add(file.toAbsolutePath());
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}

