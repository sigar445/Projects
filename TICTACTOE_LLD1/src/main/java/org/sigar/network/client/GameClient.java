package org.sigar.network.client;
import org.sigar.network.modifiedFiles.ModifiedGameApplication;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class GameClient implements AutoCloseable{
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    boolean isRunning = true;
    public GameClient(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }
    public void startClient(){
        activateSender();
        listenForServerMessages();
    }
    public void stopClient(){
        isRunning = false;
        close();
    }
    public void activateSender()  {
        new Thread(() -> {
            try(Scanner scanner = new Scanner(System.in)){
                while (isRunning){
                    String command = scanner.nextLine().trim();
                    writer.println(command);
                    if(command.contains("QUIT") || command.contains("Quit") || command.contains("quit")) {
                        System.out.println("Exiting the sender");
                        stopClient();
                    }
                }
            }
        }).start();
    }
    public void listenForServerMessages(){
        try {
            System.out.println("Waiting for messages from server ");
            String message;
            while (isRunning && ((message = reader.readLine()) != null)) {
                System.out.println("Server: " + message);
            }
        }catch (IOException e){
            System.out.println("Connection error: " + e.getMessage());
        }finally {
            stopClient();
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
        String host = "localhost";
        int port = ModifiedGameApplication.SERVER_PORT;
        try(GameClient client = new GameClient(host,port)){
            client.startClient();
        } catch (Exception e) {
            System.err.println("Failed to start client: " + e.getMessage());
        }
    }
}
