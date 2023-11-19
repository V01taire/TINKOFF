package edu.project3;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzerStorage {

    private final Map<String, Integer> addressCount;
    private final Map<String, Integer> answerCodeCount;
    private final Map<LocalDate, Integer> dateCount;
    private final Map<String, Integer> resourceCount;
    private long requestCount = 0;
    private long requestSize = 0;
    private List<String> files;

    public LogAnalyzerStorage() {
        addressCount = new HashMap<>();
        answerCodeCount = new HashMap<>();
        dateCount = new HashMap<>();
        resourceCount = new HashMap<>();
    }

    public void incrementAddressCount(String address) {
        addressCount.merge(address, 1, Integer::sum);
    }

    public List<Map.Entry<String, Integer>> getAddressCountsSorted() {
        return addressCount.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .toList();
    }

    public void incrementAnswerCodeCount(String code) {
        answerCodeCount.merge(code, 1, Integer::sum);
    }

    public List<Map.Entry<String, Integer>> getAnswerCodeCountsSorted() {
        return answerCodeCount.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .toList();
    }

    public void incrementDateCount(LocalDate date) {
        dateCount.merge(date, 1, Integer::sum);
    }

    public List<Map.Entry<LocalDate, Integer>> getDateCountsSorted() {
        return dateCount.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .toList();
    }

    public void incrementResourceCount(String resource) {
        resourceCount.merge(resource, 1, Integer::sum);
    }

    public List<Map.Entry<String, Integer>> getResourceCountsSorted() {
        return resourceCount.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .toList();
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void incrementRequestCount() {
        this.requestCount++;
    }

    public long getRequestSize() {
        return requestSize;
    }

    public void addRequestSize(long requestSize) {
        this.requestSize += requestSize;
    }
}

