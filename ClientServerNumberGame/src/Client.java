import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class is the standalone application representing the client.
 * The client connects to the server and uses the services hosted by it.
 * The client accepts the inputs from the user, sends them to the server and also accepts the data back from the server.
 * This two way communication helps the users to interact with the server and use its services.
 */
public class Client {

    private Socket serverSocket;
    private static final int MAX_ATTEMPTS = 3;

    public Client(String ip, int port) throws Exception{
        //request connection
        serverSocket = new Socket(ip, port);
    }

    /**
     * This method returns the integer accepted from System.in whenever requested by the client application.
     * @return
     */
    private static int getInt() {
        return new Scanner(System.in).nextInt();
    }

    /**
     * This method returns the string accepted from System.in whenever requested by the client application.
     *
     * @return
     */
    private static String getString() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * This method is used to play the game between the client and the server. It accepts input from the client, sends them to the server, captures server's response
     * and displays them back to the client.
     */
    public void play() {
        try {
            //in and out support r/w of bytes only
            InputStream in = serverSocket.getInputStream();
            OutputStream out = serverSocket.getOutputStream();

            //din and dout support r/w of all Java datatypes.
            DataInputStream din = new DataInputStream(in);
            DataOutputStream dout = new DataOutputStream(out);

            System.out.println("Welcome to the Number Game!!!");

            String uid, upass, numbers, ch;
            int cnt = 0, playAmount, availableMoney, clientNumber;
            boolean flag;

            do {
                System.out.println("Enter you user id:");
                uid = getString();
                System.out.println("Enter your password:");
                upass = getString();

                dout.writeUTF(uid);
                dout.writeUTF(upass);
                flag = din.readBoolean();
                cnt++;
            } while (!flag && cnt < MAX_ATTEMPTS);

            if(!flag && cnt == MAX_ATTEMPTS) {
                System.out.println(din.readUTF());
                serverSocket.close();
                return;
            }

            availableMoney = din.readInt();
            System.out.println("Available money: " + availableMoney);

            do {
                cnt = 0;
                do {
                    System.out.println("Enter play amount:");
                    playAmount = getInt();
                    dout.writeInt(playAmount);
                    flag = din.readBoolean();
                    cnt++;
                } while (!flag && cnt < MAX_ATTEMPTS);

                if(!flag && cnt == MAX_ATTEMPTS) {
                    System.out.println(din.readUTF());
                    break;
                }

                numbers = din.readUTF();
                System.out.println("Select a number from: " + numbers);
                clientNumber = getInt();
                dout.writeInt(clientNumber);
                flag = din.readBoolean();
                if(flag)
                    System.out.println("Congratulations!!! You win :))");
                else
                    System.out.println("Sorry!!! You lost :((");

                availableMoney = din.readInt();
                System.out.println("Available money: " + availableMoney);
                System.out.println("There are 33% chances of winning.");
                System.out.println("Do you want to play again ? (y/n)");
                ch = getString();
                flag = ch.equalsIgnoreCase("y") ? true : false;
                dout.writeBoolean(flag);
            } while (flag);
            serverSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try {
            new Client("127.0.0.1", 9090).play();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
