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
    ArrayList<TestServerThread> clients = new ArrayList<>();
    public TestServer() {
        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("Server is running");
            
            while(true){
                clientSocket = serverSocket.accept();
                TestServerThread serverThread = new TestServerThread(clientSocket,"sent");
                
                clients.add(serverThread);
                System.out.println("aaaa");
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                String msg = (String) ois.readObject();
                
                System.out.println(msg);
                for(int i=0;i<clients.size();i++){
                    System.out.println(String.valueOf(i));
                    System.out.println("size:" + clients.size());
                    if(String.valueOf(i).equals(msg)){
                        //clients.get(i).setClientSocket(clientSocket);
                        clients.get(i).setMsg(msg);
                        clients.get(i).start();
                    }
                }
                
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestServer.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static void main(String[] args) {
        new TestServer();
    }
    
}
