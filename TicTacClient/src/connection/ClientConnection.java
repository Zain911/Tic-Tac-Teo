/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Player;

/**
 *
 * @author Mohamed Galal
 */
public class ClientConnection {

    ObjectOutputStream objectOutputStream;
    OutputStream outputStream;
    ObjectInputStream objInputStream;
    InputStream inputStream;
    Socket mySocket;

    public ClientConnection() {

        try {
            //Establish connection
            mySocket = new Socket("10.178.241.71", 5000);
            System.out.println("Connected!");
            outputStream = mySocket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            inputStream = mySocket.getInputStream();
            objInputStream = new ObjectInputStream(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void handleConnection() {
        Player p = new Player("15", "ooo", 15, "ss", true, true);
        //System.out.println(p.getId() + "    //" + p.getName());
        System.out.println("Sending messages to the ServerSocket");
        readAndWrite(p);

    }

    private void readAndWrite(Player p) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        objectOutputStream.writeObject(p);
                        objectOutputStream.flush();
                        System.out.println(objInputStream.readObject());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
}