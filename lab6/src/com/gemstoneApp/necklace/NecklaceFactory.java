package com.gemstoneApp.necklace;

import com.gemstoneApp.gemstone.Gemstone;
import com.gemstoneApp.menu.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class NecklaceFactory {
    private Random random;
    public Necklace makeNecklace() {
        // Implement necklace creation logic
        return new Necklace();
    }

    public Necklace importNecklace() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("necklace.dat"))) {
            // Read the necklace object from the file
            Necklace necklace = (Necklace) ois.readObject();
            System.out.println("Necklace imported successfully.");
            Menu.logger.log(Level.INFO, "Successfully imported necklace from file.");
            return necklace;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to import necklace: " + e.getMessage());
            Menu.logger.log(Level.WARNING, "Failed importing necklace from file.");
            return null;
        }
    }

    public void exportNecklace(Necklace necklace) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("necklace.dat"))) {
            oos.writeObject(necklace); // Serialize the necklace object to the file
            Menu.logger.log(Level.INFO, "Successfully saved necklace to file.");
            System.out.println("Necklace exported successfully.");
        } catch (IOException e) {
            Menu.logger.log(Level.WARNING, "Failed to save necklace to file.");
            System.out.println("Error exporting necklace: " + e.getMessage());
        }
    }

    public Necklace pickStones(List<Gemstone> gemstones, int n) {
        if (gemstones == null || gemstones.isEmpty()) {
            System.out.println("No gemstones available to pick from.");
            return null;
        }

        random = new Random();

        List<Gemstone> pickedStones = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int index = random.nextInt(gemstones.size()); // Pick a random index
            pickedStones.add(gemstones.get(index));
        }

        Menu.logger.log(Level.INFO, "Successfully selected " + pickedStones.size() + " gemstones for a necklace.");

        return new Necklace(pickedStones);
    }
}
