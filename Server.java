import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT_NUMBER = 8080;
    private int client_number = 0;

    public Server() throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        System.out.println("Server is opened at port : " + PORT_NUMBER);
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, this);
                Thread thread = new Thread(serverThread);
                thread.start();
                System.out.println("New Client connected with server.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            serverSocket.close();
        }
    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getClientNumber() {
        return ++this.client_number;
    }
}
