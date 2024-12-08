package com.gemstoneApp.menu;

import com.gemstoneApp.gemstone.Gemstone;

import java.util.Scanner;
import java.util.logging.Level;

public class GemstoneCutting implements Executable {
    private Menu menu;

    public GemstoneCutting(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        // Check if there are gemstones in the menu
        if (menu.getGems().isEmpty()) {
            System.out.println("No gemstones available to cut. Add gemstones first.");
            return;
        }

        // Show available gemstones to cut
        System.out.println("Available Gemstones to Cut:");
        for (int i = 0; i < menu.getGems().size(); i++) {
            Gemstone gemstone = menu.getGems().get(i);
            System.out.printf("%d. %s (Weight: %.2f, Base Price: %.2f, Transparency: %.2f, Cut Ratio: %.2f)%n",
                    i + 1,
                    gemstone.getName(),
                    gemstone.getWeight(),
                    gemstone.getBasePrice(),
                    gemstone.getTransparency(),
                    gemstone.getCutRatio());
        }

        // Ask for which gemstone to cut
        Scanner scanner = menu.getScanner();
        System.out.print("Enter the number of the gemstone to cut: ");
        int gemIndex = -1;

        // Handle invalid input for gem selection
        while (gemIndex < 0 || gemIndex >= menu.getGems().size()) {
            try {
                gemIndex = Integer.parseInt(scanner.nextLine().trim()) - 1; // Read line and adjust for 1-based index
                if (gemIndex < 0 || gemIndex >= menu.getGems().size()) {
                    System.out.println("Invalid gemstone number. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number: ");
            }
        }

        Gemstone gemstone = menu.getGems().get(gemIndex);

        // Check if the gemstone is already cut
        if (gemstone.getCutRatio() > 1.0) {
            System.out.println("This gemstone has already been cut.");
            return;
        }

        // Ask for the level of cut: slight, average, or super fine
        System.out.println("Choose cutting level: ");
        System.out.println("1. Slight (Cut Ratio: 1.1, reduces weight by 5%)");
        System.out.println("2. Average (Cut Ratio: 1.2-1.3, reduces weight by 10%)");
        System.out.println("3. Super Fine (Cut Ratio: 1.7-1.8, reduces weight by 20%)");

        int choice = -1;

        // Handle invalid input for cut choice
        while (choice < 1 || choice > 3) {
            System.out.print("Enter choice (1, 2, or 3): ");
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid cut choice. Please choose a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the cut choice.");
            }
        }

        float newCutRatio = 0;
        float weightReduction = 0;

        // Logic for different cut levels
        switch (choice) {
            case 1:
                newCutRatio = 1.1f;
                weightReduction = 0.05f;
                break;
            case 2:
                newCutRatio = 1.2f + (float) (Math.random() * 0.1); // Random between 1.2 and 1.3
                weightReduction = 0.1f;
                break;
            case 3:
                newCutRatio = 1.7f + (float) (Math.random() * 0.1); // Random between 1.7 and 1.8
                weightReduction = 0.2f;
                break;
        }
        newCutRatio = Math.round(newCutRatio * 100.0f) / 100.0f;

        // Calculate the new weight and price after cutting
        float originalWeight = gemstone.getWeight();
        float newWeight = Math.round(originalWeight * (1 - weightReduction) * 100.0f) / 100.0f;
        float originalPrice = gemstone.calculatePrice();
        gemstone.setCutRatio(newCutRatio);
        gemstone.setWeight(newWeight);
        float newPrice = gemstone.calculatePrice();

        // Check if the cutting is profitable
        if (newPrice > originalPrice) {
            System.out.println("The gemstone has been successfully cut!");
            System.out.printf("New Cut Ratio: %.2f, New Weight: %.2f, New Price: %.2f%n", newCutRatio, newWeight, newPrice);
            Menu.logger.log(Level.INFO, "A gemstone " + gemstone.getName() + "has been cut! Weight: " + originalWeight + " -> " + newWeight + ", price: " + originalPrice + " -> " + newPrice);
        } else {
            System.out.println("Cutting is not profitable. The price decrease due to weight loss outweighs the price increase.");
            Menu.logger.log(Level.INFO, "Tried to cut gemstone " + gemstone.getName() + ", but the action wasn't profitable.");
            // Restore the original values if not profitable
            gemstone.setCutRatio(1.0f);
            gemstone.setWeight(originalWeight);
        }
    }

    @Override
    public String getDescription() {
        return "Cut a gemstone";
    }
}
