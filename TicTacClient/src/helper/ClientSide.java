package helper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
    
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohamed Galal
 */
public class ClientSide {
    // initialize socket and input output streams

    private static Socket socket = null;
    private static DataInputStream input = null;
    private static DataOutputStream out = null;

    public static void connection(String networkIp) {
        try {
            socket = new Socket(networkIp, 3002);
            input = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientSide.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread() {
            @Override
            public void run() {
                String str = "";
                while (true) {

                    try {
                        if (input != null) {
                            str = input.readLine();
                        }
                        System.out.println("" + str);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientSide.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    public static void disConnection() {

        // close the connection
        try {
            input.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public ClientSide() {

    }
}
