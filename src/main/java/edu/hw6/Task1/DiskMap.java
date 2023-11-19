package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MultipleStringLiterals")

public class DiskMap implements Map<String, String> {

    private final String filePath;
    private final Map<String, String> inMemoryMap;
    private static final Logger LOGGER = LogManager.getLogger(DiskMap.class);

    public DiskMap(String filePath) {
        this.filePath = filePath;
        this.inMemoryMap = new HashMap<>();
        loadFromDisk();
    }

    private void loadFromDisk() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    inMemoryMap.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error loading data from disk: " + e.getMessage());
        }
    }

    private void saveToDisk() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Entry<String, String> entry : inMemoryMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.error("Error saving data to disk: " + e.getMessage());
        }
    }

    @Override
    public int size() {
        return inMemoryMap.size();
    }

    @Override
    public boolean isEmpty() {
        return inMemoryMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return inMemoryMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return inMemoryMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return inMemoryMap.get(key);
    }

    @Override
    public String put(String key, String value) {
        String previousValue = inMemoryMap.put(key, value);
        saveToDisk();
        LOGGER.info("Put: Key={}, Value={}", key, value);
        return previousValue;
    }

    @Override
    public String remove(Object key) {
        String removedValue = inMemoryMap.remove(key);
        saveToDisk();
        LOGGER.info("Remove: Key={}, Value={}", key, removedValue);
        return removedValue;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        inMemoryMap.putAll(m);
        saveToDisk();
        LOGGER.info("PutAll: {}", m);
    }

    @Override
    public void clear() {
        inMemoryMap.clear();
        saveToDisk();
        LOGGER.info("Cleared the map");
    }

    @Override
    public Set<String> keySet() {
        return inMemoryMap.keySet();
    }

    @Override
    public java.util.Collection<String> values() {
        return inMemoryMap.values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return inMemoryMap.entrySet();
    }
}
