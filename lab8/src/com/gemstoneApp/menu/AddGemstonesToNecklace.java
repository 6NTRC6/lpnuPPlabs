package com.gemstoneApp.menu;

import com.gemstoneApp.gemstone.Gemstone;
import com.gemstoneApp.necklace.Necklace;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;

public class AddGemstonesToNecklace implements Executable {
    private Menu menu;

    public AddGemstonesToNecklace(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        Necklace necklace = menu.getNecklace();

        if (necklace == null) {
            System.out.println("No existing necklace found. Create a necklace first.");
            return;
        }

        if (menu.getGems().isEmpty()) {
            System.out.println("No gemstones are available to add. Add some gemstones to the list first.");
            return;
        }

        Scanner scanner = menu.getScanner();

        System.out.println("Available Gemstones:");
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

        System.out.println("Enter the numbers of gemstones to add to the necklace (separated by spaces): ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("No gemstones were added. Please enter valid gemstone numbers.");
            return;
        }

        String[] parts = input.split("\\s+");
        ArrayList<Gemstone> gemstonesToAdd = new ArrayList<>();
        boolean invalidInput = false;

        for (String part : parts) {
            try {
                int index = Integer.parseInt(part) - 1;
                if (index < 0 || index >= menu.getGems().size()) {
                    System.out.printf("Invalid gemstone number: %s. Skipping this input.%n", part);
                    invalidInput = true;
                } else {
                    gemstonesToAdd.add(menu.getGems().get(index));
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid input: %s. Please enter valid gemstone numbers.%n", part);
                invalidInput = true;
            }
        }

        if (gemstonesToAdd.isEmpty()) {
            System.out.println("No valid gemstones were added to the necklace.");
        } else {
            necklace.getGemstones().addAll(gemstonesToAdd);
            System.out.println("The following gemstones were added to the necklace:");
            for (Gemstone gemstone : gemstonesToAdd) {
                System.out.printf("- %s (Weight: %.2f, Base Price: %.2f)%n",
                        gemstone.getName(),
                        gemstone.getWeight(),
                        gemstone.getBasePrice());
            }
            Menu.logger.log(Level.INFO, gemstonesToAdd.size() + " gemstones were added to the necklace.");
        }

        if (invalidInput) {
            System.out.println("Note: Some inputs were invalid and skipped.");
        }
    }

    @Override
    public String getDescription() {
        return "Adds gemstones to the existing necklace";
    }
}
