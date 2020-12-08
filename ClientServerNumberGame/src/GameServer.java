/**
 * This class is the entry point of the server application.
 */

public class GameServer {

    public static void main(String args[]) {
        try {
            new Server(9090);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
