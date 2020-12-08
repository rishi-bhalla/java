import java.util.Arrays;
import java.util.Random;

/**
 * This class represent a simple Number game that generates three random numbers between 1 to 10.
 * It also selects one lucky number from the three random numbers it generated previously.
 * Depending upon whether the number entered by the client matches the lucky number, the client wins or looses.
 */
public class NumberGame {

    private int arr[];
    private int luckyNumber;
    private static final int QUANTITY = 3;

    public NumberGame() {
        arr = new int[QUANTITY];
        luckyNumber = -1;
    }

    /**
     * This method checks whether the three random number generated do not contain duplicates.
     *
     * @param num
     * @return
     */
    private boolean isDuplicate(int num) {
        for (int i=0; i< arr.length; i++) {
            if(arr[i] == num)
                return true;
        }
        return false;
    }

    /**
     * This method initializes the game. The three random numbers are generated and the lucky number of the three is chosen.
     *
     */
    public void setGame() {
        Random random = new Random();
        int i = 0, temp;
        while (i < QUANTITY) {
            temp = random.nextInt(10) + 1;
            if(!isDuplicate(temp)) {
                arr[i] = temp;
                i++;
            }
        }

        luckyNumber = arr[random.nextInt(QUANTITY)];
    }

    /**
     * This method returns the three lucky numbers generated in the form of a string
     *
     * @return
     */
    public String getNumbers() {
        return Arrays.toString(arr);
    }

    /**
     * This method checks whether the number chosen by the client matches the lucky number or not. Accordingly the client wins or looses.
     *
     * @param num
     * @return
     */
    public boolean checkWins(int num) {
        return luckyNumber == num;
    }
}
