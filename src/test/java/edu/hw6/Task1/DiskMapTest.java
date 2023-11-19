package edu.hw6.Task1;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

public class DiskMapTest {

    private DiskMap diskMap;
    private static final String TEST_KEY = "testKey";
    private static final String TEST_VALUE = "testValue";

    @TempDir
    static Path tempDir;

    @BeforeEach
    void setUp() {
        Path filePath = tempDir.resolve("testDiskMapFile.txt");
        diskMap = new DiskMap(filePath.toString());
    }

    @Test
    @DisplayName("Put and Get value from DiskMap")
    void putAndGet() {
        // when
        diskMap.put(TEST_KEY, TEST_VALUE);

        // then
        assertThat(diskMap.get(TEST_KEY)).isEqualTo(TEST_VALUE);
    }

    @Test
    @DisplayName("Remove value from DiskMap")
    void remove() {
        // given
        diskMap.put(TEST_KEY, TEST_VALUE);

        // when
        diskMap.remove(TEST_KEY);

        // then
        assertThat(diskMap.get(TEST_KEY)).isNull();
    }

    @Test
    @DisplayName("Check size of DiskMap")
    void size() {
        // given
        diskMap.put(TEST_KEY, TEST_VALUE);

        // when
        int size = diskMap.size();

        // then
        assertThat(size).isEqualTo(3);
    }

    @Test
    @DisplayName("Check if DiskMap is empty")
    void isEmpty() {
        // when
        boolean isEmpty = diskMap.isEmpty();

        // then
        assertThat(isEmpty).isFalse();
    }

    @Test
    @DisplayName("Check if DiskMap contains key")
    void containsKey() {
        // given
        diskMap.put(TEST_KEY, TEST_VALUE);

        // when
        boolean containsKey = diskMap.containsKey(TEST_KEY);

        // then
        assertThat(containsKey).isTrue();
    }

    @Test
    @DisplayName("Check if DiskMap contains value")
    void containsValue() {
        // given
        diskMap.put(TEST_KEY, TEST_VALUE);

        // when
        boolean containsValue = diskMap.containsValue(TEST_VALUE);

        // then
        assertThat(containsValue).isTrue();
    }

    @Test
    @DisplayName("PutAll values to DiskMap")
    void putAll() {
        // given
        Map<String, String> testData = new HashMap<>();
        testData.put("key1", "value1");
        testData.put("key2", "value2");

        // when
        diskMap.putAll(testData);

        // then
        assertThat(diskMap.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Clear DiskMap")
    void clear() {
        // given
        diskMap.put(TEST_KEY, TEST_VALUE);

        // when
        diskMap.clear();

        // then
        assertThat(diskMap.size()).isZero();
    }

    @Test
    @DisplayName("Check keySet of DiskMap")
    void keySet() {
        // given
        diskMap.put(TEST_KEY, TEST_VALUE);

        // when
        assertThat(diskMap.keySet()).containsExactly(TEST_KEY);
    }


    @Test
    @DisplayName("Check entrySet of DiskMap")
    void entrySet() {
        // given
        diskMap.put(TEST_KEY, TEST_VALUE);

        // when
        assertThat(diskMap.entrySet()).hasSize(1);
    }
}
