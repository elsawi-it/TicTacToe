package Ahmed.Elsawi;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    final int[] inputCellArray = new int[9];

    String cell1 = " ", cell2 = " ", cell3 = " ",
           cell4 = " ", cell5 = " ", cell6 = " ",
           cell7 = " ", cell8 = " ", cell9 = " ";

    String firstPlayerName = null;
    String secondPlayerName = null;


    public void fillArray() {
        Arrays.fill(inputCellArray, 0);
    }

    public void gameName() {
        System.out.println();
        System.out.println("TicTacToe game\n");
    }

    public void firstVsSecond() {
        System.out.println(firstPlayerName.toUpperCase() + " [X] vs [O] " + secondPlayerName.toUpperCase());
        System.out.println();
    }

    public void exitFromTheWholeProject() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
            }
        }));
    }

    public void clearScreen() throws InterruptedException, IOException {
        Thread.sleep(1000);
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public void gridWithNumbers() {
        System.out.println("  1  |  2  |  3  ");
        System.out.println("-----+-----+-----");
        System.out.println("  4  |  5  |  6  ");
        System.out.println("-----+-----+-----");
        System.out.println("  7  |  8  |  9  "+ "\n");
    }

    public void printGrid() {
        gridWithNumbers();
        System.out.println();
        System.out.println("  " + cell1 + "  |  " + cell2 + "  |  " + cell3 + "  ");
        System.out.println("-----+-----+-----");
        System.out.println("  " + cell4 + "  |  " + cell5 + "  |  " + cell6 + "  ");
        System.out.println("-----+-----+-----");
        System.out.println("  " + cell7 + "  |  " + cell8 + "  |  " + cell9 + "  " + "\n");

    }

    public void rest() throws IOException, InterruptedException {
        clearScreen();
        gameName();
        firstVsSecond();
        printGrid();
    }



    public void StartOrInfoOrExit() throws InterruptedException, IOException {
        clearScreen();
        gameName();
        System.out.print("Please, write (start) or (info) or (exit): ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        if (input.equalsIgnoreCase("info")) {
            info();
            startOrExitFromInfo();
        } else if (input.equalsIgnoreCase("start")) {
            start();
        } else if (input.equalsIgnoreCase("exit")) {
            exitFromTheWholeProject();
        } else {
            invalid("input");
            clearScreen();
            gameName();
            StartOrInfoOrExit();
        }
    }

    public void start() throws IOException, InterruptedException {
        clearScreen();
        gameName();
        System.out.print("[start]\n");

        firstPlayerInputName();
        secondPlayerInputName();

        System.out.println();
        rest();
        firstPlayerInput();
    }

    public void info() {
        System.out.println();
        System.out.println("..................................................................................................................\n" +
                "1- Tic-tac-toe or Xs and Os is a game for two players.\n" +
                "\n" +
                "2- Players turn to mark X or O, in a 3*3 grid (9 cells). \n" +
                "\n" +
                "3- The game board like the following examples, the player of X or O has to enter the cell number to fill it.\n");
        System.out.println("  1  |  2  |  3  \t  O  |  X  |  O  ");
        System.out.println("-----+-----+-----\t-----+-----+-----");
        System.out.println("  4  |  5  |  6  \t  X  |  O  |     ");
        System.out.println("-----+-----+-----\t-----+-----+-----");
        System.out.println("  7  |  8  |  9  \t     |  X  |  O  " + "\n");
        System.out.println("                 \t [O] player Win.\n");


        System.out.println("4- The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row is the winner.\n" +
                "....................................................................................................................");
        System.out.println();
    }

    public void startOrExitFromInfo() throws IOException, InterruptedException {

        System.out.print("\nPlease, write (start) or (exit): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        if (input.equalsIgnoreCase("start")) {
            clearScreen();
            gameName();
            start();
        } else if (input.equalsIgnoreCase("exit")) {
            exitFromTheWholeProject();
        } else {
            invalid("input");
            clearScreen();
            gameName();
            System.out.print("[info]\n");
            info();
            startOrExitFromInfo();
        }
    }


    public void firstPlayerInputName() throws InterruptedException, IOException {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("player [X] first name : ");
        firstPlayerName = scanner.nextLine().trim();

        if (firstPlayerName.length() > 20) {
            invalid("length or input");
            clearScreen();
            gameName();
            System.out.print("[start]\n");
            firstPlayerInputName();
        }
        while (!firstPlayerName.matches("[a-zA-z]+")) {
            invalid("name");
            clearScreen();
            gameName();
            System.out.print("[start]\n");
            firstPlayerInputName();
        }
    }

    public void secondPlayerInputName() throws IOException, InterruptedException {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("player [O] first name : ");
        secondPlayerName = scanner.nextLine().trim();

        if (secondPlayerName.length() > 20) {
            invalid("length or input");
            clearScreen();
            gameName();
            System.out.print("[start]\n\n");
            System.out.print("player [X] first name : " + firstPlayerName + "\n");
            secondPlayerInputName();
        }
        while (!secondPlayerName.matches("[a-zA-z]+")) {
            invalid("name");
            clearScreen();
            gameName();
            System.out.print("[start]\n\n");
            System.out.print("player [X] first name : " + firstPlayerName + "\n");
            secondPlayerInputName();
        }

    }


    public void firstPlayerInput() throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.print("[Enter a valid number from 1 to 9]\n\n");
        System.out.print(firstPlayerName.toUpperCase() + " [X] : ");
        String stringXNumber = scanner.next();

        int xNumber;

        if (stringXNumber.matches("[1-9]")) {
            xNumber = Integer.parseInt(stringXNumber);

            if (inputCellArray[xNumber - 1] == 0) {
                inputCellArray[xNumber - 1] = xNumber;
                cellsCases(xNumber, "X");
                rest();

                if (xWin()) {
                    rest();
                    System.out.println(firstPlayerName.toUpperCase() + " [X] Win!");
                    againOrExit();
                } else if (tie() == 9) {
                    System.out.println("Tie!");
                    againOrExit();
                }else {
                    secondPlayerInput();
                }

            } else {
                cellTaken();
                rest();
                firstPlayerInput();
            }
        } else {
            invalid("number");
            rest();
            firstPlayerInput();
        }

    }


    public void secondPlayerInput() throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.print("[Enter a valid number from 1 to 9]\n\n");
        System.out.print(secondPlayerName.toUpperCase() + " [O] : ");

        String stringONumber = scanner.next();
        int oNumber;

        if (stringONumber.matches("[1-9]")) {
            oNumber = Integer.parseInt(stringONumber);
            if (inputCellArray[oNumber - 1] == 0) {
                inputCellArray[oNumber - 1] = oNumber;
                cellsCases(oNumber, "O");
                rest();

                if (oWin()) {
                    rest();
                    System.out.println(secondPlayerName.toUpperCase() + " [O] Win!");
                    againOrExit();
                } else if (tie() == 9) {
                    System.out.println("Tie!");
                    againOrExit();
                } else {
                    firstPlayerInput();
                }

            } else {
                cellTaken();
                rest();
                secondPlayerInput();
            }
        } else {
            invalid("number");
            rest();
            secondPlayerInput();
        }
    }


    public void againOrExit() throws InterruptedException, IOException {
        System.out.println();
        System.out.print("Please, write (again) or (exit): ");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.next();
        if (input.equalsIgnoreCase("again")) {
            cell1 = " "; cell2 = " "; cell3 = " ";
            cell4 = " "; cell5 = " "; cell6 = " ";
            cell7 = " "; cell8 = " "; cell9 = " ";
            fillArray();
            clearScreen();
            gameName();
            StartOrInfoOrExit();
        } else if (input.equalsIgnoreCase("exit")) {
            exitFromTheWholeProject();
        } else {
            invalid("input");
            rest();
            if (xWin()) {
                System.out.println(firstPlayerName.toUpperCase() + " [X] Win!");
            } else if (oWin()) {
                System.out.println(secondPlayerName.toUpperCase() + " [O] Win!");
            } else {
                System.out.println("Tie!");
            }
            againOrExit();
        }
    }

    public void cellTaken() throws InterruptedException {
        System.out.println();
        System.out.print("Already taken! .");
        Thread.sleep(500);
        System.out.print(" .");
        Thread.sleep(500);
        System.out.print(" .");
        Thread.sleep(1000);
    }


    public void invalid(String issue) throws InterruptedException {
        System.out.println();
        System.out.printf("Invalid, [%s]! .",issue);
        Thread.sleep(500);
        System.out.print(" .");
        Thread.sleep(500);
        System.out.print(" .");
        Thread.sleep(800);
    }



    public void cellsCases(int cell, String xOrO) {
        inputCellArray[cell - 1] = cell;
        switch (cell) {
            case 1: cell1 = xOrO; break;
            case 2: cell2 = xOrO; break;
            case 3: cell3 = xOrO; break;
            case 4: cell4 = xOrO; break;
            case 5: cell5 = xOrO; break;
            case 6: cell6 = xOrO; break;
            case 7: cell7 = xOrO; break;
            case 8: cell8 = xOrO; break;
            case 9: cell9 = xOrO; break;
            default:
                System.out.println("Error!");
                exitFromTheWholeProject();
                break;
        }
    }


    public boolean xWin() {
        return cell1.equals("X") && cell2.equals("X") && cell3.equals("X") ||
                cell4.equals("X") && cell5.equals("X") && cell6.equals("X") ||
                cell7.equals("X") && cell8.equals("X") && cell9.equals("X") ||
                cell1.equals("X") && cell4.equals("X") && cell7.equals("X") ||
                cell2.equals("X") && cell5.equals("X") && cell8.equals("X") ||
                cell3.equals("X") && cell6.equals("X") && cell9.equals("X") ||
                cell1.equals("X") && cell5.equals("X") && cell9.equals("X") ||
                cell3.equals("X") && cell5.equals("X") && cell7.equals("X");
    }

    public boolean oWin() {
        return cell1.equals("O") && cell2.equals("O") && cell3.equals("O") ||
                cell4.equals("O") && cell5.equals("O") && cell6.equals("O") ||
                cell7.equals("O") && cell8.equals("O") && cell9.equals("O") ||
                cell1.equals("O") && cell4.equals("O") && cell7.equals("O") ||
                cell2.equals("O") && cell5.equals("O") && cell8.equals("O") ||
                cell3.equals("O") && cell6.equals("O") && cell9.equals("O") ||
                cell1.equals("O") && cell5.equals("O") && cell9.equals("O") ||
                cell3.equals("O") && cell5.equals("O") && cell7.equals("O");
    }

    public int tie() {
        int tie = 0;
        for (int value : inputCellArray) {
            if (value != 0) {
                tie++;
            }
        }
        return tie;
    }

}




