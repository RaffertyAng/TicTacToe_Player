import java.util.Scanner;

public class TicTacToeGame {
    public TicTacToeGame() {
        start();
    }

    public static void start() {
        Scanner input = new Scanner(System.in);
        int command = 0;
        while (command != 1 && command != 2) {
            printMenu();
            command = input.nextInt();
        }

        switch (command) {
            case 1:
                playGame();
                break;
            case 2:
                printHelp();
                start();
                break;
        }
    }

    public static void printMenu() {
        System.out.println("Enter 1 to play");
        System.out.println("Enter 2 for row and column numbers");
    }

    public static void playGame() {
        char[][] board = new char[3][3];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }

        char player = 'X';
        boolean gameWon = false;
        boolean gameDrawn = false;
        Scanner moveInput = new Scanner(System.in);

        while (!gameWon && !gameDrawn) {
            printBoard(board);

            if (player == 'X') {
                System.out.println("Player " + player + " to move ");

                int row = 0;
                while (row < 1 || row > 3) {
                    System.out.print("Enter row number: ");
                    if (moveInput.hasNextInt()) {
                        row = moveInput.nextInt();
                    } else {
                        moveInput.next();
                    }
                }

                int col = 0;
                while (col < 1 || col > 3) {
                    System.out.print("Enter column number: ");
                    if (moveInput.hasNextInt()) {
                        col = moveInput.nextInt();
                    } else {
                        moveInput.next();
                    }
                }
                System.out.println();

                if (board[row - 1][col - 1] == ' ') {
                    board[row - 1][col - 1] = player; // place the element
                    gameWon = isGameWon(board, player);
                    gameDrawn = isGameDrawn(board);
                    if (gameWon) {
                        System.out.println("Player " + player + " wins! ");
                    } else if (gameDrawn) {
                        System.out.println("Game ends in a draw! ");
                    } else {
                        player = (player == 'X') ? 'O' : 'X';
                    }
                } else {
                    System.out.println("That space is occupied. Please Try again!");
                }
            } else if (player == 'O') {
                System.out.println("Player O to move ");
                int[] aiMove = PlayerAI.getNextMove(board, 'O');
                int row = aiMove[0];
                int col = aiMove[1];
                board[row][col] = player;
                gameWon = isGameWon(board, player);
                gameDrawn = isGameDrawn(board);
                if (gameWon) {
                    System.out.println("Player " + player + " wins! ");
                } else if (gameDrawn) {
                    System.out.println("Game ends in a draw! ");
                } else {
                    player = (player == 'X') ? 'O' : 'X';
                }
            }
        }
        printBoard(board);
        start();
    }

    public static void printHelp() {
        System.out.println("Positions (row, column): ");
        System.out.println("1,1" + " | " + "1,2" + " | " + "1,3");
        System.out.println("2,1" + " | " + "2,2" + " | " + "2,3");
        System.out.println("3,1" + " | " + "3,2" + " | " + "3,3");
        System.out.println();
    }

    public static boolean isGameWon(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public static boolean isGameDrawn(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col]);
                if (col < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}