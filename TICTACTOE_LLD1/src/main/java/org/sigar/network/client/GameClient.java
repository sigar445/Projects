package org.sigar.network.client;



import java.io.*;
import java.net.Socket;

public class GameClient implements AutoCloseable {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public GameClient(Socket socket){
        try{
            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        }catch (IOException e){
            this.close();
        }
    }
    public void startClient(){
        try {
            System.out.println("Waiting for messages from server ");
            System.out.println(reader.readLine());  // Receive prompt
            writer.println("Harry");
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Server: " + message);
            }
        }catch (IOException e){
            this.close();
        }
    }
    public void close(){
        try {
            System.out.println("Closing all connections");
            if (socket != null) {
                if(reader != null)
                   reader.close();
                if(writer !=null )
                    writer.close();
                if (socket != null && !socket.isClosed()) socket.close();
                System.out.println("Client disconnected.");
            }
        }catch (IOException e){
            System.err.println("Error closing client resources: " + e.getMessage());

        }
    }

    public static void main(String[] args) {
        try(GameClient client = new GameClient(new Socket("localhost", 1245))){
            client.startClient();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
