/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.ClientConnection;
import helper.ConstantAttributes;
import helper.CustomDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mka
 */
public class IpDialogController implements Initializable {

    @FXML
    Label invalidIp;
    @FXML
    TextField ipTextField;

    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Button connectedButton;
    @FXML
    private Label invalidIp1;
    @FXML
    private ImageView backButton;
    @FXML
    AnchorPane AnchorPane;
    SceneController controller;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new SceneController();

    }

    @FXML
    public void onConnectedClicked(ActionEvent event) {
        
       

        if (ipTextField.getText().isEmpty()) {

            invalidIp.setText(" please Enter ip to coneected");

        } else if (isVaildIp(ipTextField.getText()) == true) {

            startConnection(event);

        } else {

            System.out.println("invalid");
            invalidIp.setText(" please Enter ip to coneected");

        }

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

            result = true;

        }

        return result;

    }

    @FXML
    private void onBackButtonClick(MouseEvent event) {
        try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void startConnection(ActionEvent event) {

        blockUi();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    
                    ConstantAttributes.SERVER_IP = ipTextField.getText();
                    ClientConnection.createSocket(ConstantAttributes.SERVER_IP, ConstantAttributes.SERVER_PORT);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                controller.switchToLoginScene(event);
                            } catch (IOException ex) {
                                Logger.getLogger(IpDialogController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    
                } catch (IOException ex) {
                     unBlockUi(event);
                    Logger.getLogger(IpDialogController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread.start();
    }

    private void blockUi() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AnchorPane.setDisable(true);
                progressIndicator.setVisible(true);
                connectedButton.setText("connecting");
                connectedButton.setDisable(true);
                backButton.setDisable(true);
                ipTextField.setDisable(true);
            }
        });
    }

    private void unBlockUi(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AnchorPane.setDisable(false);
                progressIndicator.setVisible(false);
                connectedButton.setText("reconnect");
                connectedButton.setDisable(false);
                backButton.setDisable(false);
                ipTextField.setDisable(false);
                CustomDialog.showAlertCantonnection();
                
            }
        });
    }
}
