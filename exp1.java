// server.java

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        try {
            // create server socket and bind it to a port
            ServerSocket serverSocket = new ServerSocket(3000);
            
            System.out.println("Server started and listening to the port 3000");
            
            // wait for client connections
            Socket socket = serverSocket.accept();
            
            // create input stream to receive messages from client
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            
            // create output stream to send messages to client
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            
            // loop to read messages from client and send response
            while (true) {
                String message = reader.readLine();
                System.out.println("Received message from client: " + message);
                writer.println("Server: " + message);
            }
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}



// client
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            // create socket and connect to server
            Socket socket = new Socket("localhost", 3000);
            
            System.out.println("Connected to server");
            
            // create input stream to receive messages from server
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            
            // create output stream to send messages to server
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            
            // loop to read messages from user and send to server
            while (true) {
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter message: ");
                String message = userInput.readLine();
                writer.println(message);
                
                // read response from server and display to user
                String response = reader.readLine();
                System.out.println("Received response from server: " + response);
            }
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
