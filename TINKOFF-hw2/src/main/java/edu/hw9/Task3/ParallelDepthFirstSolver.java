package edu.hw9.Task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.lang.Thread.sleep;

@SuppressWarnings({"MagicNumber", "LineLength"})
public class ParallelDepthFirstSolver implements Solver {
    private static final int THREAD_POOL_SIZE = 4;
    private static final int[] ROW_OFFSETS = {-1, 1, 0, 0};
    private static final int[] COL_OFFSETS = {0, 0, -1, 1};
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
        Map<Coordinate, Coordinate> parentMap = new ConcurrentHashMap<>();
        List<Coordinate> result = Collections.synchronizedList(new ArrayList<>());

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        executorService.submit(() -> {
            depthFirstSearch(maze, start, end, visited, parentMap, result);
            executorService.shutdown();
        });

        while (!executorService.isTerminated()) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException Error");
            }
        }

        if (!result.isEmpty()) {
            return reconstructPath(parentMap, start, end);
        } else {
            return Collections.emptyList();
        }
    }

    private void depthFirstSearch(Maze maze, Coordinate current, Coordinate end,
        boolean[][] visited, Map<Coordinate, Coordinate> parentMap, List<Coordinate> result) {
        if (current.equals(end)) {
            result.add(current);
            return;
        }

        visited[current.row()][current.col()] = true;

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int newRow = current.row() + ROW_OFFSETS[i];
            int newCol = current.col() + COL_OFFSETS[i];
            if (isValidMove(maze, newRow, newCol) && !visited[newRow][newCol]) {
                Future<?> future = depthFirstSearchAsync(maze, new Coordinate(newRow, newCol), end, visited, parentMap, result);
                futures.add(future);
            }
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error("InterruptedException | ExecutionException error");
            }
        }
    }

    private Future<?> depthFirstSearchAsync(Maze maze, Coordinate current, Coordinate end,
        boolean[][] visited, Map<Coordinate, Coordinate> parentMap, List<Coordinate> result) {
        return CompletableFuture.runAsync(() -> depthFirstSearch(maze, current, end, visited, parentMap, result));
    }

    private boolean isValidMove(Maze maze, int row, int col) {
        return row >= 0
            && row < maze.getHeight()
            && col >= 0
            && col < maze.getWidth()
            && maze.getGrid()[row][col].type() == Cell.Type.PASSAGE;
    }

    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> parentMap, Coordinate start, Coordinate end) {
        LinkedList<Coordinate> path = new LinkedList<>();
        Coordinate current = end;
        while (!current.equals(start)) {
            path.addFirst(current);
            current = parentMap.get(current);
        }
        path.addFirst(start);
        return path;
    }
}
