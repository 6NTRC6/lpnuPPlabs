package com.gemstoneApp.menu;

import com.gemstoneApp.gemstone.Gemstone;

import java.util.Scanner;

public class PrintGemstoneTransparencyRange implements Executable {
    private Menu menu;

    public PrintGemstoneTransparencyRange(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        // Get the scanner from the Menu class
        Scanner scanner = menu.getScanner();

        // Check if the gems list is empty
        if (menu.getGems().isEmpty()) {
            System.out.println("The gemstone list is empty. Nothing to display.");
            return;
        }

        // Input for transparency range (A and B)
        float A = -1, B = -1;

        // Input for lower bound A
        while (A < 0 || A > 1) {
            System.out.print("Enter the transparency lower bound A (0 <= A <= 1): ");
            try {
                String input = scanner.nextLine().trim().replace(',', '.'); // Handle comma input
                A = Float.parseFloat(input); // Try to parse as float
                if (A < 0 || A > 1) {
                    System.out.println("Invalid input. Transparency value A must be in the range 0 to 1.");
                    A = -1; // Reset if invalid
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for transparency.");
            }
        }

        // Input for upper bound B
        while (B < 0 || B > 1) {
            System.out.print("Enter the transparency upper bound B (0 <= B <= 1): ");
            try {
                String input = scanner.nextLine().trim().replace(',', '.'); // Handle comma input
                B = Float.parseFloat(input); // Try to parse as float
                if (B < 0 || B > 1) {
                    System.out.println("Invalid input. Transparency value B must be in the range 0 to 1.");
                    B = -1; // Reset if invalid
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for transparency.");
            }
        }

        // Normalize the range: ensure A <= B
        if (A > B) {
            float temp = A;
            A = B;
            B = temp;
        }

        // Print gemstones in the specified transparency range
        System.out.println("Gemstones with transparency in the range [" + A + ", " + B + "]:");

        boolean found = false;
        for (Gemstone gem : menu.getGems()) {
            if (gem.getTransparency() >= A && gem.getTransparency() <= B) {
                System.out.printf("- %s (Transparency: %.2f)%n", gem, gem.getTransparency());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No gemstones found in the specified transparency range.");
        }
    }

    @Override
    public String getDescription() {
        return "Print gemstones in transparency range";
    }
}
