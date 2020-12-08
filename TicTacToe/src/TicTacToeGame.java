import java.util.Scanner;

public class TicTacToeGame {

    private static String[][] board;
    private static String[]  players = {"Player 1", "Player 2"};
    private static String[] symbols = {"O", "X"};
    private static String OPEN_BRACKET = "[";
    private static String CLOSE_BRACKET = "]";
    private static String EMPTY = OPEN_BRACKET + " " + CLOSE_BRACKET;
    private static Scanner scanner = new Scanner(System.in);

    private static void initialize() {
        board = new String[3][3];
        for(int i = 0; i< board.length; i++) {
            for(int j = 0; j< board[i].length; j++)
                board[i][j] = EMPTY;
        }
    }

    private static void displayBoard() {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isBoardFull() {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j].equals(EMPTY))
                    return false;
            }
        }
        return true;
    }

    private static boolean updateBoard(int row, int column, int current) {
        if(row < 0 || row >= board.length)
            return false;
        if(column < 0 || column >= board[row].length)
            return false;
        if(board[row][column] != EMPTY)
            return false;

        board[row][column] = OPEN_BRACKET + symbols[current] + CLOSE_BRACKET;
        return true;
    }

    private static boolean checkIfWins(int current) {
        String toCompare = OPEN_BRACKET + symbols[current] + CLOSE_BRACKET;
        for(int i=0; i<board.length; i++)
            if(board[i][0].equals(toCompare) && board[i][1].equals(toCompare) && board[i][2].equals(toCompare))
                return true;

        for(int i=0; i<board.length; i++)
             if(board[0][i].equals(toCompare) && board[1][i].equals(toCompare) && board[2][i].equals(toCompare))
                 return true;

        if(board[0][0].equals(toCompare) && board[1][1].equals(toCompare) && board[2][2].equals(toCompare))
             return false;

        if(board[0][2].equals(toCompare) && board[1][1].equals(toCompare) && board[2][0].equals(toCompare))
            return true;

        return false;
    }

    public static void tictactoe() {
        initialize();
        displayBoard();
        System.out.println("Enter name of player 1:");
        players[0] = scanner.next();
        System.out.println("Enter name of player 2:");
        players[1] = scanner.next();

        System.out.println("Symbol for player " + players[0] + " is: " + symbols[0]);
        System.out.println("Symbol for player " + players[1] + " is: " + symbols[1]);

        int current = 0;
        int status = 0;
        while (!isBoardFull()) {
            System.out.println("Player " + players[current] + " plays");
            System.out.println("Enter row (0-2):");
            int row = scanner.nextInt();
            System.out.println("Enter column (0-2)");
            int column = scanner.nextInt();
            if(updateBoard(row, column, current)) {
                displayBoard();
                if(checkIfWins(current)) {
                    System.out.println("Player " + players[current] + " wins !!!");
                    status = 1;
                    break;
                } else
                    current = (current + 1) % 2;
            }
        }

        if(status == 0)
            System.out.println("Game draw ");
    }

    public static void main(String[] args) {
        char ch = 'y';
        while(ch == 'y' || ch == 'Y') {
            tictactoe();
            System.out.println("Do you wish to play again (y/n) ?");
            ch = scanner.next().charAt(0);
        }
    }
}
