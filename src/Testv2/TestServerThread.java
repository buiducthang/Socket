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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bui
 */
public class TestServerThread extends Thread{
    protected Socket clientSocket;
    
    public TestServerThread(Socket socket){
        this.clientSocket = socket;
    }
    
    public void run(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            Object o = ois.readObject();
            if(o instanceof String)
            {
                //int rs = (Strin)o + 2;
                System.out.println("rs:"+o.toString());
                oos.writeObject(o.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(TestServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(TestServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
