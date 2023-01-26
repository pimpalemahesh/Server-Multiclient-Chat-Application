import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Client {

    private static final int PORT_NUMBER = 8080;
    private static final String SERVER_HOST = "127.05.0.1";
    private static Socket socket;

    public static void main(String[] args) throws IOException {
        System.out.println("Client started.");

        try {
            socket = new Socket(SERVER_HOST, PORT_NUMBER);
            socket.setSoTimeout(60000);
            BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String message = in_socket.readLine();
                System.out.println("Server says : " + message);
                if(message.equalsIgnoreCase("exit")){
                    if(!socket.isClosed()){
                        socket.close();
                        break;
                    }
                }
                message = bufferedReader.readLine();
                if(message.equalsIgnoreCase("exit")){
                    if(!socket.isClosed()){
                        socket.close();
                        break;
                    }
                }
                out_socket.println(message);
            }
        } 
        catch(SocketTimeoutException exception){

            if(!socket.isClosed()){
                socket.close();
            }
            System.out.println("Server is not available...");
        }
        catch (Exception e) {
            if(!socket.isClosed()){
                socket.close();
            }
            e.printStackTrace();
        }
        finally{
            if(!socket.isClosed()){
                socket.close();
            }
        }
    }
}