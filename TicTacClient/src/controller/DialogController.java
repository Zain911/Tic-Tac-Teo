/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.javafx.scene.control.skin.CustomColorDialog;
import connection.ClientConnection;
import helper.CustomDialog;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mka
 */
public class DialogController implements Initializable {

    @FXML
    Label invalidIp;
    @FXML
    TextField ipTextField;
    Socket socket;
    @FXML
    public static ProgressIndicator progressIndicator;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void onCancleClicked(ActionEvent event) {
        try {
            SceneController controller = new SceneController();
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(DialogController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void onConnectedClicked(ActionEvent event) {
        SceneController controller=new SceneController();
        if (ipTextField.getText().isEmpty()) {

            invalidIp.setText(" please Enter ip to coneected");
        } else if (isVaildIp(ipTextField.getText()) == true) {
            try {
              socket=  ClientConnection.createSocket(ipTextField.getText(),5000);
                controller.switchToLoginScene(event);
            } catch (IOException ex) {
                
                /// handel no connection
                CustomDialog.askPlayAgain("cant connection");
            }

        } 
        
        else {
            
            System.out.println("invalid");
             invalidIp.setText(" please Enter ip to coneected");


        }        System.out.println("hhhhh");

    }

    @FXML
    private void startTyping() {

        invalidIp.setText(" ");

    }

    private static boolean isVaildIp(String ip) {
        boolean result = false;
        String ipRegex = "\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b";
        Pattern ip_pattern = Pattern.compile(ipRegex);

        if (ip_pattern.matcher(ip).matches()) {

            return true;

        }

        return false;

    }

    @FXML
    private void back(ActionEvent event) {
        SceneController controller = new SceneController();
        try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
