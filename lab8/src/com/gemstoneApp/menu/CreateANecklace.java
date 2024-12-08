package com.gemstoneApp.menu;

import com.gemstoneApp.necklace.Necklace;
import com.gemstoneApp.necklace.NecklaceFactory;

import java.util.Scanner;

public class CreateANecklace implements Executable {
    private Menu menu;

    public CreateANecklace(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        // Check if a necklace already exists
        if (menu.getNecklace() != null) {
            System.out.println("A necklace already exists. Please remove it or modify it before creating a new one.");
            return;
        }

        Scanner scanner = menu.getScanner();

        // Prompt user for how they want to create the necklace
        System.out.println("Choose how to create a necklace:");
        System.out.println("1. Input necklace manually");
        System.out.println("2. Import necklace from a file");
        System.out.println("3. Pick random stones");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            Necklace newNecklace = null;
            NecklaceFactory necklaceFactory = new NecklaceFactory();

            switch (choice) {
                case 1:
                    // Create a new necklace manually (using NecklaceFactory)
                    newNecklace = necklaceFactory.makeNecklace(); // Assuming this creates a new empty necklace
                    System.out.println("Necklace created successfully!");
                    break;

                case 2:
                    // Import necklace from a file (using NecklaceFactory)
                    newNecklace = necklaceFactory.importNecklace(); // Assuming this imports a necklace
                    break;

                case 3:
                    if (menu.getGems().isEmpty()) {
                        System.out.println("No gemstones available to pick from. Please add gemstones first.");
                        return;
                    }
                    int n = getValidInteger(scanner, "Enter the number of stones to pick:", 1, 100000);
                    newNecklace = necklaceFactory.pickStones(menu.getGems(), n);
                    System.out.println("Succesfully created a necklace with " + n + " stones!");
                    break;

                default:
                    System.out.println("Invalid choice. Returning to menu.");
                    return;
            }

            // Set the created or imported necklace to the menu
            menu.setNecklace(newNecklace);

            // Optionally, add gemstones to the newly created necklace
            if (choice == 1) {
                new AddGemstonesToNecklace(menu).execute(); // Reuse the add gemstones logic
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
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
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    @Override
    public String getDescription() {
        return "Create a new necklace (manually or from a file)";
    }
}
