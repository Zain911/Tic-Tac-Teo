/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import controller.SceneController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import model.LoginModel;

public class ClientConnection {

    private static ClientConnection clientConnection = null;
    private static Socket socket = null;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private OutputStream outputStream;

    public ClientConnection() {
    }

    public static Socket createSocket(String address, int port) throws IOException {

        if (socket == null) {
            socket = new Socket(address, port);

        }
        return socket;

    }

    public void sendLoginDataToServer(LoginModel model) throws IOException{
        outputStream = socket.getOutputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(model);
        objectOutputStream.flush();
        System.out.println("done");
    }
    
    
    public  void reciveDataFromServer() throws IOException, ClassNotFoundException{
    
         objectInputStream = new ObjectInputStream(socket.getInputStream());
           Object object = objectInputStream.readObject();
    }
    public void clientConnection1(String address, int port, ActionEvent event) throws IOException {
        SceneController controller = new SceneController();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    socket = new Socket(address, port);
                    System.out.println("connected");
                    controller.switchToLoginScene(event);

                } catch (IOException ex) {
                    Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();

    }

    public void clientConnection(String address, int port, ActionEvent event) throws IOException {
        SceneController controller = new SceneController();

        socket = new Socket(address, port);
        System.out.println("connected");
        controller.switchToLoginScene(event);

    }

}
