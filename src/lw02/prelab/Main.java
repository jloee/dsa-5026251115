package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        try {
            File file = new File("transactions.txt");
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (line.isEmpty()) continue;

                    String[] parts = line.split("\\s+");
                    if (parts.length < 3) continue;

                    String name = parts[0];
                    String type = parts[1];
                    String amount = parts[2];

                    transactions.add(new String[]{name, type, amount});

                    boolean found = false;
                    for (String[] customer : customers) {
                        if (customer[0].equals(name)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        customers.add(new String[]{name, "0"});
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt not found.");
            return;
        }

        Queue<String[]> queue = new LinkedList<>(transactions);
        Stack<String[]> failedTransactions = new Stack<>();

        while (!queue.isEmpty()) {
            String[] tx = queue.poll();
            String name = tx[0];
            String type = tx[1];
            int amount = Integer.parseInt(tx[2]);

            boolean processed = false;
            for (String[] customer : customers) {
                if (customer[0].equals(name)) {
                    int balance = Integer.parseInt(customer[1]);

                    if (type.equals("deposit")) {
                        balance += amount;
                        customer[1] = String.valueOf(balance);
                    } else if (type.equals("withdraw")) {
                        if (amount > balance) {
                            failedTransactions.push(tx);
                        } else {
                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                        }
                    }
                    processed = true;
                    break;
                }
            }

            if (!processed) {
                failedTransactions.push(tx);
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + ": " + customer[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failedTx = failedTransactions.pop();
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }
}
