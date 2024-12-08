package com.gemstoneApp.menu;


import com.gemstoneApp.gemstone.Diamond;
import com.gemstoneApp.gemstone.Gemstone;
import com.gemstoneApp.gemstone.Opal;
import com.gemstoneApp.necklace.Necklace;
import com.gemstoneApp.utils.MailerSend;

import java.io.IOException;
import java.util.*;
import java.util.logging.*;


public class Menu {
    public static final Logger logger = Logger.getLogger(Menu.class.getName());
    private Scanner scanner;
    private ArrayList<Gemstone> gems;
    private Necklace necklace;
    private List<Executable> commands;

    static {
        try {
            // Set up a FileHandler to write to "logs.txt"
            FileHandler fileHandler = new FileHandler("logs.txt", true); // 'true' means append to file
            fileHandler.setFormatter(new SimpleFormatter()); // Optional: Set format for logs
            LogManager.getLogManager().reset();

            // Add the file handler to the logger
            logger.addHandler(fileHandler);


        } catch (IOException e) {
            // Handle the exception if file handler can't be created
            System.out.println("Failed to setup file handler for logger: " + e.getMessage());
            try {
                MailerSend.sendEmail("1093damaged@rowdydow.com", "Severe error occured!",
                        "Failed to set up file handler for a logger, please check your application!");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            System.exit(1);
        }
    }

    public Menu(Scanner scanner) {
        this.scanner = scanner;
        this.gems = new ArrayList<>();
        this.necklace = null;
        this.commands = new ArrayList<>();
        initializeCommands();
    }

    private void initializeCommands() {
        logger.log(Level.INFO, "Initializing commands...");
        commands.add(new GemstoneAddingChoice(this));
        commands.add(new CreateANecklace(this));
        commands.add(new AddGemstonesToNecklace(this));
        commands.add(new SortGemsByPrice(this));
        commands.add(new GemstoneCutting(this));
        commands.add(new PrintGemstoneTransparencyRange(this));
        commands.add(new PrintGemstoneList(this));
        commands.add(new PrintGemstoneData(this));
        commands.add(new PrintNecklaceData(this));
        commands.add(new ExportChoice(this));
    }

    public void printMenu() {
        boolean running = true;
        logger.log(Level.INFO, "Application started.");
        System.out.println("\nWelcome to Gem Stone App!");
        this.gems.add(new Gemstone("Gem1", 10.0f, 15.0f, 0.05f));
        this.gems.add(new Diamond("Gem2D", 12.0f, 25.0f, 0.1f, 10.0f));
        this.gems.add(new Opal("Gem3O", 15.0f, 35.0f, 0.25f, 10.0f));

        while (running) {
            System.out.println("=======================");

            // Print menu options using getDescription() from each command
            for (int i = 0; i < commands.size(); i++) {
                System.out.println((i + 1) + ". " + commands.get(i).getDescription());
            }

            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice > 0 && choice <= commands.size()) {
                    commands.get(choice - 1).execute();
                } else if (choice == 0) {
                    running = false;
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public ArrayList<Gemstone> getGems() {
        return gems;
    }

    public void addGem(Gemstone gem) {
        gems.add(gem);
    }

    public Necklace getNecklace() {
        return necklace;
    }

    public void setNecklace(Necklace necklace) {
        this.necklace = necklace;
    }

    public Scanner getScanner() {
        return scanner;
    }

}

