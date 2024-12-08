public class LucasSequence {
//    manual mode
//
//    private int previousNumber = 2;
//    private int currentNumber = 1;
//
//    public int getPreviousNumber() {
//        int temp = previousNumber;
//        previousNumber = previousNumber - currentNumber;
//        currentNumber = temp;
//        return previousNumber;
//    }
//


    /**
     * Returns the number at a specific index in the Lucas sequence.
     *
     * @param index the index of the number in the sequence
     * @return the number at the specified index
     * @throws IllegalArgumentException if index is higher than 0
     */
    public static int getLucasNumberAt(int index) throws IllegalArgumentException {
        return switch (-index % 6) {
            case 0 -> 2;
            case 1, 5 -> 1;
            case 2, 4 -> -1;
            case 3 -> -2;
            default -> throw new IllegalArgumentException("Invalid index");
        };
    }
}
