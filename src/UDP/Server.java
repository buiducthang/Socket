/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Bui
 */
public class Server {
    public static void main(String[] args) throws SocketException, IOException {
        // Tao ket noi 
        DatagramSocket serverSocket = new DatagramSocket(3602);
        
        // Thong bao server da san sang ket noi
        System.out.println("Server is now already");
        
        //Tao mang byte de chua du lieu gui len tu Client
        byte inFromClient1[] = new byte[256];
        byte inFromClient2[] = new byte[256];
        
        // Lay kich thuoc mang
        int length1 = inFromClient1.length;
        int length2 = inFromClient2.length;
        
        // Tao goi de nhan du lieu tu Client
        DatagramPacket fromClient1 = new DatagramPacket(inFromClient1, length1);
        DatagramPacket fromClient2 = new DatagramPacket(inFromClient2, length2);
        
        // nhan goi ve server
        serverSocket.receive(fromClient1);
        serverSocket.receive(fromClient2);
        
        // Tao bien data kieu String de lay du lieu trong goi ra
        String data1, data2;
        
        // Lay du lieu vao bien data
        data1 = (new String(fromClient1.getData(),0,inFromClient1.length)).trim();
        data2 = (new String(fromClient2.getData(),0,inFromClient2.length)).trim();
        
        // Chuyen du lieu tu String->integer
        int a,b,tong;
        a = Integer.parseInt(data1);
        b = Integer.parseInt(data2);
        tong = a+b;
        
        // Chuyen du lieu tu kieu int -> String va truyen vao bien data
        String data = String.valueOf(tong);
        
        // Dong goi ket qua
        byte outToClient[] = data.getBytes();
        
        // Lay kich thuoc mang
        int lengthOfData = outToClient.length;
        
        // Lay dia chi cua may khach, no nam luon trong goi ma da gui len server
        InetAddress address = fromClient1.getAddress();
        
        // Lay so port
        int port = fromClient1.getPort();
        
        // Tao goi de gui ve client
        DatagramPacket toClient = new DatagramPacket(outToClient, lengthOfData, address, port);
        // Gui ve client
        serverSocket.send(toClient);
        // Dong socket
        serverSocket.close();
    }
}
