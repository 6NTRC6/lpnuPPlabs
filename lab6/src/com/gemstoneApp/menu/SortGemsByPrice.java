package com.gemstoneApp.menu;

import com.gemstoneApp.gemstone.Gemstone;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;

public class SortGemsByPrice implements Executable {
    private Menu menu;

    public SortGemsByPrice(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        // Check if the list of gemstones is empty
        if (menu.getGems().isEmpty()) {
            System.out.println("List is empty, nothing to sort yet.");
            return; // Exit the method if the list is empty
        }
        // Sorting the gems list by price using a comparator
        Collections.sort(menu.getGems(), (gem1, gem2) -> {
            // Compare by calculated price (using calculatePrice method)
            return Float.compare(gem1.calculatePrice(), gem2.calculatePrice());
        });

        // Print the sorted gems to verify the result
        System.out.println("Sorted gems by price:");
        for (Gemstone gem : menu.getGems()) {
            System.out.println(gem + " - Price: " + gem.calculatePrice());
        }
        Menu.logger.log(Level.INFO, "Gemstones were sorted by price.");
    }

    @Override
    public String getDescription() {
        return "Sort list of gemstones by price";
    }
}
