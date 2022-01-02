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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed Galal
 */
public class HardModeFXMLController implements Initializable {

    @FXML
    private ImageView easyModeImg;
    @FXML
    private ImageView hardModeImg;
    @FXML
    Button easyModeBtn;
    @FXML
    Button hardModeBtn;
    @FXML
    private Button backBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;
    SceneController controller;
    PlayervsComputerEasyModeController easyModeScene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image hardImg = new Image("/resource/hardmode.png");
        hardModeImg.setImage(hardImg);

        Image easyImg = new Image("/resource/easymode.png");
        easyModeImg.setImage(easyImg);

        controller = new SceneController();

    }

    @FXML
    private void setClickBack(ActionEvent event) {
        try {
            controller.switchToMainScene(event);
        } catch (IOException ex) {
            Logger.getLogger(HardModeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setClickEasy(ActionEvent event) {

        myScene("easy");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void setClickHard(ActionEvent event) {
        myScene("hard");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void myScene(String mode) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlayervsComputerEasyMode.fxml"));
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(HardModeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        PlayervsComputerEasyModeController board = loader.getController();
        board.checkHardOrEasy(mode);

    }

}
