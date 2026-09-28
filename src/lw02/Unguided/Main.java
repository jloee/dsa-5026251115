package lw02.Unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> borrowers = new LinkedList<>();
        LinkedList<String[]> titlesBooks = new LinkedList<>();

        try {
            File file = new File("borrowers.txt");
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (line.isEmpty()) continue;

                    String[] parts = line.split("\\s+");
                    if (parts.length < 2) continue;

                    String name = parts[0];
                    String bookTitle = parts[1];

                    borrowers.add(new String[]{name, bookTitle});

                    boolean found = false;
                    for (String[] title : titlesBooks) {
                        if (title[0].equals(bookTitle)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        titlesBooks.add(new String[]{bookTitle, "0"});
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File borrowers.txt not found.");
            return;
        }

        Queue<String[]> queue = new LinkedList<>(borrowers);
        Stack<String[]> failedBorrowings = new Stack<>();


        while (!queue.isEmpty()) {
            String[] borrowing = queue.poll();
            String bookTitle = borrowing[1];

            boolean processed = false;
            for (String[] title : titlesBooks) {
                if (title[0].equals(bookTitle)) {
                    int count = Integer.parseInt(title[1]);
                    if (count > 0) {
                        title[1] = String.valueOf(count - 1);
                        processed = true;
                        break;
                    }
                }
            }
            if (!processed) {
                failedBorrowings.push(borrowing);
            }
        }
        System.out.println("=== Successfully Processed Requests ===");
        for (String[] title: titlesBooks) {
            System.out.println("Book Title: " + title[0] + ", Remaining Copies: " + title[1]);
        }

        System.out.println("\n=== Failed Borrowing Requests ===");
        for (String[] borrowing : failedBorrowings) {
            System.out.println("Borrower: " + borrowing[0] + ", Book Title: " + borrowing[1]);
        }
    }       
}  
