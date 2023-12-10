package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectoryCounterTask extends RecursiveTask<List<File>> {

    private final File directory;

    public DirectoryCounterTask(File directory) {
        this.directory = directory;
    }

    @Override
    protected List<File> compute() {
        List<DirectoryCounterTask> subtasks = new ArrayList<>();
        List<File> result = new ArrayList<>();

        File[] contents = directory.listFiles();
        if (contents != null) {
            for (File file : contents) {
                if (file.isDirectory()) {
                    DirectoryCounterTask subtask = new DirectoryCounterTask(file);
                    subtask.fork();
                    subtasks.add(subtask);
                } else {
                    result.add(file);
                }
            }
        }

        for (DirectoryCounterTask subtask : subtasks) {
            result.addAll(subtask.join());
        }

        return result;
    }
}


