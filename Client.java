import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final int PORT_NUMBER = 8080;
    private static final String SERVER_HOST = "127.0.01";
    private static Socket socket;

    public static void main(String[] args) throws IOException {
        System.out.println("Client started.");

        try {
            socket = new Socket(SERVER_HOST, PORT_NUMBER);
            BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String message = in_socket.readLine();
                System.out.println("Server says : " + message);
                message = bufferedReader.readLine();
                out_socket.println(message);
                if(message.equalsIgnoreCase("bye")){
                    socket.close();
                }
            }
        } catch (Exception e) {
            socket.close();
            e.printStackTrace();
        }
    }
}