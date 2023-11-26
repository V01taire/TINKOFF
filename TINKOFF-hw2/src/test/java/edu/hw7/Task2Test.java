package edu.hw3;

import edu.hw3.Task2.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    @DisplayName("Тест кластеризации строки с одним кластером")
    void testClusterizeSingleCluster() {
        // given
        String input = "((()))";
        List<String> expectedClusters = List.of("((()))");

        // when
        List<String> clusters = Task2.clusterize(input);

        // then
        assertEquals(expectedClusters, clusters);
    }

    @Test
    @DisplayName("Тест кластеризации строки с несколькими кластерами")
    void testClusterizeMultipleClusters() {
        // given
        String input = "((()))(())()()(()())";
        List<String> expectedClusters = List.of("((()))", "(())", "()", "()", "(()())");

        // when
        List<String> clusters = Task2.clusterize(input);

        // then
        assertEquals(expectedClusters, clusters);
    }

    @Test
    @DisplayName("Тест кластеризации строки с вложенными кластерами")
    void testClusterizeNestedClusters() {
        // given
        String input = "((())())(()(()()))";
        List<String> expectedClusters = List.of("((())())", "(()(()()))");

        // when
        List<String> clusters = Task2.clusterize(input);

        // then
        assertEquals(expectedClusters, clusters);
    }
}

