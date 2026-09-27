


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        List<Rental> rentals = new ArrayList<>();

        try (Scanner sc = new Scanner(new File("rentals.txt") )) {
            while (sc.hasNext()) {
                String type = sc.next();
                String id = sc.next();
                int days = sc.nextInt();

                if (type.equalsIgnoreCase("PROJECTOR")) {
                    rentals.add(new ProjectorRental(id, days));
                } else if (type.equalsIgnoreCase("LAPTOP")) {
                    rentals.add(new LaptopRental(id, days));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("rentals.txt tidak ditemukan.");
        }

        for (Rental rental : rentals) {
            System.out.println(rental);
        }
    
    }
}
