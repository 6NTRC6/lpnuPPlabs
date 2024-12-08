import java.util.Scanner;


/**
 * This is the main class that runs the program.
 */
public class Main {

    /**
     * This is the main method that starts the program execution.
     * It prompts the user to enter an index and then prints the number at that index in the Lucas sequence.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput;

        while (true) {
            System.out.print("Enter index (index <= 0) of Lucas number: ");
            userInput = scanner.nextInt();

            if (userInput <= 0) {
                break; // Exit the loop if the input is valid
            } else {
                System.out.println("Invalid input. Please enter an integer less than or equal to 0.");
            }
        }
        System.out.println("Number with index" + userInput + " at Lucas sequence is " + LucasSequence.getLucasNumberAt(userInput));

    }
}
