package edu.hw3.Task2;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {
    public static List<String> clusterize(String input) {
        List<String> clusters = new ArrayList<>();
        int openCount = 0;
        int closeCount = 0;
        StringBuilder currentCluster = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == '(') {
                openCount++;
                currentCluster.append(c);
            } else if (c == ')') {
                closeCount++;
                currentCluster.append(c);
                if (openCount == closeCount) {
                    clusters.add(currentCluster.toString());
                    currentCluster = new StringBuilder();
                    openCount = 0;
                    closeCount = 0;
                }
            } else {
                currentCluster.append(c);
            }
        }

        return clusters;
    }
}
