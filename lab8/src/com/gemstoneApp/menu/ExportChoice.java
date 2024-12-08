package com.gemstoneApp.menu;

import com.gemstoneApp.gemstone.Gemstone;
import com.gemstoneApp.necklace.Necklace;
import com.gemstoneApp.necklace.NecklaceFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class ExportChoice implements Executable {
    private Menu menu;

    public ExportChoice(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        Scanner scanner = menu.getScanner();
        System.out.println("Choose what to export:");
        System.out.println("1. Export Necklace");
        System.out.println("2. Export Gemstones");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Export Necklace
                    exportNecklace();
                    break;
                case 2:
                    // Export Gemstones
                    exportGemstones();
                    break;
                default:
                    System.out.println("Invalid choice. Returning to menu.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    @Override
    public String getDescription() {
        return "Save gemstones or necklace into file";
    }

    private void exportNecklace() {
        Necklace necklace = menu.getNecklace();
        NecklaceFactory necklaceFactory = new NecklaceFactory();
        if (necklace != null) {
            necklaceFactory.exportNecklace(necklace);
        } else {
            System.out.println("No necklace to export.");
        }
    }

    private void exportGemstones() {
        List<Gemstone> gemstones = menu.getGems();
        if (gemstones.isEmpty()) {
            System.out.println("No gemstones available to export.");
            return;
        }

        Scanner scanner = menu.getScanner();
        String exportAll;
        // Loop until we get a valid 'y' or 'n' response
        while (true) {
            System.out.println("Do you want to export all gemstones? (y/n): ");
            exportAll = scanner.nextLine().trim().toLowerCase();

            if (exportAll.equals("y") || exportAll.equals("n")) {
                break; // Exit loop if valid input is given
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }

        if (exportAll.equals("y")) {
            exportAllGemstones(gemstones);
        } else {
            exportSelectedGemstones(gemstones);
        }
    }


    // Method to export all gemstones
    private void exportAllGemstones(List<Gemstone> gemstones) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gemstones.dat"))) {
            oos.writeObject(gemstones);
            System.out.println("All gemstones exported successfully to 'gemstones.dat'.");
            Menu.logger.log(Level.INFO, "Successfully saved all gemstones to file.");
        } catch (IOException e) {
            System.out.println("Failed to export gemstones: " + e.getMessage());
            Menu.logger.log(Level.WARNING, "Failed to save gemstones to file.");
        }
    }

    // Method to export selected gemstones
    private void exportSelectedGemstones(List<Gemstone> gemstones) {
        Scanner scanner = menu.getScanner();

        // Display gemstones to the user with 1-based index
        System.out.println("Select gemstones to export (1-based index):");
        for (int i = 0; i < gemstones.size(); i++) {
            System.out.println((i + 1) + ". " + gemstones.get(i).getName());
        }

        // Ask the user for their selection
        System.out.println("Enter the numbers of gemstones to export (separated by spaces): ");
        String input = scanner.nextLine().trim();

        String[] indices = input.split("\\s+");
        try {
            List<Gemstone> selectedGemstones = new ArrayList<>();
            for (String index : indices) {
                int gemIndex = Integer.parseInt(index) - 1;  // Convert to 0-based index
                if (gemIndex >= 0 && gemIndex < gemstones.size()) {
                    selectedGemstones.add(gemstones.get(gemIndex));
                    System.out.println("Gemstone " + gemstones.get(gemIndex).getName() + " added for export.");
                } else {
                    System.out.println("Invalid gemstone index: " + gemIndex + 1);  // Show 1-based index
                }
            }

            if (!selectedGemstones.isEmpty()) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gemstones.dat"))) {
                    oos.writeObject(selectedGemstones);
                    System.out.println("Selected gemstones exported successfully to 'gemstones.dat'.");
                    Menu.logger.log(Level.INFO, "Successfully saved " + selectedGemstones.size() + " gemstones to file.");
                } catch (IOException e) {
                    Menu.logger.log(Level.WARNING, "Failed to save gemstones to file.");
                    System.out.println("Failed to export gemstones: " + e.getMessage());
                }
            } else {
                Menu.logger.log(Level.WARNING, "Tried to select gemstones for saving, but none valid were found.");
                System.out.println("No valid gemstones selected for export.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid gemstone indices.");
        }
    }
}