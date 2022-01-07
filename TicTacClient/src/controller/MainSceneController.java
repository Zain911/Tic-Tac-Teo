/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.sun.javafx.scene.control.skin.CustomColorDialog;
import controller.SceneController;
import helper.AccessFile;
import helper.CustomDialog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;



public class MainSceneController implements Initializable {
    SceneController controller;
    public static boolean isRecord=false;
    @FXML
    private Button playerVsAiBtn;
    @FXML
    private Label labSinglePlayer;
    @FXML
    private Button playerVsPlayerBtn;
    @FXML
    private Button btnOnlineGame;
    @FXML
    private Button btnWatchGame;
    @FXML
    private AnchorPane playervsplayerOffLine;
    @FXML
    private void handleVsAiBtn(ActionEvent event){
        controller=new SceneController();
        try {
         controller.switchToChooseLevelModeScene(event);

            //controller.switchToChooseLevelModeScene(event);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

     @FXML
    private void handleVsPlayerOnlineBtn(ActionEvent event){
        
         System.out.println("online ");
        
        try {
             controller=new SceneController();
            
            controller.switchToIpScene(event);
            
  
        } catch (IOException ex) {
            System.out.println("bbb");
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void openPlayerVsPlayerOffLine(ActionEvent event) {
        try {
            controller = new SceneController();
            controller.switchToPlayersNamesScene(event);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openRecordGame(ActionEvent event) {
        try {
            controller = new SceneController();
            controller.switchToRecordListScene(event);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
   

    

    

  
     
}

