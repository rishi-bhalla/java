import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class is the standalone application representing the server and its services.
 * Here, the service offered by the server is a number game.
 * The client can connect to the server to play the game.
 */
public class Server {

    private ServerSocket port;

    public Server(int num) throws Exception {
        //A port is a connection end point
        //It is represented by a unqiue whole number in range 0-65535
        //0 : reserved
        //1-1024 : OS use (wellknown ports)
        //1025-65535 : Applications use
        port = new ServerSocket(num);
        new ConnectionManager();
    }

    /**
     * This method is used to close the server port.
     */
    public void close() {
        try {
            port.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This inner class is used to manage the multiple connections coming to the server.
     * It keeps on listening to the server port for connections. Whenever a client tries to connect,
     * it accepts the connection and created a new thread to serve the client.
     */
    class ConnectionManager extends Thread {

        public ConnectionManager() {
            start();
        }

        /**
         * This method listens to the server port for connections and created a new thread to serve the client everytime a new request comes.
         */
        private void acceptConnections() {
            try {
                Socket socket = port.accept();
                System.out.println("Got a client");
                new ClientProcessor(socket);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * This is the run method of the ConnectionManager class.
         */
        public void run() {
            while(true) {
                acceptConnections();
            }
        }
    }

    /**
     * This inner class is used to handle each client request separately.
     * Every client is served by a separate new thread.
     */
    class ClientProcessor extends Thread {

        private Socket clientSocket;
        private static final int MAX_ATTEMPTS = 3;

        public ClientProcessor(Socket clientSocket) {
            this.clientSocket = clientSocket;
            start();
        }

        /**
         * This method is used to to interact with the client and play the game. This method accepts the inputs from the client, processes the data and sends back
         * the response to the client.
         */
        private void processClient() {
            try {
                //in and out support r/w of bytes only
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();

                //din and dout support r/w of all Java datatypes
                DataInputStream din = new DataInputStream(in);
                DataOutputStream dout = new DataOutputStream(out);

                boolean flag = false;
                String uid, upass;
                int cnt = 0, availableMoney = 1000, playAmount, clientNumber;
                NumberGame ng = new NumberGame();

                //login authentication
                do {
                    uid = din.readUTF();
                    upass = din.readUTF();
                    if(uid.equalsIgnoreCase("test") && upass.equals("test"))
                        flag = true;
                    dout.writeBoolean(flag);
                    cnt++;
                } while (!flag && cnt < MAX_ATTEMPTS);

                if(!flag && cnt == MAX_ATTEMPTS) {
                    dout.writeUTF("You have exceeded maximum number of attempts.");
                    clientSocket.close();
                    return;
                }

                dout.writeInt(availableMoney);

                //logged in
                do {
                    cnt = 0;
                    do {
                        playAmount = din.readInt();
                        if(playAmount <= 0 || playAmount > availableMoney)
                            flag = false;
                        cnt++;
                        dout.writeBoolean(flag);
                    } while (!flag && cnt < MAX_ATTEMPTS);

                    if(!flag && cnt == MAX_ATTEMPTS) {
                        dout.writeUTF("You have exceeded maximum number of attempts.");
                        break;
                    }

                    //lets play
                    ng.setGame();
                    dout.writeUTF(ng.getNumbers());
                    clientNumber = din.readInt();
                    if(ng.checkWins(clientNumber)) {
                        dout.writeBoolean(true);
                        availableMoney += playAmount * 10;
                    } else {
                        dout.writeBoolean(false);
                        availableMoney -= playAmount;
                    }

                    dout.writeInt(availableMoney);
                    flag = din.readBoolean();
                } while(flag);
                clientSocket.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        /**
         * This is the run method of the ClientProcessor class.
         */
        public void run() {
            processClient();
        }
    }
}
