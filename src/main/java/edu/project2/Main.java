package edu.project2;

import java.util.List;
import java.util.Scanner;

@SuppressWarnings({"MagicNumber", "HideUtilityClassConstructor", "LineLength", "RegexpSinglelineJava"})
public class Main {
    public static void main(String[] args) {

        final String LABYRINTH_ICON = "\uD83C\uDF84";

        Scanner scanner = new Scanner(System.in);
        Generator generator = null;
        Solver solver = null;

        TinkoffIntro.generateTinkoffIntro();

        System.out.println("    Выберите алгоритм для генерации лабиринта и поиска пути:");
        System.out.println("    1) Алгоритм RecursiveBacktracker и DFS");
        System.out.print("""
                2) Алгоритм Прима и BFS

                \u001B[3m\u001B[38;2;0;128;0mВАШ ВЫБОР:\u001B[0m \
            """);

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                generator = new RecursiveBacktrackerGenerator();
                solver = new DepthFirstSolver();
                break;
            case 2:
                generator = new RandomizedPrimGenerator();
                solver = new BreadthFirstSolver();
                break;
            default:
                System.out.println("    \u001B[3m\u001B[4m\u001B[38;2;255;0;0mНекорректный выбор. Пожалуйста, перезапустите задание и выберите 1 или 2.\u001B[0m");
                return;
        }

        Maze maze = generator.generate(11, 85);
        List<Coordinate> path = solver.solve(maze,
            new Coordinate(1, 1),
            new Coordinate(maze.getHeight() - 2, maze.getWidth() - 2));

        Renderer renderer = new ConsoleRenderer();

        System.out.println("\n" + TinkoffIntro.renderEmptySpace(75) + LABYRINTH_ICON + "\u001B[1mСГЕНЕРИРОВАННЫЙ ЛАБИРИНТ\u001B[0m" + LABYRINTH_ICON + "\n");
        System.out.println(renderer.render(maze));
        System.out.println(TinkoffIntro.renderEmptySpace(82) + LABYRINTH_ICON + "\u001B[1mРЕШЕНИЕ\u001B[0m" + LABYRINTH_ICON + "\n");
        System.out.println(renderer.render(maze, path));
    }
}
