package com.gemstoneApp.menu;

import com.gemstoneApp.necklace.Necklace;
import com.gemstoneApp.gemstone.Gemstone;

public class PrintNecklaceData implements Executable {
    private Menu menu;

    public PrintNecklaceData(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        // Get the current necklace from the menu
        Necklace necklace = menu.getNecklace();

        if (necklace == null) {
            System.out.println("No necklace exists. Create a necklace first to view its details.");
            return;
        }

        System.out.println("=== Necklace Details ===");
        System.out.println("Total Weight: " + necklace.calculateWeight() + " carats");
        System.out.println("Total Price: $" + necklace.calculatePrice());

        // Print details of each gemstone in the necklace
        System.out.println("\nGemstones in Necklace:");
        if (necklace.getGemstones().isEmpty()) {
            System.out.println("No gemstones have been added to the necklace yet.");
        } else {
            for (Gemstone gemstone : necklace.getGemstones()) {
                System.out.println(gemstone);
            }
        }
    }

    @Override
    public String getDescription() {
        return "Print detailed information about a necklace";
    }
}
