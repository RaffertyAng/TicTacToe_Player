import java.util.ArrayList;
import java.util.List;

public class PlayerAI {

    public static int[] getNextMove(char[][] board, char player) {
        int[] bestMove = minimax(board, player);
        return new int[]{bestMove[1], bestMove[2]};
    }

    private static int[] minimax(char[][] board, char player) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[3];

        List<int[]> possibleMoves = generateMoves(board);

        for (int[] move : possibleMoves) {
            int row = move[0];
            int col = move[1];

            if (board[row][col] == ' ') {
                board[row][col] = player;

                if (isWinner(board, player)) {
                    board[row][col] = ' ';
                    return new int[]{player == 'O' ? 1 : -1, row, col};
                }

                int score;
                if (player == 'O') {
                    score = minimax(board, 'X')[0];
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = score;
                        bestMove[1] = row;
                        bestMove[2] = col;
                    }
                } else {
                    score = minimax(board, 'O')[0];
                    if (score < bestScore) {
                        bestScore = score;
                        bestMove[0] = score;
                        bestMove[1] = row;
                        bestMove[2] = col;
                    }
                }

                board[row][col] = ' ';
            }
        }

        return bestMove;
    }

    private static List<int[]> generateMoves(char[][] board) {
        List<int[]> moves = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == ' ') {
                    moves.add(new int[]{row, col});
                }
            }
        }
        return moves;
    }

    private static boolean isWinner(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }
}