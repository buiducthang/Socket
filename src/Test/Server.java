package Test;
import java.awt.List;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Server extends Thread {
    private ServerSocket server;
    protected ArrayList<ClientHandler> clients;

    public Server(int port) {
        try {
            this.server = new ServerSocket(port);
            System.out.println("New server initialized!");
            clients = new ArrayList<ClientHandler>();
            this.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        while (true) {
            try {
                Socket client = server.accept();
                System.out.println(client.getInetAddress().getHostName()
                        + " connected");
                ClientHandler newClient = new ClientHandler(client);
                clients.add(newClient);
                new SendMessage(clients);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        new Server(1200);
    }
}