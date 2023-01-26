import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{
    
    private Socket socket;
    private Server server;
    private int clientId;

    public ServerThread(Socket socket, Server server){
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            clientId = server.getClientNumber();
            System.out.println("Client : " + clientId + " is connected with server.");
            // Input-Output Buffers.
            BufferedReader input_from_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output_to_socket = new  PrintWriter(socket.getOutputStream(), true);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            output_to_socket.println("Welcome to Server What is your name?");
            String message = input_from_socket.readLine();
            System.out.println("Client " + clientId + " replied : " + message);
            output_to_socket.println("Hello! " + message);
            while(true){
                message = input_from_socket.readLine();
                System.out.println("Client " + clientId + " replied : " + message);
                message = bufferedReader.readLine();
                if(message.equalsIgnoreCase("exit")){
                    output_to_socket.println(message);
                    break;
                }
                output_to_socket.println(message);
            }
            
            if(!socket.isClosed()){
                socket.close();
            }
            System.out.println("Client : " + clientId + " is disconnected.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(!socket.isClosed()){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }


}
