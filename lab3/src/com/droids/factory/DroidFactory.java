package com.droids.factory;

import com.droids.droid.BattleDroid;
import com.droids.droid.Droid;
import com.droids.droid.HealerDroid;

import java.util.Scanner;

public class DroidFactory {
    public static Droid createDroid() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose Droid type (1 for Standard, 2 for Healer): ");
        int type = Integer.parseInt(scanner.nextLine());

        String name;
        int health, damage, healingPower;

        System.out.print("Enter Droid name: ");
        name = scanner.nextLine();

        System.out.print("Enter health (must be positive): ");
        health = Integer.parseInt(scanner.nextLine());
        if (health <= 0) {
            System.out.println("Invalid health. Droid creation failed.");
            return null; // Invalid health, return null
        }

        System.out.print("Enter damage (must be positive): ");
        damage = Integer.parseInt(scanner.nextLine());
        if (damage < 0) {
            System.out.println("Invalid damage. Droid creation failed.");
            return null; // Invalid damage, return null
        }

        switch (type) {
            case 1:
                return new BattleDroid(name, health, damage);
            case 2:
                System.out.print("Enter healing power (must be positive): ");
                healingPower = Integer.parseInt(scanner.nextLine());
                if (healingPower <= 0) {
                    System.out.println("Invalid healing power. Droid creation failed.");
                    return null; // Invalid healing power, return null
                }
                return new HealerDroid(name, health, damage, healingPower, 20);
            default:
                System.out.println("Invalid Droid type selected.");
                return null;
        }
    }
}
