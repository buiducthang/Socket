/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testv2;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Bui
 */
public class RunClient {
    public static void main(String[] args) throws IOException {
        SocketClient client = new SocketClient();
        Thread clientThread = new Thread(client);
        clientThread.start();
        System.out.println("Nhap tn:");
        Scanner input = new Scanner(System.in);
        client.send(new Message("message", "User1", input.nextLine(), "SERVER"));
    }
}
