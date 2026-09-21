package lw01.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List <PrintJob> jobs = new ArrayList<>();

        try (Scanner sc = new Scanner(new File("jobs.txt"))) {
            while (sc.hasNext()) {
                String type = sc.next();
                String id = sc.next();
                int pages = sc.nextInt();

                if (type.equalsIgnoreCase("MONO")) {
                    jobs.add(new Monoprint(id, pages));
                }else if (type.equalsIgnoreCase("COLOUR")) {
                    jobs.add(new ColourPrint(id, pages));
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("jobs.txt tidak ditemukan.");
            return;
        }

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}