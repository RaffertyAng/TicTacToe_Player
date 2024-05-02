import java.util.Scanner;

class Main {
    public static void main(String[] args) {
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
        boolean gameOver = false;
        Scanner moveInput = new Scanner(System.in);

        while (!gameOver) {
            printBoard(board);
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

            if (board[col - 1][row - 1] == ' ') {
                board[col - 1][row - 1] = player; // place the element
                gameOver = isGameOver(board, player);
                if (gameOver) {
                    System.out.println("Player " + player + " wins! ");
                } else {
                    player = (player == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("That space is occupied. Please Try again!");
            }
        }
        printBoard(board);
        start();
    }

    public static void printHelp() {
        System.out.println("Positions (row, column): ");
        System.out.println("1,1" + " | " + "2,1" + " | " + "3,1");
        System.out.println("1,2" + " | " + "2,2" + " | " + "3,2");
        System.out.println("1,3" + " | " + "2,3" + " | " + "3,3");
        System.out.println();
    }

    public static boolean isGameOver(char[][] board, char player) {
        // check the rows
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        // check the columns
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // check the diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
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