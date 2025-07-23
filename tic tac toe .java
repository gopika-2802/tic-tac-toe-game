import java.util.Scanner;

public class TicTacToe {
    static String[] board = new String[9];

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            board[i] = " ";
        }
        playGame();
    }

    public static void printBoard() {
        System.out.println();
        System.out.println(board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("--+---+--");
        System.out.println(board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("--+---+--");
        System.out.println(board[6] + " | " + board[7] + " | " + board[8]);
        System.out.println();
    }

    public static boolean checkWin(String player) {
        int[][] winCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };

        for (int[] combo : winCombinations) {
            if (board[combo[0]].equals(player) &&
                board[combo[1]].equals(player) &&
                board[combo[2]].equals(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDraw() {
        for (String cell : board) {
            if (cell.equals(" ")) {
                return false;
            }
        }
        return true;
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        String currentPlayer = "X";

        while (true) {
            printBoard();
            System.out.print("Player " + currentPlayer + ", choose a position (1-9): ");
            int move;

            try {
                move = Integer.parseInt(scanner.nextLine()) - 1;

                if (move < 0 || move > 8 || !board[move].equals(" ")) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Enter a number between 1 and 9.");
                continue;
            }

            board[move] = currentPlayer;

            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } else if (checkDraw()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }

        scanner.close();
    }
}
