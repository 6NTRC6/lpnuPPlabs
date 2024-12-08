package com.gemstoneApp.menu;

import com.gemstoneApp.gemstone.Gemstone;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintGemstoneData implements Executable {
    private Menu menu;

    public PrintGemstoneData(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        ArrayList<Gemstone> gems = menu.getGems();

        if (gems.isEmpty()) {
            System.out.println("No gemstones are available. Add some gemstones to the list first.");
            return;
        }

        System.out.println("Available Gemstones:");
        for (int i = 0; i < gems.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, gems.get(i).getName());
        }

        Scanner scanner = menu.getScanner();
        System.out.print("Enter the number of the gemstone you want to view details for: ");
        String input = scanner.nextLine().trim();

        try {
            int index = Integer.parseInt(input) - 1;

            if (index < 0 || index >= gems.size()) {
                System.out.println("Invalid gemstone number. Please select a number from the list.");
                return;
            }

            Gemstone selectedGemstone = gems.get(index);
            System.out.println("Gemstone Details:");
            System.out.printf("- Name: %s%n", selectedGemstone.getName());
            System.out.printf("- Weight: %.2f%n", selectedGemstone.getWeight());
            System.out.printf("- Base Price: %.2f%n", selectedGemstone.getBasePrice());
            System.out.printf("- Transparency: %.2f%n", selectedGemstone.getTransparency());
            System.out.printf("- Cut Ratio: %.2f%n", selectedGemstone.getCutRatio());
            System.out.printf("- Price: %.2f%n", selectedGemstone.calculatePrice());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number corresponding to a gemstone.");
        }
    }

    @Override
    public String getDescription() {
        return "Print detailed information about a gemstone";
    }
}
