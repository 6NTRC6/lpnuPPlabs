package com.droids.battle;

import com.droids.droid.Droid;
import com.droids.team.Team;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Battle {
    private static final StringBuilder battleLog = new StringBuilder(); // Store battle log in memory

    public static int oneOnOne(Droid droid1, Droid droid2) {
        if (droid1 == droid2) {
            System.out.println("Droid can't fight itself.");
            return 3;
        }
        int result = 0;
        Battle.addToBattleLog("Battle started: " + droid1.getName() + " vs " + droid2.getName());
        Battle.addToBattleLog(droid1 + "\n vs \n" + droid2);
        battleLog.setLength(0); // Clear previous log

        while (droid1.isAlive() && droid2.isAlive()) {
            droid2.takeDamage(droid1.getDamage());
            battleLog.append(String.format("%s attacks %s! %s takes %d damage! Health left: %d\n",
                    droid1.getName(), droid2.getName(), droid2.getName(), droid1.getDamage(), droid2.getHealth()));

            if (!droid2.isAlive()) {
                battleLog.append(String.format("%s wins!\n", droid1.getName()));
                result = 2;
                break;
            }

            droid1.takeDamage(droid2.getDamage());
            battleLog.append(String.format("%s attacks %s! %s takes %d damage! Health left: %d\n",
                    droid2.getName(), droid1.getName(), droid1.getName(), droid2.getDamage(), droid1.getHealth()));

            if (!droid1.isAlive()) {
                battleLog.append(String.format("%s wins!\n", droid2.getName()));
                result = 1;
            }
        }

        // Print the battle log and prompt to save
        droid1.restoreStats();
        droid2.restoreStats();
        return result;
    }

    public static void teamVsTeam(Team team1, Team team2, Scanner scanner) {
        int battlesLeft = team1.getSize();
        if (team1 == team2) {
            System.out.println("Team can't fight itself.");
            return;
        }
        if (team1.getSize() != team2.getSize()) {
            System.out.println("Teams should have the same size.");
            return;
        }
        battleLog.setLength(0); // Clear previous log
        System.out.printf("Team battle: %s vs %s\n", team1.getName(), team2.getName());

        int [] wins = {0, 0, 0, 0};
        while (team1.isAlive() && team2.isAlive() && battlesLeft > 0) {
            oneOnOne(team1.getDroid(0), team2.getDroid(0));
            for (int i = team1.getSize() - 1; i >= 0; i--) {
                wins[oneOnOne(team1.getDroid(i), team2.getDroid(i))]++;
            }
            if (!team2.isAlive() || wins[1] > wins[2]) {
                battleLog.append(String.format("%s wins! Score: team 1 - %d wins, team 2 - %d wins\n", team1.getName(), wins[1], wins[2]));
                break;
            } else if (!team1.isAlive() || wins[2] > wins[1]) {
                battleLog.append(String.format("%s wins! Score: team 1 - %d wins, team 2 - %d wins\n", team2.getName(), wins[1], wins[2]));
            } else {
                battleLog.append("Tie!");
            }
            battlesLeft--;
        }

        // Print the battle log and prompt to save
        printAndPromptToSaveLog(scanner);
    }

    public static void addToBattleLog(String message) {
        battleLog.append(message);
    }

    public static void printAndPromptToSaveLog(Scanner scanner) {
        System.out.println("\nBattle Log:");
        System.out.println(battleLog.toString());
        System.out.println("Do you want to save this log? (yes/no)");

        String saveChoice = scanner.nextLine();  // Use the passed Scanner instance
        if (saveChoice.equalsIgnoreCase("yes")) {
            saveBattleLog();
            System.out.println("Battle log saved!");
        } else {
            System.out.println("Battle log not saved.");
        }
    }

    public static void saveBattleLog() {
        try (FileWriter writer = new FileWriter("battle_log.txt", false)) {
            writer.write(battleLog.toString());
            writer.write("--------------------------------------------------\n");
        } catch (IOException e) {
            System.err.println("Error saving battle log: " + e.getMessage());
        }
    }

    public static String getBattleLog() {
        return battleLog.toString();
    }

}
