package Others;
/*
import java.util.*;

public class Time {
    static String menu = "[0] – exit\n" +
            "[1] – print current string\n" +
            "[2] – append the string\n" +
            "[3] – insert the string to the current\n" +
            "[4] – reverse current string\n" +
            "[5] – delete substring from the current string\n" +
            "[6] – replace substring in the current string";

    static Scanner scanner = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    static void append() {
        System.out.println("Input string to append: ");
        sb.append(scanner.next());
    }

    static void insert() {
        System.out.println("Input the integer of offset: ");
        int offset = scanner.nextInt();
        System.out.println("Input string to insert: ");
        sb.insert(offset, scanner.next());
    }

    static void delete() {
        System.out.println("Input the integer of start index: ");
        int start = scanner.nextInt();
        System.out.println("Input the integer of end index: ");
        int finish = scanner.nextInt();
        sb.delete(start, finish);
    }

    static void replace() {
        System.out.println("Input the integer of start index: ");
        int start = scanner.nextInt();
        System.out.println("Input the integer of end index: ");
        int finish = scanner.nextInt();
        System.out.println("Input string to replacing: ");
        sb.replace(start, finish, scanner.next());
    }

    public static void main(String[] args) {
        int choose;
        do {
            System.out.println(menu);
            choose = scanner.nextInt();

            switch (choose) {
                case 0:
                    break;
                case 1:
                    System.out.println(sb);
                    break;
                case 2:
                    append();
                    break;
                case 3:
                    insert();
                    break;
                case 4:
                    sb.reverse();
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    replace();
                    break;
                default:
                    System.out.printf(
                            "Unexpected input: %d.%nUse number between 0 and 6.%n", choose);
                    break;
            }
        } while(choose != 0);
    }
}*/

import java.util.Scanner;

public class Others {
    static Scanner scanner = new Scanner(System.in);
    private static int chosenNumber;
    private static int rows;
    private static int columns;
    public static String menu = "1. Show the seats\n" +
            "2. Buy a ticket\n" +
            "3. Statistics\n" +
            "0. Exit";
    private static int purchasedTickets = 0; // statistics
    private static float percentageTickets = 0f;
    private static int currentIncome = 0;
    private static int totalIncome = 0;
    private static boolean bought = false;


    public static void main(String[] args) {
        input();
        int[][] seats = new int[rows][columns];

        do {
            System.out.println(menu);
            chosenNumber = scanner.nextInt();

            switch (chosenNumber) {
                case 0:
                    return;
                case 1:
                    showTheSeats(seats);
                    break;
                case 2:
                    buyTicket(seats);
                    break;
                case 3:
                    showStatistics();
                    break;
                default:
                    System.err.println("Invalid menu number");
            }
        } while (chosenNumber != 0);
    }

    public static void input() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        columns = scanner.nextInt();
        totalCount();
    }

    public static void showTheSeats(int[][] seats) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 0; i < columns; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < columns; j++) {
                if (seats[i][j] == 0)
                    System.out.print(" S");
                else
                    System.out.print(" B");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void showStatistics() {
        float percentage = (float) purchasedTickets / (rows * columns) * 100;
        System.out.printf("Number of purchased tickets: %d\nPercentage: %.2f%%\n", purchasedTickets, percentage);
        System.out.printf("Current income: $%d\nTotal income: $%d\n", currentIncome, totalIncome);
        System.out.println();
    }

    public static void totalCount() {
        if (rows * columns <= 60) {
            totalIncome += rows * columns * 10;
        } else {
            totalIncome += columns * (rows / 2) * 10;
            totalIncome += columns * ((rows + 1) / 2) * 8;
        }
    }

    public static void buyTicket(int[][] seats) {
        do {
            System.out.println("Enter a row number:");
            int chosen_Seat_Row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int chosen_Seat_column = scanner.nextInt();
            bought = false;

            if ((chosen_Seat_Row > rows) || (chosen_Seat_Row < 1) || (chosen_Seat_column > columns) || (chosen_Seat_Row < 1)) {
                System.out.println("Wrong input!\n");
                continue;
            }
            if (seats[chosen_Seat_Row - 1][chosen_Seat_column - 1] == 1) {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
            } else {
                if (rows * columns <= 60) {
                    System.out.println("Ticket price: $" + 10);
                    currentIncome += 10;
                } else {
                    if (chosen_Seat_Row <= rows / 2) {
                        System.out.println("Ticket price: $" + 10);
                        currentIncome += 10;
                    } else {
                        System.out.println("Ticket price: $" + 8);
                        currentIncome += 8;
                    }
                }
                System.out.println();
                seats[chosen_Seat_Row - 1][chosen_Seat_column - 1] = 1;
                purchasedTickets += 1;
                bought = true;
            }
        } while (!bought);
    }
}

/* a strange /* // /* single-line comment */