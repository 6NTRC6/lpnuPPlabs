package com.droids.menu;

import com.droids.battle.Battle;
import com.droids.droid.BattleDroid;
import com.droids.droid.Droid;
import com.droids.droid.HealerDroid;
import com.droids.factory.DroidFactory;
import com.droids.team.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.droids.utils.FileUtils.printFile;

public class Menu {
    private static final List<Droid> droidList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("\nWelcome to the Droid Battle Simulator!");
        droidList.add(new BattleDroid("Warrior", 150, 15));
        droidList.add(new HealerDroid("Healer", 100, 10, 7, 20));
        droidList.add(new HealerDroid("Healer2", 210, 5, 5, 20));
        droidList.add(new BattleDroid("Warrior2", 200, 10));


        while (running) {
            System.out.println("=======================");
            System.out.println("1. Create Droid");
            System.out.println("2. Show List of Created Droids");
            System.out.println("3. Start One-on-One Battle");
            System.out.println("4. Start Team vs Team Battle");
            System.out.println("5. Load and Replay Battle Log");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        Droid droid = DroidFactory.createDroid();
                        if (droid != null) {
                            droidList.add(droid);
                        } else {
                            System.out.println("Failed to create droid due to invalid parameters.");
                        }
                        break;

                    case 2:
                        if (droidList.isEmpty()) {
                            System.out.println("No droids created yet.");
                        } else {
                            System.out.println("Created Droids:");
                            for (int i = 0; i < droidList.size(); i++) {
                                System.out.println((i + 1) + ": " + droidList.get(i));
                            }
                        }
                        break;

                    case 3:
                        if (droidList.size() < 2) {
                            System.out.println("Need at least 2 droids to start a battle.");
                        } else {
                            System.out.println("Choose droids to battle (enter the number corresponding to each droid):");
                            System.out.print("Enter first droid number: ");
                            int index1 = Integer.parseInt(scanner.nextLine()) - 1; // Adjust for 1-based index
                            System.out.print("Enter second droid number: ");
                            int index2 = Integer.parseInt(scanner.nextLine()) - 1; // Adjust for 1-based index

                            if (isValidDroidSelection(index1, index2)) {
                                Droid droid1 = droidList.get(index1);
                                Droid droid2 = droidList.get(index2);
                                Battle.oneOnOne(droid1, droid2);
                            }
                            Battle.printAndPromptToSaveLog(scanner);
                        }
                        break;

                    case 4:
                        if (droidList.size() < 4) {
                            System.out.println("Need at least 4 droids to form two teams.");
                        } else {
                            System.out.println("Forming teams...");
                            Team team1 = new Team("Team A");
                            Team team2 = new Team("Team B");

                            createTeam(droidList, scanner, team1);
                            createTeam(droidList, scanner, team2);

                            Battle.teamVsTeam(team1, team2, scanner);
                        }
                        break;

                    case 5:
                        System.out.println("Loading and replaying battle log...");
                        printFile("battle_log.txt");
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static boolean isValidDroidSelection(int index1, int index2) {
        if (index1 < 0 || index1 >= droidList.size() || index2 < 0 || index2 >= droidList.size() || index1 == index2) {
            System.out.println("Invalid droid selection. Please select different droids.");
            return false;
        }
        return true;
    }

    private static boolean isValidDroidIndex(int index) {
        return index >= 0 && index < droidList.size();
    }

    private static void createTeam(List<Droid> droidArr, Scanner scanner, Team team) {
        for (int i = 0; i < droidArr.size()/2; i++) {
            System.out.println("Select Droid for Team :" + team.getName());
            int index = Integer.parseInt(scanner.nextLine()) - 1; // Adjust for 1-based index
            if (isValidDroidIndex(index)) {
                team.addDroid(droidList.get(index));
            } else {
                System.out.println("Invalid selection. Please try again.");
                i--; // Decrement to retry the same selection
            }
        }
    }

}
