/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.PlayersNamesOfflineMode;

/**
 * FXML Controller class
 *
 * @author PCM
 */
public class PlayerNameDialogController implements Initializable {

    @FXML
    private TextField dialogEdt;
    @FXML
    private Button btnPlay;
    @FXML
    private Text txtError;
    @FXML
    private TextField dialogEdt2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setFormateToEdt();
    }

    
    private boolean isEdtEmpty() {
        return dialogEdt.getText().equals("")
                || dialogEdt2.getText().equals("");
    }

    @FXML
    private void hideText(KeyEvent event) {
        txtError.setVisible(false);
    }

    @FXML
    private void onPlay(ActionEvent event) {
        if(isEdtEmpty()) {
            txtError.setVisible(true);
        } else {
            /*PlayersNamesOfflineMode names =
                    new PlayersNamesOfflineMode(dialogEdt.getText().toString(), dialogEdt2.getText().toString());
            System.out.println(names.getPlayerOneName());
            System.out.println(names.getPlayerTwoName());*/
            
            SceneController controller = new SceneController();
            try {
                controller.switchToPlayerVsPlayerScene(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
          
            dismisDialog(event);
            
        }
        
        
    }

    private void setFormateToEdt() {
        // to prevent spaces from in edit text
        TextFormatter<?> formatter2  = new TextFormatter<>((TextFormatter.Change change) -> {
        String text = change.getText();

        if (!text.isEmpty()) {
            String newText = text.replace(" ", "").toLowerCase();

            int carretPos = change.getCaretPosition() - text.length() + newText.length();
            change.setText(newText);

            change.selectRange(carretPos, carretPos);
        }
            return change;
        });
        
        TextFormatter<?> formatter = new TextFormatter<>((TextFormatter.Change change) -> {
        String text = change.getText();

        if (!text.isEmpty()) {
            String newText = text.replace(" ", "").toLowerCase();

            int carretPos = change.getCaretPosition() - text.length() + newText.length();
            change.setText(newText);

            change.selectRange(carretPos, carretPos);
        }
            return change;
        });
        
        dialogEdt.setTextFormatter(formatter);
        dialogEdt2.setTextFormatter(formatter2);
        // to set focus on first edtit text
        Platform.runLater(() -> {dialogEdt.requestFocus();});

    }

    @FXML
    private void closeDialog(ActionEvent event) {
        dismisDialog(event);
    }

    private void dismisDialog(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage statge = (Stage) btn.getScene().getWindow();
        statge.close();
    }

   

    
}
