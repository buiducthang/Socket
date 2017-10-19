/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bui
 */
public class MultiThreadChatServer {
    // The server socket
    private static ServerSocket serverSocket = null;
    // The client socket
    private static Socket clientSocket = null;
    
    // This chat server can accept up to maxClientCount clients's connections
    private static final int maxClientsCount = 10;
    private static final clientThread[] threads = new clientThread[maxClientsCount];
    
    public static void main(String[] args) {
        // The default port number
        int portNumber = 2222;
        if (args.length < 1){
            System.out.println("Now using port = " + portNumber);
        }
        else {
            portNumber = Integer.valueOf(args[0]).intValue();
        }
        
        try {
            // Open a server socket on the portNumber (default 2222). Note that we can
            // not choose a port less than 1023 if we are not privileged users (root)

            serverSocket = new ServerSocket(portNumber);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        // Create a client socket for each connection and pass it to a new client thread
        while(true){
            try {
                clientSocket = serverSocket.accept();
                int i= 0;
                for(i=0; i<maxClientsCount; i++){
                    if (threads[i] == null){
                        (threads[i] = new clientThread(clientSocket, threads)).start();
                        break;
                    }
                }
                
                if(i == maxClientsCount){
                    PrintStream os = new PrintStream(clientSocket.getOutputStream());
                    os.println("Server too busy");
                    os.close();
                    clientSocket.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
