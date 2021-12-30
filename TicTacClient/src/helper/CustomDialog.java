/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import controller.SceneController;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Ghaly
 */
public class CustomDialog {

    public static boolean askPlayAgain(String s) {

        ButtonType Yes = new ButtonType("Play Again");
        ButtonType No = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setTitle("Paly Again");
        a.getDialogPane().getButtonTypes().addAll(Yes, No);
        a.setHeaderText(s);
        a.showAndWait();
        if (a.getResult() == Yes) {
            return true;
        } else {
            return false;
        }
    }

    
    
    public static void showIpDialog(ActionEvent event) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text Input Dialog");

        dialog.setContentText("Please enter ip to connect :");

        dialog.setHeaderText(" Enter Network Ip to connect ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {

            if (isVaildIp(result.get()) == true) {
                SceneController controller = new SceneController();
                try {

                    controller.switchToLoginScene(event);
                } catch (IOException ex) {
                    Logger.getLogger(CustomDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error occur");
                
                alert.setContentText("invalid ip Adderss");

                alert.showAndWait();

            }
        }

    }

    private static boolean isVaildIp(String ip) {

        String ipRegex = "\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b";
        Pattern ip_pattern = Pattern.compile(ipRegex);
        if (ip_pattern.matcher(ip).matches()) {

            return true;

        }

        return false;

    }

}
