package edu.hw1;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task8 {

    private Task8() {
        throw new AssertionError("Utility class, do not instantiate");
    }

    private static final int MOVE_OFFSET_FOR_1 = 1;
    private static final int MOVE_OFFSET_FOR_MINUS_1 = -1;
    private static final int MOVE_OFFSET_FOR_2 = 2;
    private static final int MOVE_OFFSET_FOR_MINUS_2 = -2;
    private static final int BOARD_SIZE = 8;

    public static boolean knightBoardCapture(int[][] board) {

        int[] dx = {MOVE_OFFSET_FOR_MINUS_2, MOVE_OFFSET_FOR_MINUS_2, MOVE_OFFSET_FOR_MINUS_1, MOVE_OFFSET_FOR_MINUS_1,
            MOVE_OFFSET_FOR_1, MOVE_OFFSET_FOR_1, MOVE_OFFSET_FOR_2, MOVE_OFFSET_FOR_2};
        int[] dy = {MOVE_OFFSET_FOR_MINUS_1, MOVE_OFFSET_FOR_1, MOVE_OFFSET_FOR_MINUS_2, MOVE_OFFSET_FOR_2,
            MOVE_OFFSET_FOR_MINUS_2, MOVE_OFFSET_FOR_2, MOVE_OFFSET_FOR_MINUS_1, MOVE_OFFSET_FOR_1};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    for (int k = 0; k < BOARD_SIZE; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && board[x][y] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}


