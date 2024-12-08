package com.gemstoneApp.menu;

import com.gemstoneApp.gemstone.Diamond;
import com.gemstoneApp.gemstone.Gemstone;
import com.gemstoneApp.gemstone.Opal;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class GemstoneAddingChoice implements Executable {
    private Menu menu;

    public GemstoneAddingChoice(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        Scanner scanner = menu.getScanner();
        System.out.println("Choose how to add a gemstone:");
        System.out.println("1. Input gemstone manually");
        System.out.println("2. Load gemstones from a file");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Gemstone gemstone = inputGemstone(scanner);
                    menu.getGems().add(gemstone);
                    System.out.println("Gemstone added successfully!");
                    break;
                case 2:
                    loadGemstonesFromFile();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    @Override
    public String getDescription() {
        return "Add gemstones (manually or from a file)";
    }

    private Gemstone inputGemstone(Scanner scanner) {
        System.out.println("Select the type of gemstone:");
        System.out.println("1. Basic Gemstone");
        System.out.println("2. Diamond");
        System.out.println("3. Opal");

        int choice = getValidInteger(scanner, "Enter your choice (1-3):", 1, 3);

        // Input gemstone details with validation
        String name = getValidString(scanner, "Enter gemstone name (non-empty):");
        Float weight = getValidFloat(scanner, "Enter gemstone weight (positive number):", 0.1f, 10000.0f);
        Float basePrice = getValidFloat(scanner, "Enter gemstone base price (positive number):", 0.1f, 10000.0f);
        Float transparency = getValidFloat(scanner, "Enter gemstone transparency (0.0 to 1.0):", 0.0f, 1.0f);

        // Return the appropriate gemstone type
        return switch (choice) {
            case 1 -> {
                Menu.logger.log(Level.INFO, "Created new basic gemstone: " + name + ", weight=" + weight + ", basePrice=" + basePrice + ", transparency=" + transparency);
                yield new Gemstone(name, weight, basePrice, transparency); // Basic Gemstone
            }
            case 2 -> {
                Float hardness = getValidFloat(scanner, "Enter diamond hardness (positive number):", 0.1f, 100.0f);
                Menu.logger.log(Level.INFO, "Created new diamond: " + name + ", weight=" + weight + ", basePrice=" + basePrice + ", transparency=" + transparency + ", hardness=" + hardness);
                yield new Diamond(name, weight, basePrice, transparency, hardness); // Diamond
            }
            case 3 -> {
                Float waterContent = getValidFloat(scanner, "Enter opal water content (0.0 to 100.0):", 0.0f, 100.0f);
                Menu.logger.log(Level.INFO, "Created new opal: " + name + ", weight=" + weight + ", basePrice=" + basePrice + ", transparency=" + transparency + ", waterContent=" + waterContent);
                yield new Opal(name, weight, basePrice, transparency, waterContent); // Opal
            }
            default -> {
                System.out.println("Invalid choice. Returning to menu.");
                yield null;
            }
        };
    }

    private void loadGemstonesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gemstones.dat"))) {
            // Read the list of gemstones from the file
            List<Gemstone> gemstones = (List<Gemstone>) ois.readObject();
            menu.getGems().addAll(gemstones);
            System.out.println("Gemstones imported successfully.");
            Menu.logger.log(Level.INFO, "Loaded gemstones from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to import gemstones: " + e.getMessage());
        }
    }

    private int getValidInteger(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.println(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("Please enter a number between %d and %d.\n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    private float getValidFloat(Scanner scanner, String prompt, float min, float max) {
        while (true) {
            System.out.println(prompt);
            try {
                float value = Float.parseFloat(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("Please enter a number between %.2f and %.2f.\n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private String getValidString(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
    }
}