/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testv2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bui
 */
public class TestClient {
    
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;
    Socket clientSocket;
    
    public TestClient() throws IOException{
        clientSocket = new Socket("127.0.0.1",9000);
    }
    
    public String Client(String input) {
        try {
            
            
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject(input);
            oos.flush();
            ois = new ObjectInputStream(clientSocket.getInputStream());
            
            return (String)ois.readObject();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        } /*catch (ClassNotFoundException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return null;
        
    }
    
    public static void main(String[] args) throws IOException {
        boolean stop = true;
        TestClient client = new TestClient();
        while(stop){
            System.out.println("Nhap ki tu:");
            Scanner input = new Scanner(System.in);
            String in = input.nextLine();
            
            if(in.equals("stop")) {
                stop = false;
                return;
            }
            
            String msg = client.Client(in);
            
            System.out.println("Server:" + msg);
        }
    }
}
