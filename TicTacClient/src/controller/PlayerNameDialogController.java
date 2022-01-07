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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.PlayersNamesOfflineMode;

/**
 * FXML Controller class
 *
 * @author PCM
 */
public class PlayerNameDialogController implements Initializable {

    @FXML
    private Button btnPlay;
    @FXML
    private Text txtError;

    @FXML
    private ImageView playersImg;
    @FXML
    private TextField edtNameTwo;
    @FXML
    private TextField edtNameOne;
    public static String firstName;
    public static String secondName;
    @FXML
    private ImageView backButton;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image img = new Image("/resource/pvsp.png");
        playersImg.setImage(img);
        setFormateToEdt();
    }

    private boolean isEdtEmpty() {
        return edtNameOne.getText().equals("")
                || edtNameTwo.getText().equals("");
    }

    private void hideText(KeyEvent event) {
        txtError.setVisible(false);
    }

    private void onPlay(ActionEvent event) {
        if (isEdtEmpty()) {
            txtError.setVisible(true);
        } else {
            SceneController controller = new SceneController();

            // setFirstName(edtNameOne.getText().toString());
            //setSecoundName( edtNameTwo.getText());
            firstName = (edtNameOne.getText());
            secondName=( edtNameTwo.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlayerVsPlayerView.fxml"));

            try {
                loader.load();
//                PlayerVsPlayerController con = loader.getController();
                // con.setPlayerNames(firstName,secoundName);
                controller.switchToPlayerVsPlayerScene(event);
            } catch (IOException ex) {
                Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void setFormateToEdt() {
        // to prevent spaces from in edit text
        TextFormatter<?> formatter2 = new TextFormatter<>((TextFormatter.Change change) -> {
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

        edtNameOne.setTextFormatter(formatter);
        edtNameTwo.setTextFormatter(formatter2);
        // to set focus on first edtit text
        Platform.runLater(() -> {
            edtNameOne.requestFocus();
        });

    }

    private void closeDialog(ActionEvent event) {
        dismisDialog(event);
    }

    private void dismisDialog(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage statge = (Stage) btn.getScene().getWindow();
        statge.close();

    }

  


    @FXML
    private void onBackButtonClick(MouseEvent event) {
         SceneController controller = new SceneController();
        try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void startPlay(ActionEvent event) {
        
           if (isEdtEmpty()) {
            txtError.setVisible(true);
        } else {
            SceneController controller = new SceneController();

            // setFirstName(edtNameOne.getText().toString());
            //setSecoundName( edtNameTwo.getText());
            firstName = (edtNameOne.getText());
            secondName=( edtNameTwo.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlayerVsPlayerView.fxml"));

            try {
                loader.load();
//                PlayerVsPlayerController con = loader.getController();
                // con.setPlayerNames(firstName,secoundName);
                controller.switchToPlayerVsPlayerScene(event);
            } catch (IOException ex) {
                Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

  @FXML
    private void onPlayerOneKeyPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
                edtNameTwo.requestFocus();
               // edtNameTwo.re
            }
        
    }

   /* @FXML
    private void onPlayerTwoKeyPressed(KeyEvent event) {
        
        if(event.getCode().equals(KeyCode.ENTER)){
               btnPlay.requestFocus();
            }
        
        
        
    }*/
    
    private void startGame(ActionEvent event){
    
      if (isEdtEmpty()) {
            txtError.setVisible(true);
        } else {
            SceneController controller = new SceneController();

            // setFirstName(edtNameOne.getText().toString());
            //setSecoundName( edtNameTwo.getText());
            firstName = (edtNameOne.getText());
            secondName=( edtNameTwo.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlayerVsPlayerView.fxml"));

            try {
                loader.load();
//                PlayerVsPlayerController con = loader.getController();
                // con.setPlayerNames(firstName,secoundName);
                controller.switchToPlayerVsPlayerScene(event);
            } catch (IOException ex) {
                Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    
    }

    @FXML
    private void onPlayerTwoKeyPressed(KeyEvent event) {
          if(event.getCode().equals(KeyCode.ENTER)){
             
            }
    }

}
