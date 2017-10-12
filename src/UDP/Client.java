/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bui
 */
public class Client {
    public static void main(String[] args) throws IOException {
        int a, b, tong;
        
        try {
            // Tao moi Socket Client
            DatagramSocket clientSocket = new DatagramSocket();
            
            // Nhap yeu cau tu nguoi dung
            DataInputStream dis = new DataInputStream(System.in);
            
            System.out.println("Nhap a:");
            a = Integer.parseInt(dis.readLine());
            System.out.println("Nhap b:");
            b = Integer.parseInt(dis.readLine());
            
            // Khai bao mang byte de chuyen du lieu di server
            byte outToServer1[];
            byte outToServer2[];
            
            // Chuyen du lieu tu int -> string
            String s1 = String.valueOf(a);
            String s2 = String.valueOf(b);
            
            // Chuyen du lieu String -> byte va dua vao mang byte
            outToServer1 = s1.getBytes();
            outToServer2 = s2.getBytes();
            
            // Lay kich thuoc cua mang
            int length1 = outToServer1.length;
            int length2 = outToServer2.length;
            
            // Dia chi may chu
            InetAddress host = InetAddress.getByName("localhost");
            
            int port = 3602;
            // Tao goi de gui di
            DatagramPacket toServer1 = new DatagramPacket(outToServer1, length1, host, port);
            DatagramPacket toServer2 = new DatagramPacket(outToServer2, length2, host, port);
            
            // gui goi len server
            clientSocket.send(toServer1);
            clientSocket.send(toServer2);
            
            // Tao goi de nhan du lieu ve
            byte inFromServer[] = new byte[256];
            
            // kich thuoc mang nhan ve
            length1 = inFromServer.length;
            
            // Tao goi nhan du lieu ve
            DatagramPacket fromServer = new DatagramPacket(inFromServer, length1);
            
            // Nhan goi du lieu tra ve tu server
            clientSocket.receive(fromServer);
            
            // Khai bao bien de chuyen du lieu tu byte sang String
            String data;
            
            // Dua du lieu tu mang byte vao bien data, lay vi tri tu vi tri so 0
            data = (new String(fromServer.getData(),0,fromServer.getLength())).trim();
            
            // In ket qua ra man hinh
            System.out.println("ket qua:" + data);
            clientSocket.close();
        } catch (SocketException ex) {
            System.out.println(ex.toString());
        }
        
    }
}
