package opgave4;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws InterruptedException {
        final int N = 10;

        Scanner scanner = new Scanner(System.in);
        CircularQueue sharedQueue = new CircularQueue(N);
        String[] testData = {"Lukas", "Mads", "Signe", "Sidsel", "Mikkel", "Thomas", "Lasse", "Nicolai", "Mathias",
                "Niklas", "Jay", "Patrick", "Levi", "Meika", "Maria", "Mar", "Torben", "Casper", "Martin"};

        CustomerRunnable custRunnable = new CustomerRunnable(sharedQueue);
        CashierRunnable cashRunnable = new CashierRunnable(sharedQueue);

        Thread custThread = new Thread(custRunnable, "Customer Thread");
        Thread cashThread = new Thread(cashRunnable, "Cashier Thread");

        custThread.start();
        cashThread.start();

        Sleep.sleepRand(500, 500);
        System.out.println("Welcome to the DMU Bakery!");
        System.out.println("Please enter your name to get a ticket.");
        System.out.println("You can also type 'test' to use test data.");
        System.out.println("You can always type 'exit' to leave.");
        System.out.println();

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("test")) {
                for (String name : testData) {
                    custRunnable.takeTicket(name);
                }
            } else if (input.equalsIgnoreCase("exit")) {
                break;
            } else if (input.isBlank()) {
                System.out.println("Please type in your name...");
            } else {
                if (sharedQueue.isFull()) {
                    System.out.println("The building is currently at it's max capacity. Please wait...");
                } else {
                    System.out.printf("Thank you %s, you have been added to the queue!%n", input);

                    custRunnable.takeTicket(input);
                }
            }
        }

        custRunnable.close();
        cashRunnable.close();
        System.exit(0);
    }
}
