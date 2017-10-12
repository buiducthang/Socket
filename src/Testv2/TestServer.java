/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testv2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bui
 */
public class TestServer {
    ServerSocket serverSocket;
    Socket clientSocket;

    public TestServer() {
        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("Server is running");
            
            while(true){
                clientSocket = serverSocket.accept();
                TestServerThread serverThread = new TestServerThread(clientSocket);
                serverThread.start();
//                ArrayList<Socket> clients = new ArrayList<>();
//                //clients.add(serverSocket.accept());
//                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
//                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
//                
//                Object o = ois.readObject();
//                
//                if(o instanceof String)
//                {
//                    //int rs = (Strin)o + 2;
//                    System.out.println("rs:"+o.toString());
//                    oos.writeObject(o.toString());
//                }
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } 
    }
    
    public static void main(String[] args) {
        new TestServer();
    }
    
}
