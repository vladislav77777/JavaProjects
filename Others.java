/*
This code implements the simplest model for choosing seats in a movie theater
*/
package Others;
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
        int[][] seats = new int[rows][columns]; // our cinema hall

        do {
            System.out.println(menu);
            chosenNumber = scanner.nextInt();

            switch (chosenNumber) {           // we print our menu and the consumer chooses the option he needs 
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

    public static void input() { // first part
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        columns = scanner.nextInt();
        totalCount();
    }

    public static void showTheSeats(int[][] seats) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 0; i < columns; i++) {          // first row 1 2 3 4 ...
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {            // left column 1 2 3 4 ...
            System.out.print(i + 1);
            for (int j = 0; j < columns; j++) {
                if (seats[i][j] == 0)               // S - free
                    System.out.print(" S");
                else
                    System.out.print(" B");         // B - seat is taken
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void showStatistics() {                                      // useful statistics for the cinema owner
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
                    currentIncome += 10; // count our income
                } else {
                    if (chosen_Seat_Row <= rows / 2) {
                        System.out.println("Ticket price: $" + 10);
                        currentIncome += 10;                               // count our income
                    } else {
                        System.out.println("Ticket price: $" + 8);
                        currentIncome += 8; // count our income
                    }
                }
                System.out.println();
                seats[chosen_Seat_Row - 1][chosen_Seat_column - 1] = 1;    // now seat is taken
                purchasedTickets += 1;                                     // we bought a ticket
                bought = true;
            }
        } while (!bought);
    }
}

/* a strange /* // /* single-line comment */
