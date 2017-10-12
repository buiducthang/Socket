/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testv2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Bui
 */
public class SocketServer implements Runnable{
    public ServerThread clients[];
    public ServerSocket server = null;
    public Thread       thread = null;
    public int clientCount = 0, port = 13000;
    
    public SocketServer(){
       
        clients = new ServerThread[50];
        
	try{  
	    server = new ServerSocket(port);
            port = server.getLocalPort();
	    System.out.println("Server startet. IP : " + InetAddress.getLocalHost() + ", Port : " + server.getLocalPort());
	    start(); 
        }
	catch(IOException ioe){  
            System.out.println("Can not bind to port : " + port + "\nRetrying"); 
            //ui.RetryStart(0);
	}
    }
    
    public SocketServer(int Port){
       
        clients = new ServerThread[50];
        port = Port;
        
	try{  
	    server = new ServerSocket(port);
            port = server.getLocalPort();
	    System.out.println("Server startet. IP : " + InetAddress.getLocalHost() + ", Port : " + server.getLocalPort());
	    start(); 
        }
	catch(IOException ioe){  
            System.out.println("\nCan not bind to port " + port + ": " + ioe.getMessage()); 
	}
    }
    
    public void run(){  
	while (thread != null){  
            try{  
		System.out.println("\nWaiting for a client ..."); 
	        addThread(server.accept()); 
	    }
	    catch(Exception ioe){ 
                System.out.println("\nServer accept error: \n");
                //ui.RetryStart(0);
	    }
        }
    }
    
    public void start(){  
    	if (thread == null){  
            thread = new Thread(this); 
	    thread.start();
	}
    }
    
    @SuppressWarnings("deprecation")
    public void stop(){  
        if (thread != null){  
            thread.stop(); 
	    thread = null;
	}
    }
    
    private int findClient(int ID){  
    	for (int i = 0; i < clientCount; i++){
        	if (clients[i].getID() == ID){
                    return i;
                }
	}
	return -1;
    }
    
    public synchronized void handle(int ID, Message msg){
        if(msg.type.equals("message")){
                if(msg.recipient.equals("All")){
                    Announce("message", msg.sender, msg.content);
                }
        }
    }
    
    public void Announce(String type, String sender, String content){
        Message msg = new Message(type, sender, content, "All");
        for(int i = 0; i < clientCount; i++){
            clients[i].send(msg);
        }
    }
    
    private void addThread(Socket socket){  
	if (clientCount < clients.length){  
            System.out.println("\nClient accepted: " + socket);
            System.out.println("Nhap userid:");
            Scanner input = new Scanner(System.in);
            int userid = input.nextInt();
	    clients[userid] = new ServerThread(this, socket, userid);
	    try{  
	      	clients[userid].open(); 
	        clients[userid].start();  
	        clientCount++; 
	    }
	    catch(IOException ioe){  
	      	System.out.println("\nError opening thread: " + ioe); 
	    } 
	}
	else{
            System.out.println("\nClient refused: maximum " + clients.length + " reached.");
	}
    }
}
