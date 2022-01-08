/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import model.LoginPlayer;
import model.Player;

public class ClientConnection {

    private static Socket socket = null;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;

    public ClientConnection() {}

    public static Socket createSocket(String address, int port) throws IOException {
        if (socket == null) {
            socket = new Socket(address, port);
        }
        return socket;
    }
    
    public static ObjectOutputStream createOutObject() throws IOException {
        if(objectOutputStream == null) {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }
        return objectOutputStream;
    }
    
    public static ObjectInputStream createInputObject() throws IOException {
        if(objectInputStream == null) {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        }
        return objectInputStream;
    }
    
    public static void closeSocket() throws IOException {
        objectInputStream.close();
        objectOutputStream.close();
        socket.close();
        objectInputStream = null;
        objectOutputStream = null;
        socket = null;
    }

    public void sendLoginDataToServer(LoginPlayer model) throws IOException {
        createOutObject().writeObject(model);
        objectOutputStream.flush();
    }

    public void sendRequestOnlineUsers(String playerName) throws IOException {
        createOutObject().writeObject(playerName);
        objectOutputStream.flush();
    }

    public Object reciveOnlineUsers() throws IOException, ClassNotFoundException {
        return (ArrayList<Player>) createInputObject().readObject();
    }

    public Object reciveLoginDataFromServer() throws IOException, ClassNotFoundException {
        return createInputObject().readObject();
    }

    public void sendRegisterDataToServer(Player model) throws IOException {
        createOutObject().writeObject(model);
        objectOutputStream.flush();
    }

    public Object reciveRegisterDataFromServer() throws IOException, ClassNotFoundException {
        return createInputObject().readObject();
    }

    public void sendChangeStatues(Player model) throws IOException, ClassNotFoundException {
        createOutObject().writeObject(model);
        objectOutputStream.flush();
    }
    
}