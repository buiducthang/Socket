/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bui
 */
public class MultiThreadChatClient implements Runnable {
    
    // The Client socket
    private static Socket clientSocket = null;
    // The output stream
    private static PrintStream os = null;
    // The input stream
    private static DataInputStream is = null;
    
    private static BufferedReader inputLine = null;
    private static boolean closed = false;
    
    public static void main(String[] args) {
        // The default port
        int portNumber = 2222;
        // The deafault host
        String host = "localhost";
        
        if(args.length < 2){
            System.out.println("Now using host=" + host + ", port=" + portNumber);
        }
        else
        {
            host = args[0];
            portNumber = Integer.valueOf(args[1]).intValue();
        }
        
        try {
            // Open a socket on a given host and port. Open input and output streams
            clientSocket = new Socket(host, portNumber);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Couldn't get I/O for the connection to the host " + host);
        }
        
        // If everything has been initialized then we want to write some data 
        // to the socket we have opened a connection to on the port number
        if(clientSocket != null && os != null && is != null)
        {
            try {  
                // Create a thread to read from server
                new Thread(new MultiThreadChatClient()).start();    
                while(!closed){
                    os.println(inputLine.readLine().trim());
                }
                // Close the output stream, close the input stream, close the socket
                os.close();
                is.close();
                clientSocket.close();
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        
    
    // Create a thread to read from server 
    @Override
    public void run() {
        // Keep on reading from socket till we receive "Bye" from the server
        // Once we received that then we want to break
        String responseLine;
        try {
            while((responseLine = is.readLine()) != null){
                System.out.println(responseLine);
                if(responseLine.indexOf("*** Bye") != -1)
                    break;
            }
            closed = true;
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
}
