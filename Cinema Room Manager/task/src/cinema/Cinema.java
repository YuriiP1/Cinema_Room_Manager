package cinema;

import java.util.Scanner;

public class Cinema {

    private static Scanner scanner = new Scanner(System.in);
    private static char[][] cinema;
    private static int currentIncome;
    private static int countTickets;
    private static int totalCurrent;
    private static int pickRow;
    public static void main(String[] args) {

        cinema();
        pick();
    }

    private static void cinema() {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        cinema = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j< seats; j++) {
                cinema[i][j] = 'S';
            }
        }
        System.out.println();
        menu();

    }

    private static void menu() {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");

    }

    private static void pick() {

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            if (n == 1) {
                System.out.println();
                printCinema();
                System.out.println();
                menu();
            } else if (n == 2) {
                System.out.println();
                pickSeatCinema();
                System.out.println();
                menu();
            } else if (n == 3) {
                System.out.println();
                statistics();
                System.out.println();
                menu();
            } else {
                return;
            }
        }
    }

    private static void printCinema() {
        System.out.println("Cinema: ");
        System.out.print("  ");
        for (int k = 1; k < cinema[0].length + 1; k++) {
            System.out.print(k + " ");
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            int n = i + 1;
            System.out.print(n + " ");
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void pickSeatCinema() {
        int r = getRow();
        pickRow = r;
        int s = getSeat();
        if (r > cinema[0].length || s > cinema.length) {
            System.out.println("\nWrong input!\n");
            pickSeatCinema();
        } else if (cinema[r - 1][s - 1] ==  'B') {
            System.out.println("\nThat ticket has already been purchased!\n");
            pickSeatCinema();
        } else {
            cinema[r - 1][s - 1] = 'B';
            countTickets++;
            getTicketPrice();
        }

    }

    private static void getTicketPrice() {
        System.out.print("\nTicket price: ");

        if(!isLarger()) {
            currentIncome = 10;
        } else {
            if (pickRow > cinema[0].length / 2) {
                currentIncome = 8;
            } else {
                currentIncome = 10;
            }
        }
        totalCurrent += currentIncome;
        System.out.println("$" + currentIncome);
    }

    private static int getSeat() {
        System.out.println("Enter a seat number in that row:");
        return scanner.nextInt();
    }

    private static int getRow() {
        System.out.println("Enter a row number:");
        return scanner.nextInt();
    }


    private static boolean isLarger() {
        return cinema[0].length * cinema.length > 60;
    }

    private static void statistics() {
        double per = countTickets / (double)(cinema[0].length * cinema.length) * 100;
        System.out.println("Number of purchased tickets: " + countTickets);
        System.out.printf("Percentage: %.2f%s\n", per ,"%");
        System.out.println("Current income: " + "$" + totalCurrent);
        System.out.println("Total income: " + totalIncome());
    }

    private static String totalIncome() {
        int rows = cinema[0].length;
        int seats = cinema.length;
        int total = 0;
        if (isLarger()) {
            int firstHalf = rows / 2;
            int secondHalf = (int) Math.ceil((double) rows / 2);

            total = (firstHalf * seats * 10) + (secondHalf * seats * 8);
        } else {
            total = rows * seats * 10;
        }

        return "$" + total;
    }
}