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
    public static void main(String[] args) {
        try {
            System.out.println("Nhap ki tu:");
            Scanner input = new Scanner(System.in);
            
            Socket clientSocket = new Socket("127.0.0.1",9000);
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject(input.nextLine());
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            
            Object o = ois.readObject();
            System.out.println(o.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
