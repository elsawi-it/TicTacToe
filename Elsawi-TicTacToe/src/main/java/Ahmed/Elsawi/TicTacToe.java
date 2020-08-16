package Ahmed.Elsawi;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TicTacToe {

    public static final int X = 1, O = -1;
    public static final int EMPTY = 0;
    public int player = 0;

    String firstPlayerName = null;
    String secondPlayerName = null;

    public void StartOrInfoOrExit() throws InterruptedException, IOException {
        System.out.println();
        System.out.println("Tic Tac Toe game :)\n");
        Thread.sleep(1500);
        System.out.println("Hello there!, Do you want to start the game, the game info or exit the game?");
        System.out.print("Please, write (start) or (info) or (exit): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        if (input.equalsIgnoreCase("info")) {
            info();
        } else if (input.equalsIgnoreCase("start")) {
            start();
        } else if (input.equalsIgnoreCase("exit")) {
            exitFromTheWholeProject();
        }else {
            System.out.println("Invalid input!");
            clearScreen();
            StartOrInfoOrExit();
        }
    }
    public void info(){
        System.out.println();
        System.out.println("..................................................................................................................\n" +
                "Tic Tac Toe Infos:\n\n"
                + "1- Tic-tac-toe or Xs and Os is a game for two players.\n" +
                "\n" +
                "2- Players turn to mark X or O, in a 3*3 grid (9 cells). \n" +
                "\n" +
                "3- The game board like the following examples, and the player of X or O has to enter the cell number to fill it.\n");
        System.out.println("  1  |  2  |  3  \t  O  |  X  |  O  ");
        System.out.println("-----+-----+-----\t-----+-----+-----");
        System.out.println("  4  |  5  |     \t  X  |  O  |     ");
        System.out.println("-----+-----+-----\t-----+-----+-----");
        System.out.println("     |  8  |  9  \t     |  X  |  O  "+"\n");


        System.out.println("4- The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row is the winner.\n" +
                "....................................................................................................................");

    }

    public void clearScreen() throws InterruptedException, IOException {
        //System.out.println("Ahmed (X) vs (O) Mostafa ");
        Thread.sleep(1000);
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public void exitFromTheWholeProject() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override public void write(int b) {}
        }));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("First player, please enter your first name : ");

        firstPlayerName = scanner.nextLine().trim();
        while (!firstPlayerName.matches("[a-zA-z]+")) {
            System.out.print("Invalid name!\n");
            System.out.print("First player, please retype your first name : ");
            firstPlayerName = scanner.nextLine().trim();
        }
        System.out.println();

        System.out.print("Second player, please enter your first name : ");
        secondPlayerName = scanner.nextLine().trim();
        while (!secondPlayerName.matches("[a-zA-z]+")) {
            System.out.print("Invalid name!\n");
            System.out.print("Second player, please retype your first name : ");
            secondPlayerName = scanner.nextLine().trim();
        }
        System.out.println();

        //System.out.printf("Hello there!, my name is Tic Tac Toe :)");
        //System.out.print("\n\n");
        //setXOrO();
    }

    public void setXOrO() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s!, you love (X) or (O) : ", firstPlayerName);
        String xOrO = scanner.next();
        //System.out.print("\n");
        int xO;
        if (xOrO.equalsIgnoreCase("x") || xOrO.equalsIgnoreCase("o")) {
            if (xOrO.equalsIgnoreCase("x")) {
                xO = 1;
                playerXorO(xO);
            } else if (xOrO.equalsIgnoreCase("o")) {
                xO = -1;
                playerXorO(xO);
            }
        } else {
            //System.out.println(" :( ");
            System.out.println("Sorry!, you can only enter values like (x, X, o, O), please try again :)\n\n");
            setXOrO();
        }
    }


    public void playerXorO(int num) {
        player = num;
    }


    private int[][] board = new int[3][3];
    public boolean isEmpty = false;

    /**
     * Puts an X or O mark at position i,j.
     */
    public void putSign(int x, int y) {
        if (x < 0 || x > 2 || y < 0 || y > 2) {
            System.out.println("Invalid board position");
            return;
        }
        if (board[x][y] != EMPTY) {
            System.out.println("Board position occupied");
            return;
        }
        board[x][y] = player;   // place the mark for the current player
        player = -player;       // switch players (uses fact that O = - X)
    }

    /**
     * Checks whether the board configuration is a win for the given player.
     */
    public boolean isWin(int player) {
        return ((board[0][0] + board[0][1] + board[0][2] == player * 3) ||
                (board[1][0] + board[1][1] + board[1][2] == player * 3) ||
                (board[2][0] + board[2][1] + board[2][2] == player * 3) ||
                (board[0][0] + board[1][0] + board[2][0] == player * 3) ||
                (board[0][1] + board[1][1] + board[2][1] == player * 3) ||
                (board[0][2] + board[1][2] + board[2][2] == player * 3) ||
                (board[0][0] + board[1][1] + board[2][2] == player * 3) ||
                (board[2][0] + board[1][1] + board[0][2] == player * 3));
    }

    /**
     * display the winning player or indicate a tie (or unfinished game).
     */
    public void displayWinner() {
        if (isWin(X)) {
            System.out.println("\n X wins...!!");
            isEmpty = false;
        } else if (isWin(O)) {
            System.out.println("\n O wins...!!");
            isEmpty = false;
        } else {
            if (!isEmpty) {
                System.out.println("its a tie");
            }

        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        isEmpty = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case X:
                        s.append(" X ");
                        break;
                    case O:
                        s.append(" O ");
                        break;
                    case EMPTY:
                        s.append("   ");
                        isEmpty = true;
                        break;
                }
                if (j < 2) {
                    s.append("|");
                }

            }
            if (i < 2) {
                s.append("\n-----------\n");
            }
        }
        return s.toString();
    }

}
