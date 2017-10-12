/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.sun.corba.se.pept.encoding.InputObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bui
 */
public class Client {
    protected Socket client;
    protected BufferedReader in;

    public Client(String hostName, int ip) {
        try {
            this.client = new Socket(hostName, ip);
            this.in = new BufferedReader(new InputStreamReader(
                    this.client.getInputStream()));
            String buffer = null;
            while ((buffer = in.readLine()) != null) {
                System.out.println(buffer);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }     
    
    public static void main(String[] args) {
        new Client("127.0.0.1",1200);
    }
}
