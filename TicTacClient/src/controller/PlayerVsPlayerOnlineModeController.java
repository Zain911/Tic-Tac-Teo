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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author PCM
 */
public class PlayerVsPlayerOnlineModeController implements Initializable {

    @FXML
    private ImageView imgCoinPlayer1;
    @FXML
    private ImageView imgCoinPlayer2;
    @FXML
    private ImageView imgXO;
    @FXML
    private Button btnBack;

    SceneController controller;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image image1 = new Image("/resource/star.png"){};
        Image img = new Image("/resource/xoimg.png"){};

        imgCoinPlayer1.setImage(image1);
        imgCoinPlayer1.setCache(true);
        imgCoinPlayer2.setImage(image1);
        imgCoinPlayer2.setCache(true);
        imgXO.setImage(img);
        imgXO.setCache(true);
        
         controller = new SceneController();
    }    

    @FXML
    private void backToMain(ActionEvent event) {
        
        try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
