/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import controller.SceneController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;


public class ClientConnection {
 private Socket socket = null;
private DataInputStream input = null;
private DataOutputStream out = null;
public ClientConnection(String address, int port ,ActionEvent event){
    
      SceneController controller=new SceneController();
     try {
         socket = new Socket(address, port);
           if(socket.isConnected()){
               System.out.println("bbbbbb");
                     controller.switchToLoginScene(event);
           }
       
         
       
     } catch (IOException ex) {
         Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
     }
}




}

